package mappers;

import vo.MemberVO;

public interface MemberMapper {

    MemberVO selectOne(MemberVO vo);	// login을 위한 select method
    int idCheck(String id);             // join 시 id check를 위한 method
    int insert(MemberVO vo);			// join을 위한 insert method

}
