package com.example.metric_api.response;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{

	private LocalDateTime timestamp;
	private Integer status;
	private String message;
	private T data;

	// error için gerekli
	public ApiResponse(LocalDateTime timestamp, Integer status,String message){
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}

	public static <T> ResponseEntity<ApiResponse<T>> ok(ResponseType responseType, T data){
		ApiResponse<T> response = new ApiResponse<T>(
			LocalDateTime.now(),
			responseType.getStatus().value(),
			responseType.getMessage(),
			data);
		
		return ResponseEntity.status(responseType.getStatus()).body(response);
	}

	public static <T> ResponseEntity<ApiResponse<T>> error(ResponseType responseType){
		ApiResponse<T> response = new ApiResponse<T>(
				LocalDateTime.now(),
				responseType.getStatus().value(),
				responseType.getMessage());

		return ResponseEntity.status(responseType.getStatus()).body(response);
	}
}
