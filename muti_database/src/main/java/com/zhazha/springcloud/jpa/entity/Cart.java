package com.zhazha.springcloud.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cart", schema = "jpa")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    @ToString.Exclude
    private Set<Item> items = new LinkedHashSet<>();

}