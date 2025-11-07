package com.kt.dto.user;

import java.time.LocalDateTime;

import com.kt.domain.user.User;

/**
 *packageName    : com.kt.dto.user
 * fileName       : UserResponse
 * author         : howee
 * date           : 2025-11-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-11-07        howee       최초 생성
 */
public interface UserResponse {
	record Search(
		Long id,
		String username,
		LocalDateTime createdAt
	){

	}

	record Detail(
		Long id,
		String name,
		String email
	) {
		public static Detail of(User user) {
			return new Detail(
				user.getId(),
				user.getName(),
				user.getEmail()
			);
		}
	}
}
