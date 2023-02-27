package service;

import mappers.MemberMapper;
import org.springframework.stereotype.Service;
import vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper MAPPER;

    public MemberServiceImpl(MemberMapper mapper) {
        this.MAPPER = mapper;
    }

    // 로그인을 위한 method
    @Override
    public MemberVO selectOne(MemberVO vo) {
        return MAPPER.selectOne(vo);
    }

    // 회원가입 id check를 위한 method
    @Override
    public int idCheck(String id) { return MAPPER.idCheck(id); }

    // 회원가입을 위한 method
    @Override
    public int insert(MemberVO vo) {
        return MAPPER.insert(vo);
    }

}
