package com.kell.config.mapper;

import org.hibernate.collection.internal.PersistentBag;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PersistentBagToListConverter implements Converter<PersistentBag, List<?>> {
    @Override
    public List<?> convert(MappingContext<PersistentBag, List<?>> context) {
        if (context == null)
            return new ArrayList<>();
        PersistentBag source = context.getSource();
        if (source == null) {
            return null;
        }
        return (List<?>) source.stream().collect(Collectors.toList());
    }
}
