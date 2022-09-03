package com.zhazha.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Payment)实体类
 *
 * @author makejava
 * @since 2022-09-02 20:41:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
	private static final long serialVersionUID = 678388202034900391L;
	private Long id;
	private String serial;
}

