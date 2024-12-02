package com.finale.search;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.finale.global.jwt.JwtProvider;
import com.finale.product.controller.ProductController;
import com.finale.product.dto.ProductPage;
import com.finale.product.repository.dto.ProductSearch;
import com.finale.product.service.ProductImageService;
import com.finale.product.service.ProductService;
import com.finale.product.util.ProductSort;
import com.finale.user.controller.ArtistController;
import com.finale.user.dto.ArtistInfoPage;
import com.finale.user.repository.dto.ArtistSearch;
import com.finale.user.service.ArtistService;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({ProductController.class, ArtistController.class})
@AutoConfigureMockMvc(addFilters = false)
@Import(JwtProvider.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductImageService productImageService;

    @MockBean
    private ArtistService artistService;


    @Test
    @DisplayName("상품 검색 컨트롤러")
    void 상품_검색() throws Exception {
        // given
        String query = "product";
        int size = 10;
        int page = 0;
        ProductSort productSort = ProductSort.LATEST;

        var productSearch = mock(ProductSearch.class);
        given(productSearch.getId()).willReturn(1L);
        given(productSearch.getName()).willReturn("product1");
        given(productSearch.getArtist()).willReturn("artist");
        given(productSearch.getPrice()).willReturn(10000L);
        given(productSearch.getThumbnailUrl()).willReturn("thumbnailUrl");
        var paging = ProductPage.Paging.from(new PageImpl<>(List.of(productSearch)));
        given(productService.getProductsByPage(eq(query), any(Pageable.class)))
            .willReturn(paging);

        // when & then
        mvc.perform(get("/v1/products")
                .param("query", query)
                .param("size", String.valueOf(size))
                .param("page", String.valueOf(page))
                .param("sort", productSort.name()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext").value(false))
            .andExpect(jsonPath("$.products[0].name").value("product1"))
            .andExpect(jsonPath("$.products[0].artist").value("artist"))
            .andExpect(jsonPath("$.products[0].price").value(10000L))
            .andExpect(jsonPath("$.products[0].thumbnailUrl").value("thumbnailUrl"));
    }

    @Test
    @DisplayName("작가 검색 컨트롤러")
    void 작가_검색() throws Exception {
        // given
        String query = "nickname";
        int size = 10;
        int page = 0;

        var artistSearch = mock(ArtistSearch.class);
        given(artistSearch.getId()).willReturn(1L);
        given(artistSearch.getNickname()).willReturn("nickname");
        given(artistSearch.getArtistImageUrl()).willReturn("artistImageUrl");
        given(artistSearch.getTotalFollowers()).willReturn(10000L);
        given(artistSearch.getTotalLikes()).willReturn(10000L);
        var paging = ArtistInfoPage.Paging.of(new PageImpl<>(List.of(artistSearch)), Set.of());
        given(artistService.getArtistsByPage(eq(query), any(Pageable.class), null))
            .willReturn(paging);

        // when & then
        mvc.perform(get("/v1/artists")
                .param("query", query)
                .param("size", String.valueOf(size))
                .param("page", String.valueOf(page)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext").value(false))
            .andExpect(jsonPath("$.artists[0].nickname").value("nickname"))
            .andExpect(jsonPath("$.artists[0].artistImageUrl").value("artistImageUrl"))
            .andExpect(jsonPath("$.artists[0].totalFollowers").value(10000L))
            .andExpect(jsonPath("$.artists[0].totalLikes").value(10000L));
    }

}
