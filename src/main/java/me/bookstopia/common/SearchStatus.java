package me.bookstopia.common;

import lombok.Getter;

@Getter
public enum SearchStatus {
    ALL("Keyword"),
    BOOK_NAME("Title"),
    WRITER_NAME("Author");

    // 문자열 값을 반환하는 메서드
    private final String label;

    // 생성자
    SearchStatus(String label) {
        this.label = label;
    }

}
