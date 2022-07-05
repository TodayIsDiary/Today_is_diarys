package com.example.today_is_diarys.repository.good;

import com.example.today_is_diarys.entity.good.Good;
import com.example.today_is_diarys.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {

    Optional<Good> findGoodByUserAndPostId(User user, Long postId);
}
