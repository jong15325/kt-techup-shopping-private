package com.kt.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *packageName    : com.kt.common
 * fileName       : ApiResult
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
public class ApiResult<T> {
	private String code;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL) // data가 null인 경우 응답에서 제외
	private T data;

	public static ApiResult<Void> ok() {
		return ApiResult.of("ok", "성공", null);
	}

	public static <T> ApiResult<T> ok(T data) {
		return ApiResult.of("ok", "성공", data);
	}

	private static <T> ApiResult<T> of(String code, String message, T data) {
		return new ApiResult<>(code, message, data);
	}
}
