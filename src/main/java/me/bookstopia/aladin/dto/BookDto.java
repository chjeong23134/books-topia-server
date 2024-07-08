package me.bookstopia.aladin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {
    private String title;
    private String author;
    private String pubDate;
    private String description;
    private String isbn13;
    private int priceStandard;
    private String cover;
    private String publisher;
    private boolean adult;
}
