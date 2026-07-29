package com.mgudux.ifas.mapper;

import com.mgudux.ifas.domain.dto.ContentDto;
import com.mgudux.ifas.domain.entity.Content;

public interface ContentMapper {
    ContentDto.Summary toSummary(Content content);
    ContentDto.Detail toDetail(Content content);

    Content toEntity(ContentDto.Request request);
}
