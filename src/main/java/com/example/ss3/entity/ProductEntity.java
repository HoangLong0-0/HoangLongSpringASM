package com.example.ss3.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne() //EAGER
    @JoinColumn(name = "categoryid", insertable = false, updatable = false)
    private CategoryEntity category;

    public ProductEntity(Integer categoryid, String name, Integer price, Integer quantity) {
        this.categoryid = categoryid;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
