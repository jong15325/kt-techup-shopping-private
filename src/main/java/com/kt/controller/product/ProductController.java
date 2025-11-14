package com.kt.controller.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.ApiResult;
import com.kt.common.SwaggerAssistance;
import com.kt.domain.product.Product;
import com.kt.dto.product.ProductCreateReqeust;
import com.kt.dto.product.ProductUpdateReqeust;
import com.kt.service.ProductService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 *packageName    : com.kt.controller
 * fileName       : ProductController
 * author         : howee
 * date           : 2025-11-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-11-06        howee       최초 생성
 */

@Tag(name = "Product", description = "상품 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController extends SwaggerAssistance {

	private final ProductService productService;

	//조회(리스트)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Page<Product>> search(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(required = false) String keyword) {

		return ApiResult.ok(productService.search(PageRequest.of(page - 1, size), keyword));
	}

	//조회(단건)
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Product> detail(@PathVariable Long id) {
		return ApiResult.ok(productService.detail(id));
	}

	//TODO: restful하지 않음
	//TODO: 상태 변경 각각 엔드포인트로 분리
	//상태변경
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Void> active(
		@PathVariable Long id
	) {

		productService.active(id);

		return ApiResult.ok();
	}

}
