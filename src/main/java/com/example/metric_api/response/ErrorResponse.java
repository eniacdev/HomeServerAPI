package com.example.metric_api.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ErrorResponse {

	//error response model
	private LocalDateTime timestamp;
	private Integer status;
	private String code;
	private String message;
}
