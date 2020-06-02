package com.test.notification.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdGenericResponse<T> extends ResponseBase {
	
	private static final long serialVersionUID = -2171231561283150148L;
	
	private T data;
	
}

	
