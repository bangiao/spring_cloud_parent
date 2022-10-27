package com.zhazha.formlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UsersDTO implements Serializable {
	private String username;
	private String password;

}
