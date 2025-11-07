package com.kt.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 *packageName    : com.kt.common
 * fileName       : CustomException
 * author         : howee
 * date           : 2025-11-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-11-07        howee       최초 생성
 */

@Getter
public class CustomException extends RuntimeException {
	private final ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
