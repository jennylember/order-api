package ru.lember.orderapi.data.dao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

}
