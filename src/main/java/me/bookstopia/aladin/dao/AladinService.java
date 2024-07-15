package me.bookstopia.aladin.dao;

import me.bookstopia.aladin.dto.BookDto;
import me.bookstopia.aladin.enumeration.AladinListTypeEnum;
import me.bookstopia.aladin.response.AladinResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public AladinResponse findByKeyWord(String keyword) {
        String url = String.format("%sItemSearch.aspx?ttbkey=%s&Query=%s&SearchTarget=Book&output=js&Version=20131101",
                ALADIN_URL, key, keyword);

        return restTemplate.getForObject(url, AladinResponse.class);
    }

    public AladinResponse findByType(AladinListTypeEnum type) {
        String url = String.format("%sItemList.aspx?ttbkey=%s&QueryType=%s&SearchTarget=Book&output=js&Version=20131101"
                ,
                ALADIN_URL, key, type.getString());

        return restTemplate.getForObject(url, AladinResponse.class);
    }

    public AladinResponse findById(Long id) {
        String url = String.format("%sItemLookUp.aspx?ttbkey=%s&itemIdType=ISBN13&ItemId=%s&output=js&Version=20131101",
                ALADIN_URL, key, id);

        return restTemplate.getForObject(url, AladinResponse.class);
    }
}
