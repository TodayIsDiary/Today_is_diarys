package com.example.today_is_diarys.controller.good;

import com.example.today_is_diarys.dto.good.GoodDto;
import com.example.today_is_diarys.service.good.GoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/good")
public class GoodController {

    private final GoodService goodService;

    @PostMapping
    public ResponseEntity<GoodDto> heart(@RequestBody GoodDto dto) {
        goodService.good(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<GoodDto> unHeart(@RequestBody GoodDto dto) {
        goodService.unGood(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
