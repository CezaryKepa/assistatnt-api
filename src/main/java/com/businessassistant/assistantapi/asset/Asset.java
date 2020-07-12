package com.businessassistant.assistantapi.asset;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    @Enumerated(EnumType.STRING)
    private AssetCategory category;
    private Double price;
    @Column(length = 1024)
    private String description;
    private Integer stock;
    private String imgUrl;
}
