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

    // �α����� ���� method
    @Override
    public MemberVO selectOne(MemberVO vo) {
        return MAPPER.selectOne(vo);
    }

    // ȸ������ id check�� ���� method
    @Override
    public int idCheck(String id) { return MAPPER.idCheck(id); }

    // ȸ�������� ���� method
    @Override
    public int insert(MemberVO vo) {
        return MAPPER.insert(vo);
    }

}
