package me.bookstopia.aladin.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.bookstopia.aladin.dto.BookDto;

import java.util.List;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinSearchResponse {
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;

    @JsonProperty("item")
    private List<BookDto> items;
}