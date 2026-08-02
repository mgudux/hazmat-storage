package com.mgudux.ifas.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.enums.IndustryType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class CompanyClientApp {

    private final JFrame frame;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public CompanyClientApp() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();

        this.frame = new JFrame("IFAS Company Manager");
        this.frame.setSize(600, 400);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Adresse", "Branche"};
        this.tableModel = new DefaultTableModel(columns, 0);
        this.table = new JTable(tableModel);

        this.frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Firmen laden");
        loadButton.addActionListener(e -> fetchCompaniesFromServer());

        JButton createButton = new JButton("Firma erstellen");
        createButton.addActionListener(e -> showCreateCompanyDialog());

        buttonPanel.add(loadButton);
        buttonPanel.add(createButton);
        this.frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void fetchCompaniesFromServer() {
        SwingWorker<List<CompanyDto.Summary>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<CompanyDto.Summary> doInBackground() throws Exception {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8081/api/company"))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                return objectMapper.readValue(response.body(), new TypeReference<List<CompanyDto.Summary>>() {});
            }

            @Override
            protected void done() {
                try {
                    List<CompanyDto.Summary> companies = get();
                    tableModel.setRowCount(0);
                    for (CompanyDto.Summary c : companies) {
                        tableModel.addRow(new Object[]{c.id(), c.name(), c.address(), c.industryType()});
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Fehler beim Laden: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void showCreateCompanyDialog() {
        JDialog dialog = new JDialog(frame, "Neue Firma anlegen", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2, 5, 5));
        dialog.setLocationRelativeTo(frame);

        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        String[] industryChoices = Arrays.stream(IndustryType.values())
                .map(IndustryType::name)
                .toArray(String[]::new);
        JComboBox<String> industryBox = new JComboBox<>(industryChoices);

        dialog.add(new JLabel(" Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel(" Adresse:"));
        dialog.add(addressField);
        dialog.add(new JLabel(" Branche:"));
        dialog.add(industryBox);

        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            IndustryType industry = IndustryType.valueOf((String) industryBox.getSelectedItem());
            createCompanyOnServer(name, address, industry);
            dialog.dispose();
        });

        JButton cancelButton = new JButton("Abbrechen");
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(saveButton);
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    private void createCompanyOnServer(String name, String address, IndustryType industry) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                CompanyDto.Request requestDto = new CompanyDto.Request(name, address, industry);
                String jsonPayload = objectMapper.writeValueAsString(requestDto);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8081/api/company"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 201) {
                    throw new RuntimeException("Server meldet Fehler: " + response.statusCode());
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(frame, "Firma erfolgreich angelegt!");
                    fetchCompaniesFromServer();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Fehler beim Speichern: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CompanyClientApp().setVisible(true);
        });
    }
}