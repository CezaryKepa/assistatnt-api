package com.businessassistant.assistantapi.asset;

import java.math.BigInteger;

public class AssetMapper {

    public static com.businessassistant.assistantapi.gen.Asset toDto(Asset asset){
        com.businessassistant.assistantapi.gen.Asset dto= new com.businessassistant.assistantapi.gen.Asset();
        dto.setId(asset.getId());
        dto.setBrand(asset.getBrand());
        dto.setCategory(asset.getCategory().name());
        dto.setDescription(asset.getDescription());
        dto.setImgUrl(asset.getImgUrl());
        dto.setName(asset.getName());
        dto.setPrice(asset.getPrice().toString());
        dto.setStock(BigInteger.valueOf(asset.getStock()));
        return dto;
    }

    public static Asset toEntity(com.businessassistant.assistantapi.gen.Asset asset) {
        Asset entity= new Asset();
        entity.setId(asset.getId());
        entity.setBrand(asset.getBrand());
        entity.setCategory(AssetCategory.valueOf(asset.getCategory()));
        entity.setDescription(asset.getDescription());
        entity.setImgUrl(asset.getImgUrl());
        entity.setName(asset.getName());
        entity.setPrice(Double.parseDouble(asset.getPrice()));
        entity.setStock(asset.getStock().intValue());
        return entity;
    }
}