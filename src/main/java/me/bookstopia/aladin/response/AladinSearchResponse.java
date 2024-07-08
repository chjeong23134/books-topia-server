package me.bookstopia.aladin.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.bookstopia.aladin.dto.BookDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinSearchResponse {
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;

    private List<BookDto> items;
}