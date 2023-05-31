package com.kell.service.impl;

import com.kell.model.Material;
import com.kell.repository.MaterialRepository;
import com.kell.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;

    @Override
    public List<Material> getAllMaterial() {
        return materialRepository.findAll();
    }
}
