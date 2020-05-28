package com.ep.jo.domain.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDto {
	private String type;
	private Long uid;
	private String email;
	private String username;
	private int gold;
	private int dia;
	private int exp;
	private int win;
	private int lose;
}
