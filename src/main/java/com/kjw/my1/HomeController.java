package com.kjw.my1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CalendarService;
import vo.ScheduleVO;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Controller
public class HomeController {

    // 필드
    private final CalendarService calendarService;

    // 생성자
    public HomeController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    /**
     * 메인 페이지를 위한 컨트롤러
     * @param model Model
     * @param request HttpServletRequest
     * @param vo ScheduleVO
     * @return 메인 페이지
     */
    @GetMapping("/")
    public String home(Model model, ScheduleVO vo, HttpServletRequest request,
                       @RequestParam(value = "message", required = false) String message) {
        // 오늘 날짜 가져오기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        // todo_dispo, todo_every 테이블에서 현재 날짜와 맞는 데이터 받아오기 ( 구현해야 함 )
        vo.setId((String)request.getSession().getAttribute("loginID"));
        vo.setTodo_date(dateFormat.format(date));
        model.addAttribute("dispo", calendarService.getDetailDataD(vo));
        model.addAttribute("every", calendarService.getDetailDataE(vo));

        // 오늘 날짜 넘기기
        model.addAttribute("today", dateFormat.format(date));

        // 메시지 넣기
        model.addAttribute("message", message);

        return "index";
    }

}
