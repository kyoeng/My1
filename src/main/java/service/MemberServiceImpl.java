package service;

import mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

    // 필드
    private final MemberMapper mapper;

    // 생성자
    public MemberServiceImpl(MemberMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 로그인, 내정보를 위한 메서드
     * @param vo 회원 VO
     * @return 해당 회원의 정보
     */
    @Override
    public MemberVO selectOne(MemberVO vo) {
        return mapper.selectOne(vo);
    }

    /**
     * 회원가입 시 아이디 체크를 위한 메서드
     * @param id 입력받은 ID
     * @return 중복인 경우 : 1 | 중복이 아닌 경우 : 0
     */
    @Override
    public int idCheck(String id) { return mapper.idCheck(id); }

    /**
     * 회원가입을 위한 메서드
     * @param vo 회원 VO
     * @return 성공 : 1 | 실패 : 0
     */
    @Override
    public int join(MemberVO vo) {
        return mapper.join(vo);
    }

    /**
     * 내정보 수정을 위한 메서드
     * @param vo 회원 VO
     * @return 성공 : 1 | 실패 : 0
     */
    @Override
    public int update(MemberVO vo) {
        return mapper.update(vo);
    }
}
