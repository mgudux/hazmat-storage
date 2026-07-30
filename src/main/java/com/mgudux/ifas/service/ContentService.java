package com.mgudux.ifas.service;

import com.mgudux.ifas.domain.dto.ContentDto;
import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

public interface ContentService {
    List<ContentDto.Summary> listContents();
    ContentDto.Summary createContent(ContentDto.Request request);
    void deleteContent(long id);
    ContentDto.Detail updateContent(long id, ContentDto.Request request);
    List<ContentDto.Summary> getContentsByName(String name);
    List<ContentDto.Summary> getContentsByAmount(Double amount, MeasurementUnit amountUnit);
    List<ContentDto.Summary> getContentsByExpiration(LocalDateTime expirationDate, Sort sort);

}
