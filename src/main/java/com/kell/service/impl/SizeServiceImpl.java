package com.kell.service.impl;

import com.kell.repository.SizeRepository;
import com.kell.service.SizeService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.SizeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;
    private final MappingHelper mappingHelper;
    @Override
    @Transactional
    public List<SizeDto> getAllSize() {
        return sizeRepository.findAll()
                .stream()
                .map(e -> mappingHelper.map(e, SizeDto.class))
                .collect(Collectors.toList());
    }
}
