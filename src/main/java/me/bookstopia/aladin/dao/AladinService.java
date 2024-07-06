package me.bookstopia.aladin.dao;

import me.bookstopia.common.SearchStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AladinService {
    private final RestTemplate restTemplate;

    @Value("${jwt.secret}")
    private String key;

    private static final String ALADIN_URL = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx";

    public AladinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> find(String searchWord, SearchStatus type) {
        String url = String.format("%s?ttbkey=%s&Query=%s&SearchTarget=Book&output=js&Version=20131101",
                ALADIN_URL, key, type.getLabel());

        return restTemplate.getForObject(url, AladinResponse.class);
    }
}
