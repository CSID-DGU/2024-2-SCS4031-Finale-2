package com.finale.product.controller.docs;

import com.finale.global.ApiResponse.ApiResponse;
import com.finale.product.dto.ProductPage;
import com.finale.product.dto.ProductPage.Paging;
import com.finale.product.util.ProductSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "상품 관련 기능", description = "상품 관련 API")
public interface ProductApiDocs {
    
    @Operation(summary = "상품 검색")
    @GetMapping
    public ResponseEntity<ApiResponse<Paging>> getProductsByPage(
        @RequestParam("query") String query,
        @RequestParam(name = "size", required = false, defaultValue = "20") int size,
        @RequestParam("page") int page,
        @RequestParam("sort") ProductSort productSort
    );
}
