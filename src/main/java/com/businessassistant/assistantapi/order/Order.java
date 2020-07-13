package com.businessassistant.assistantapi.order;

import com.businessassistant.assistantapi.asset.Asset;
import com.businessassistant.assistantapi.client.Client;
import com.businessassistant.assistantapi.order_details.OrderDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ordert")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_assets",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id", referencedColumnName = "id")
    )
    private List<Asset> assets = new ArrayList<>();
    @OneToOne
    private OrderDetails orderDetails;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
