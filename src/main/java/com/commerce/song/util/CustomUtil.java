package com.commerce.song.util;

import com.commerce.song.domain.dto.PageDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUtil {
    public static <I, O> O convertClass(I entity, Class<? extends O> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(entity, clazz);
    }

    public static PageRequest convertPageVo(PageDto vo) {
        return PageRequest.of(vo.getPage(), vo.getSize());
    }

    public static <O> Collection<O> safeList(Collection<O> other) {
        return other == null ? Collections.emptyList() : other;
    }
}
