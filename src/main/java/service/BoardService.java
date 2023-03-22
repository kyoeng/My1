package service;

import vo.BoardVO;
import vo.CommentVO;
import vo.SearchCri;

import java.util.List;

public interface BoardService {

    /* 게시판 전체 데이터 가져오기 */
    List<BoardVO> selectAll(SearchCri cri);

    /* 게시판 전체 데이터 갯수 조회 */
    int getTotalData(SearchCri cri);

    /* 게시판 글 등록 */
    int regBoard(BoardVO vo);

    /* 게시판 디테일 정보 */
    BoardVO selectOne(BoardVO vo);

    /* 댓글 가져오기 */
    List<CommentVO> getComments(BoardVO vo);

    /* 댓글 등록 */
    int regComment(CommentVO vo);

}
