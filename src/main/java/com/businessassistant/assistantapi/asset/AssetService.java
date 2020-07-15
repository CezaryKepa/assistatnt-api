package com.businessassistant.assistantapi.asset;

import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<Asset> saveAll(List<Asset> assets) {
        List<Asset> assetsBySerialNumbers = new ArrayList<>();
        ServiceStatus serviceStatus = new ServiceStatus();

        for (Asset e: assets) {
            //TODO: refactor to many queries
            Optional<Asset> bySerialNumber = assetRepository.findBySerialNumber(e.getSerialNumber());
            bySerialNumber.ifPresent(assetsBySerialNumbers::add);
        }

        if(!assetsBySerialNumbers.isEmpty()){
            StringBuilder message = new StringBuilder();
            message.append("Duplicate assets with this serial numbers: ");

            assetsBySerialNumbers.forEach(e->
                    message.append(e.getSerialNumber()).append(" ")
            );

            serviceStatus.setStatusCode("409");
            serviceStatus.setMessage(message.toString());
            throw new ServiceFaultException("Conflict",serviceStatus);
        }

        return assetRepository.saveAll(assets);
    }
}
