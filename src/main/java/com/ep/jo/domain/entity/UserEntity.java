package com.ep.jo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.ep.jo.domain.dto.LoginDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Setter
public class UserEntity extends TimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	
	@Email(message = "이메일 형식에 맞지 않습니다.")
	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@Column(length = 100, nullable = false)
	private String username;
	
	@Builder.Default
	private int gold = 0;
	@Builder.Default
	private int dia = 0;
	@Builder.Default
	private int exp = 0;
	@Builder.Default
	private int win = 0;
	@Builder.Default
	private int lose = 0;
	
	public LoginDto toDto() {
		return LoginDto.builder()
				.uid(uid)
				.type("LOGIN")
				.email(email)
				.username(username)
				.gold(gold)
				.dia(dia)
				.exp(exp)
				.win(win)
				.lose(lose)
				.build();
	}

}
