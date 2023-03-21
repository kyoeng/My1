package service;


import mappers.BoardMapper;
import org.springframework.stereotype.Service;
import vo.BoardVO;
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
}
