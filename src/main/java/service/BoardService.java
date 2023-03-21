package service;

import vo.BoardVO;
import vo.SearchCri;

import java.util.List;

public interface BoardService {

    /* 게시판 전체 데이터 가져오기 */
    List<BoardVO> selectAll(SearchCri cri);

    /* 게시판 전체 데이터 갯수 조회 */
    int getTotalData(SearchCri cri);

    /* 게시판 글 등록 */
    int regBoard(BoardVO vo);

}
