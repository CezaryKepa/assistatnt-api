package com.businessassistant.assistantapi.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetService {

    AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<com.businessassistant.assistantapi.gen.Asset> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(AssetMapper::toDto)
                .collect(Collectors.toList());
    }
    public Optional<com.businessassistant.assistantapi.gen.Asset> findById(Long id) {
        return assetRepository.findById(id).map(AssetMapper::toDto);
    }

    //TODO:check for duplicates
    public List<Asset> saveAll(List<Asset> assets) {
        return saveAll(assets);
    }
}
