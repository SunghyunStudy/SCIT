package net.datasa.spring4_v2.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring4_v2.domain.dto.GuestbookDTO;
import net.datasa.spring4_v2.domain.entity.GuestbookEntity;
import net.datasa.spring4_v2.domain.entity.GuestbookRecommendEntity;
import net.datasa.spring4_v2.domain.entity.GuestbookRecommendKey;
import net.datasa.spring4_v2.repository.GuestbookRecommendRepository;
import net.datasa.spring4_v2.repository.GuestbookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class GuestbookService {

    private final GuestbookRepository gr;
	private final GuestbookRecommendRepository grr;
	
	/********************************************************************************************************
	 * 저장 테스트
	 */
	public void test() {
        GuestbookEntity entity = GuestbookEntity.builder()
                .name("작성자이름")
                .password("111")
                .message("글 저장 테스트중입니다.")
                .build();
		
		gr.save(entity);

    }

    /********************************************************************************************************
     * 글 저장
     * @param dto 저장할 글 내용
     */
    public void write(GuestbookDTO dto) {
        GuestbookEntity entity = GuestbookEntity.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .message(dto.getMessage())
                .build();
		
		gr.save(entity);
    }

    /********************************************************************************************************
     * 방명록 글을 작성일의 내림차순으로 모두 조회.
     * @return 방명록 글 목록
     */
    public List<GuestbookDTO> getList() {
		
        //정렬 기준
        Sort sort = Sort.by(Sort.Direction.DESC, "inputdate");
		
        List<GuestbookEntity> entityList = gr.findAll(sort);
        List<GuestbookDTO> dtoList = new ArrayList<>();

        for (GuestbookEntity entity : entityList) {
            GuestbookDTO dto = GuestbookDTO.builder()
                    .num(entity.getNum())
                    .name(entity.getName())
//                    .password(entity.getPassword())
                    .message(entity.getMessage())
                    .inputdate(entity.getInputdate())
					.recommendCnt(entity.getRecommendCnt())
                    .build();
            dtoList.add(dto);
        }
		
        return dtoList;
    }

    /********************************************************************************************************
     * 글번호와 비밀번호를 전달받아 글을 삭제.
     * @param num 삭제할 글번호
     * @param password 작성시 입력한 비밀번호
     * @throws EntityNotFoundException 해당 번호의 글이 없을 때
     * @throws RuntimeException 비밀번호가 틀릴 때
     */
    public void delete(Integer num, String password) {
        GuestbookEntity entity = gr.findById(num)
                .orElseThrow(() -> new EntityNotFoundException(num + "번 글이 없습니다."));

        if (!entity.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 틀립니다.");
        }
		
		gr.delete(entity);
    }
	
	/********************************************************************************************************
	 * 글번호와 비밀번호를 전달받아 수정가능한지 체크.
	 * @param num
	 * @param password
	 * @throws EntityNotFoundException 해당 번호의 글이 없을 때
	 * @throws RuntimeException 비밀번호가 틀릴 때
	 */
	public void passwordCheck(Integer num, String password) {
		GuestbookEntity entity = gr.findById(num)
				.orElseThrow(() -> new EntityNotFoundException(num + "번 글이 없습니다."));
		
		if (!entity.getPassword().equals(password)) {
			throw new RuntimeException("비밀번호가 틀립니다.");
		}
	}
	
	/********************************************************************************************************
	 * 게시글 조회
	 * @param num
	 * @return dto 게시글 정보
	 * @throws EntityNotFoundException 해당 번호의 글이 없을 때
	 */
	public GuestbookDTO selectGuestbook(Integer num) {
		GuestbookEntity entity = gr.findById(num)
				.orElseThrow(() -> new EntityNotFoundException(num + "번 글이 없습니다."));
		
		GuestbookDTO dto = GuestbookDTO.builder()
				.num(entity.getNum())
				.name(entity.getName())
				.password(entity.getPassword())
				.message(entity.getMessage())
				.inputdate(entity.getInputdate())
				.build();
		
		return dto;
	}
	
	/********************************************************************************************************
	 * 게시글 수정
	 * @param dto
	 * @throws EntityNotFoundException 해당 번호의 글이 없을 때
	 */
	public void update(GuestbookDTO dto) {
		GuestbookEntity entity = gr.findById(dto.getNum())
				.orElseThrow(() -> new EntityNotFoundException(dto.getNum() + "번 글이 없습니다."));
		
		entity.setPassword(dto.getPassword());
		entity.setMessage(dto.getMessage());
	}
	
	
	/********************************************************************************************************
	 * 추천
	 * @param guestbookNum 추천할 글 번호
	 * @param ip 추천할 사용자의 IP
	 * @throws EntityNotFoundException 해당 번호의 글이 없을 때
	 * @throws RuntimeException 같은 IP로 추천한 적이 있을 때
	 */
	public void recommend(Integer guestbookNum, String ip) {
		
		// 파라미터 객체화
		GuestbookRecommendKey key = new GuestbookRecommendKey(guestbookNum, ip);
		
		// 1. 이미 이 IP로 이 글을 추천했는지 확인
//		boolean exists = grr.existsByIdGuestbookNumAndIdIp(guestbookNum, ip);
		boolean exists = grr.existsById(key);
		
		if (exists) {
			throw new RuntimeException("이미 추천한 글입니다.");
		}
		
		// 2. 추천 이력 저장 (EmbeddedId 사용)
		GuestbookRecommendEntity recommend = GuestbookRecommendEntity.builder()
				.id(key)
				.build();
		
		grr.save(recommend);
		
		// 3. 원글의 추천 수 +1
		GuestbookEntity guest = gr.findById(guestbookNum)
				.orElseThrow(() -> new EntityNotFoundException("글을 찾을 수 없습니다. num=" + guestbookNum));
		
		guest.increaseRecommend();   // JPA 변경감지, 자동 update
	}
	
}
