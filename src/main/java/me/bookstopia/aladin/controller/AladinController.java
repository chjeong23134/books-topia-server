package me.bookstopia.aladin.controller;

import lombok.RequiredArgsConstructor;
import me.bookstopia.aladin.dao.AladinService;
import me.bookstopia.aladin.dto.BookDto;
import me.bookstopia.aladin.enumeration.AladinListTypeEnum;
import me.bookstopia.aladin.response.AladinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/aladin")
public class AladinController {
    private final AladinService aladinService;

    @GetMapping("/search")
    public ResponseEntity<AladinResponse> search(@RequestParam String keyword) {
        return new ResponseEntity<>(this.aladinService.findByKeyWord(keyword), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<AladinResponse> list(@RequestParam AladinListTypeEnum type) {
        return new ResponseEntity<>(this.aladinService.findByType(type), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<AladinResponse> detail(@PathVariable Long id) {
        return new ResponseEntity<>(this.aladinService.findById(id), HttpStatus.OK);
    }
}
