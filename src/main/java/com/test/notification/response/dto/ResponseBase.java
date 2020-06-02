package com.test.notification.response.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseBase implements Serializable {

	private static final long serialVersionUID = -635901634908218380L;

	@Builder.Default
	private String message = "Success";

	@Builder.Default
	private Boolean status = true;

	@Builder.Default
	private HttpStatus httpStatus = HttpStatus.OK;


}