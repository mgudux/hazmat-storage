package com.mgudux.ifas.mapper.impl;

import com.mgudux.ifas.domain.dto.ContentDto;
import com.mgudux.ifas.domain.entity.Content;
import com.mgudux.ifas.mapper.ContentMapper;
import com.mgudux.ifas.mapper.StorageMapper;
import org.springframework.stereotype.Component;

@Component
public class ContentMapperImpl implements ContentMapper {

    private final StorageMapper storageMapper;

    public ContentMapperImpl(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }

    @Override
    public ContentDto.Summary toSummary(Content content) {
        if (content == null) {
            return null;
        }
        return new ContentDto.Summary(
                content.getId(),
                content.getName(),
                content.getHazard(),
                content.getAmount(),
                content.getAmountUnit(),
                content.getExpirationDate()
        );
    }

    @Override
    public ContentDto.Detail toDetail(Content content) {
        if (content == null) {
            return null;
        }
        return new ContentDto.Detail(
                content.getId(),
                content.getName(),
                content.getHazard(),
                content.getAmount(),
                content.getAmountUnit(),
                content.getExpirationDate(),
                content.getUpdated(),
                content.getCreated(),
                storageMapper.toSummary(content.getStorage())
        );

    }

    @Override
    public Content toEntity(ContentDto.Request request) {
        if (request == null) {
            return null;
        }
        Content content = new Content();
        content.setName(request.name());
        content.setHazard(request.hazardClass());
        content.setAmount(request.amount());
        content.setAmountUnit(request.amountUnit());
        content.setExpirationDate(request.expirationDate());
        return content;
    }
}
