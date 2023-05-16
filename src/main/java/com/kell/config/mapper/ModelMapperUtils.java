package com.kell.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public final class ModelMapperUtils {

    private static ModelMapper modelMapper;

    private ModelMapperUtils() {}

    public static ModelMapper getModelMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT)
                    .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
            modelMapper.addConverter(new PersistentBagToListConverter());
        }
        return modelMapper;
    }
}
