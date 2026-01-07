package net.practice4.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.practice4.domain.entity.GuestBookEntity;

@Service
@Slf4j
@Builder
@Transactional
@RequiredArgsConstructor
public class GuestBookService {

    public void test() {
        GuestBookEntity entity = GuestBookEntity.builder()
                .name("작성자")
                .password("111")
                .message("글 저장 테스트 중")
                .bulid();
    }
}
