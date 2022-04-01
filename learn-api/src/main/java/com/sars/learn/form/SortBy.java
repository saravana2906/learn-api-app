package com.sars.learn.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortBy {
	private String fieldName;
	private SortOrder order;
	public  enum SortOrder {
		ASCENDING,
		DESCENDING
	}
}

