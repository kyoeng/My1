package com.kjw.my1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.BoardService;
import vo.BoardVO;
import vo.CommentVO;
import vo.PageMaker;
import vo.SearchCri;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }


    /**
     * 게시판으로 가기위한 컨트롤러
     * @param mv ModelAndView
     * @param request HttpServletRequest
     * @param cri SearchCri
     * @param pageMaker PageMaker
     * @return 게시판 페이지
     */
    @GetMapping("/board")
    public ModelAndView toBoard(ModelAndView mv, HttpServletRequest request, SearchCri cri, PageMaker pageMaker) {
        // 검색할 DB 시작번호 설정
        cri.setStartNum();

        // 데이터 조회 후 담기
        mv.addObject("boardList", service.selectAll(cri));

        // 페이징 객체 설정 후 담기
        pageMaker.setCriteria(cri);
        pageMaker.setTotalDataCount(service.getTotalData(cri));
        mv.addObject("pageMaker", pageMaker);

        // 사용자 아이디 넘겨주기
        mv.addObject("id", request.getSession().getAttribute("loginID"));

        mv.setViewName("board/board");
        return mv;
    }

    /**
     * 게시판 글 등록을 위한 컨트롤러
     * @param mv ModelAndView
     * @param vo BoardVO
     * @param rttr RedirectAttributes
     * @return 게시판 페이지로
     */
    @PostMapping("/reg-board")
    public ModelAndView regBoard(ModelAndView mv, BoardVO vo, RedirectAttributes rttr) {
        String uri = "redirect:/board";

        if (service.regBoard(vo) > 0) {
            rttr.addFlashAttribute("message", "등록에 성공하였습니다.");
        } else {
            rttr.addFlashAttribute("message", "등록에 실패하였습니다.");
        }

        mv.setViewName(uri);
        return mv;
    }


    /**
     * 게시글 디테일 페이지를 위한 컨트롤러
     * @param mv ModelAndView
     * @param vo BoardVO
     * @return 게시판 디테일 페이지
     */
    @GetMapping("/detail-board")
    public ModelAndView detailBoard(ModelAndView mv, BoardVO vo) {
        mv.addObject("board", service.selectOne(vo));
        mv.addObject("commnet", service.getComments(vo));
        mv.setViewName("board/board-detail");
        return mv;
    }


    @PostMapping("/reg-comment")
    public ModelAndView regComment(CommentVO vo, ModelAndView mv, HttpServletRequest request) {
        if (request.getSession().getAttribute("loginID").equals(vo.getId())) {
            if (service.regComment(vo) > 0) {
                mv.addObject("code", "200");
            } else {
                mv.addObject("code", "202");
            }
        } else {
            mv.addObject("code", "202");
        }

        mv.setViewName("jsonView");
        return mv;
    }
}
