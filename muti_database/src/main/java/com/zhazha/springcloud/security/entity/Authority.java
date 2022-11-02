package com.zhazha.springcloud.security.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "authorities", schema = "spring_security")
public class Authority {
    @Id
    @Column(name = "username", nullable = false)
    private String id;

    @Column(name = "authority")
    private String authority;
}