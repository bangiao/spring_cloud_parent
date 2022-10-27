package com.zhazha.formlogin.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (Authorities)实体类
 *
 * @author makejava
 * @since 2022-10-27 23:59:15
 */
@Data
@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {
	private static final long serialVersionUID = -56878014697011982L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String username;
	
	private String authority;
	
}

