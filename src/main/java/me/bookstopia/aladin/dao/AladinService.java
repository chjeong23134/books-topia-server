package me.bookstopia.aladin.dao;

import me.bookstopia.aladin.enumeration.AladinListTypeEnum;
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

    private static final String ALADIN_URL = "http://www.aladin.co.kr/ttb/api/";

    public AladinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AladinSearchResponse findByKeyWord(String keyWord) {
        String url = String.format("%sItemSearch.aspx?ttbkey=%s&Query=%s&SearchTarget=Book&output=js&Version=20131101",
                ALADIN_URL, key, keyWord);

        return restTemplate.getForObject(url, AladinSearchResponse.class);
    }

    public AladinSearchResponse findByType(AladinListTypeEnum type) {
        String url = String.format("%sItemList.aspx?ttbkey=%s&QueryType=%s&SearchTarget=Book&output=js&Version=20131101"
                ,
                ALADIN_URL, key, type.getString());

        return restTemplate.getForObject(url, AladinSearchResponse.class);
    }
}
