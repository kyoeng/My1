package service;


import mappers.BoardMapper;
import org.springframework.stereotype.Service;
import vo.BoardVO;
import vo.CommentVO;
import vo.SearchCri;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<BoardVO> selectAll(SearchCri cri) {
        return mapper.selectAll(cri);
    }

    @Override
    public int getTotalData(SearchCri cri) {
        return mapper.getTotalData(cri);
    }

    @Override
    public int regBoard(BoardVO vo) {
        return mapper.regBoard(vo);
    }

    @Override
    public BoardVO selectOne(BoardVO vo) {
        return mapper.selectOne(vo);
    }

    @Override
    public List<CommentVO> getComments(BoardVO vo) {
        return mapper.getComments(vo);
    }

    @Override
    public int regComment(CommentVO vo) {
        return mapper.regComment(vo);
    }
}
