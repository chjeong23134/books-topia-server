package me.bookstopia.aladin.controller;

import lombok.RequiredArgsConstructor;
import me.bookstopia.aladin.dao.AladinService;
import me.bookstopia.aladin.response.AladinSearchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/aladin")
public class AladinController {
    private final AladinService aladinService;

    @GetMapping
    public ResponseEntity<AladinSearchResponse> search(@RequestParam(name = "keyword") String keyWord) {
        return new ResponseEntity<>(this.aladinService.findByKeyWord(keyWord), HttpStatus.OK);
    }
}
