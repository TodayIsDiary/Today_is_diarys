package com.example.today_is_diarys.service.good;

import com.example.today_is_diarys.dto.good.GoodDto;
import com.example.today_is_diarys.entity.good.Good;
import com.example.today_is_diarys.entity.user.User;
import com.example.today_is_diarys.error.exception.BeargameException;
import com.example.today_is_diarys.repository.good.GoodRepository;
import com.example.today_is_diarys.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodService {

    private final GoodRepository goodRepository;
    private final UserRepository userRepository;

    public void good(GoodDto dto){

        //이미 좋아요 되면 409에러
        if(findGoodByUserAndPostId(dto).isPresent())
            throw new IllegalStateException("ALREADY_GOOD");

        Good good = Good.builder()
                .postId(dto.getPostId())
                .user(userRepository.findUserById(dto.getUserId()).get())
                .build();
        goodRepository.save(good);
    }

    public void unGood(GoodDto dto){
        Optional<Good> good = findGoodByUserAndPostId(dto);

        if (good.isEmpty())
            throw new IllegalStateException("GOOD_NOT_FOUND");

        goodRepository.delete(good.get());
    }

    private Optional<Good> findGoodByUserAndPostId(GoodDto dto) {
        Optional<User> good = userRepository.findUserById(dto.getUserId());
        if (good.isEmpty())
            throw new UsernameNotFoundException("MEMBER_NOT_FOUND");

        return goodRepository.findGoodByUserAndPostId(good.get(), dto.getPostId());
    }
}
