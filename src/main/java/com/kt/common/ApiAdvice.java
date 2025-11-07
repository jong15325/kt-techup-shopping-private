package com.kt.common;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

/**
 *packageName    : com.kt.common
 * fileName       : ApiAdvice
 * author         : howee
 * date           : 2025-11-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-11-07        howee       최초 생성
 */

@Hidden // swagger 에서 해당 클래스 스캔하지 않도록 설정
@RestControllerAdvice
public class ApiAdvice {

	// 전역적으로 서버에러 핸들링
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse.ErrorData> internalServerError(Exception e) {
		e.printStackTrace();//<-에러가뜬건지 알아볼때

		return ErrorResponse.error(HttpStatus.INTERNAL_SERVER_ERROR,"서버에러입니다. 백엔드팀에 문의하세요.");
	}

	// 커스텀 예외 핸들링(비즈니스 로직 에러 등)
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse.ErrorData> customException(CustomException e) {
		return ErrorResponse.error(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
	}

	// @Valid, @Validated 유효성 검증 실패 시 핸들링
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse.ErrorData> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		e.printStackTrace();
		var details = Arrays.toString(e.getDetailMessageArguments());
		var message = details.split(",", 2)[1].replace("]", "").trim();

		return ErrorResponse.error(HttpStatus.BAD_REQUEST, message);
	}

	//todo 이외 추가적인 예외 핸들링


}
