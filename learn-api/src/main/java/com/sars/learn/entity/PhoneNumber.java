package com.sars.learn.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {
	
	@Column(nullable = false)
	private String countryCode;
	@Column(nullable = false)
	private String phoneNumber;
}
