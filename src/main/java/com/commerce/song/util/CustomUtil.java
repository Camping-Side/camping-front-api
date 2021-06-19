package com.commerce.song.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public class CustomUtil {
    public static <I, O> O convertClass(I entity, Class<? extends O> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(entity, clazz);
    }
}
