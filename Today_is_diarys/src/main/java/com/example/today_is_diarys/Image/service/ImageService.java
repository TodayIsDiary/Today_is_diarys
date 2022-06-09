package com.example.today_is_diarys.Image.service;

import com.example.today_is_diarys.Image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

}
