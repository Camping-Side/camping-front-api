package com.commerce.song.util;

import com.commerce.song.domain.dto.PageDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.data.domain.PageRequest;

public class CustomUtil {
    public static <I, O> O convertClass(I entity, Class<? extends O> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(entity, clazz);
    }

    public static PageRequest convertPageVo(PageDto vo) {
        return PageRequest.of(vo.getPage(), vo.getSize());
    }
}
