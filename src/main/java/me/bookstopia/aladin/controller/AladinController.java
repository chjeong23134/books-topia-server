package me.bookstopia.aladin.controller;

import lombok.RequiredArgsConstructor;
import me.bookstopia.aladin.dao.AladinService;
import me.bookstopia.aladin.response.AladinSearchResponse;
import me.bookstopia.common.SearchStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/aladin")
public class AladinController {
    private final AladinService aladinService;
    public ResponseEntity<AladinSearchResponse> list(@RequestParam String searchWord) {
        return new ResponseEntity<>(this.aladinService.find(searchWord), HttpStatus.OK);
    }
}
