package me.bookstopia.aladin.dao;

import me.bookstopia.aladin.response.AladinSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AladinService {
    private static final Logger logger = Logger.getLogger(AladinService.class.getName());

    private final RestTemplate restTemplate;

    @Value("${aladin.ttb}")
    private String key;

    private static final String ALADIN_URL = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx";

    public AladinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AladinSearchResponse find(String searchWord) {
        String url = String.format("%s?ttbkey=%s&Query=%s&SearchTarget=Book&output=js&Version=20131101",
                ALADIN_URL, key, searchWord);

        AladinSearchResponse res = restTemplate.getForObject(url, AladinSearchResponse.class);

        return res;
    }
}
