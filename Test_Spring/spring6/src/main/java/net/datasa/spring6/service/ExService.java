package net.datasa.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring6.domain.dto.CommentDTO;
import net.datasa.spring6.domain.entity.BoardEntity;
import net.datasa.spring6.domain.entity.CommentEntity;
import net.datasa.spring6.repository.BoardRepository;
import net.datasa.spring6.repository.CommentRepository;
import net.datasa.spring6.repository.MemberRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor // final로부터 생성자 주입이 됨 어쨋든 메서드 불러올때 쓰는거임.
public class ExService {

    private final BoardRepository br;
    private final MemberRepository mr;
    private final CommentRepository cr;

    // public int getCount(int i) {
    // BoardEntity entity = br.findById(i)
    // .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));

    // int num = entity.getCnt();
    // return num;
    // }

    // public void setCount(int num, int cnt) {
    // BoardEntity entity = br.findById(num)
    // .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
    // entity.setCnt(cnt);

    // }

    public int readLike(Integer num) {
        BoardEntity entity = br.findById(num).orElseThrow(
                () -> new EntityNotFoundException("회원이 존재하지 않습니다."));
        return entity.getCnt();
    }

    public int likePlus(Integer num) {
        BoardEntity entity = br.findById(num).orElseThrow(
                () -> new EntityNotFoundException("회원이 존재하지 않습니다."));
        int n = entity.getCnt();
        n++;
        entity.setCnt(n);
        return n;
    }

    // public boolean idCheck(String id) {
    // boolean result = mr.existsById(id);
    // return result;
    // }

    public boolean checkId(String id) {
        return mr.existsById(id);
    }

}
