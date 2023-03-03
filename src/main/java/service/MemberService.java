package service;

import vo.MemberVO;

public interface MemberService {

    MemberVO selectOne(MemberVO vo);	// login을 위한 select method
    int idCheck(String id);             // join 시 id check를 위한 method
    int join(MemberVO vo);			// join을 위한 insert method
    int update(MemberVO vo);            // update를 위한 update method

}
