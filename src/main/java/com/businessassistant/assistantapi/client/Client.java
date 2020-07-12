package com.businessassistant.assistantapi.client;

import com.businessassistant.assistantapi.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String surname;
    private String email;
    @OneToMany(mappedBy = "client")
    List<Order> orders =new ArrayList<>();
 }
