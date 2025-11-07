package com.kt.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *packageName    : com.kt.common
 * fileName       : ErrorResponse
 * author         : howee
 * date           : 2025-11-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-11-07        howee       최초 생성
 */

@Getter
@AllArgsConstructor
public class ErrorResponse {
	private HttpStatus status;
	private String message;

	public static ResponseEntity<ErrorData> error(HttpStatus status, String message) {
		return ResponseEntity.status(status).body(ErrorData.of(status.series().name(), message));
	}

	@Getter
	@AllArgsConstructor
	public static class ErrorData {
		private String code;
		private String message;

		public static ErrorData of(String code, String message) {
			return new ErrorData(code, message);
		}
	}
}
