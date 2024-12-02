package com.finale.search.dto;

import java.util.List;

public record PopularSearchResponse (
    List<String> popularSearch
) {
}
