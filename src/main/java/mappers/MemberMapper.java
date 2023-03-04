package mappers;

import vo.MemberVO;



public interface MemberMapper {

    // login을 위한 select method
    MemberVO selectOne(MemberVO vo);

    // join 시 id check를 위한 method
    int idCheck(String id);

    // join을 위한 insert method
    int join(MemberVO vo);

    // update를 위한 update method
    int update(MemberVO vo);

    // delete를 위한 method
    int delete(MemberVO vo);

}
