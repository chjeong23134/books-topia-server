package me.bookstopia.aladin.enumeration;

public enum AladinListTypeEnum {
    BESTSELLER("Bestseller"),
    NEW("ItemNewAll");

    private final String type;

    AladinListTypeEnum(String type) {
        this.type = type;
    }

    public String getString() {
        return type;
    }
}
