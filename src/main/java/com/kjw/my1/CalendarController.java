package com.kjw.my1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CalendarService;
import vo.DateVO;
import vo.ScheduleVO;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class CalendarController {

    // 필드
    private final CalendarService service;

    // 생성자
    public CalendarController(CalendarService service) {
        this.service = service;
    }


    /**
     * 캘린더 페이지를 위한 컨트롤러
     * @param mv ModelAndView
     * @param vo DateVO
     * @param scheVo ScheduleVO
     * @return 캘린더 페이지
     */
    @RequestMapping(value = "/month-view")
    public ModelAndView monthView(ModelAndView mv, DateVO vo, ScheduleVO scheVo, HttpServletRequest request) {

        Calendar calendar = Calendar.getInstance();
        DateVO calendarDate;

        if(vo.getDate().equals("") && vo.getMonth().equals("")) {
            vo = new DateVO(String.valueOf(calendar.get(Calendar.YEAR)),
                    String.valueOf(calendar.get(Calendar.MONTH)),
                    String.valueOf(calendar.get(Calendar.DATE)), null);
        }

        Map<String, Integer> todayInfo = vo.todayInfo(vo);
        List<DateVO> dateList = new ArrayList<>();

        for(int i = 1; i < todayInfo.get("start"); i++) {
            calendarDate = new DateVO(null, null, null, null);
            dateList.add(calendarDate);
        }

        for(int i = todayInfo.get("startDate"); i <= todayInfo.get("endDate"); i++) {
            if(i == todayInfo.get("today")) {
                calendarDate = new DateVO(String.valueOf(vo.getYear()),
                        String.valueOf(vo.getMonth()),
                        String.valueOf(i), "today");
            } else {
                calendarDate = new DateVO(String.valueOf(vo.getYear()),
                        String.valueOf(vo.getMonth()),
                        String.valueOf(i), "normalDay");
            }

            dateList.add(calendarDate);
        }

        int index = 7 - dateList.size() % 7;

        if((dateList.size() % 7) != 0) {
            for(int i = 0; i < index; i++) {
                calendarDate = new DateVO(null, null, null, null);
                dateList.add(calendarDate);
            }
        }

        mv.addObject("dateList", dateList);
        mv.addObject("todayInfo", todayInfo);

        if (vo.getYear() != null && !vo.getYear().equals("")) {
            String yyyy = vo.getYear();
            int mm = Integer.parseInt(vo.getMonth());
            String calcM = mm < 9 ? "0" + (mm + 1) : "" + (mm + 1) ;
            scheVo.setTodo_date(yyyy + "-" + calcM);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date date = new Date();
            scheVo.setTodo_date(simpleDateFormat.format(date));
        }
        scheVo.setId((String)request.getSession().getAttribute("loginID"));
        mv.addObject("dispo", service.getMonthDataD(scheVo));
        mv.addObject("every", service.getMonthDataE(scheVo));

        mv.setViewName("calendar/month-view");
        return mv;
    }


    /**
     * 일정 상세 페이지를 위한 컨트롤러
     * @param vo ScheduleVO
     * @param mv ModelAndView
     * @param request HttpServletRequest
     * @return 일정 상세 페이지
     */
    @GetMapping("/info-todo")
    public ModelAndView infoTodo(ScheduleVO vo, ModelAndView mv, HttpServletRequest request) {
        vo.setId((String)request.getSession().getAttribute("loginID"));

        mv.addObject("date", vo.getTodo_date());
        mv.addObject("dispo", service.getDetailDataD(vo));
        mv.addObject("every", service.getDetailDataE(vo));

        mv.setViewName("calendar/infoTodo");
        return mv;
    }


    /**
     * 일정 등록을 위한 컨트롤러
     * @param vo ScheduleVO
     * @param mv ModelAndView
     * @param request HttpServletRequest
     * @param rttr RedirectAttributes
     * @param check_eve RequestParam-check_eve
     * @return 해당 페이지로 리다이렉팅
     */
    @PostMapping("/reg_todo")
    public ModelAndView regTodo(ScheduleVO vo, ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr,
                                @RequestParam(value = "check_eve", required = false) String check_eve) {
        String uri;
        vo.setId((String)request.getSession().getAttribute("loginID"));

        if (check_eve != null && check_eve.length() > 0) {
            if (service.insertE(vo) > 0) {
                rttr.addFlashAttribute("message", "등록이 완료되었습니다.");
            } else {
                rttr.addFlashAttribute("message", "등록에 실패했습니다.");
            }
        } else {
            if (service.insertD(vo) > 0) {
                rttr.addFlashAttribute("message", "등록이 완료되었습니다.");
            } else {
                rttr.addFlashAttribute("message", "등록에 실패했습니다.");
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        if (vo.getTodo_date().equals(dateFormat.format(date))) {
            uri = "redirect:/";
        } else {
            rttr.addAttribute("todo_date", vo.getTodo_date());
            uri = "redirect:/info-todo";
        }

        mv.setViewName(uri);
        return mv;
    }


    /**
     * 일정 삭제를 위한 컨트톨러
     * @param vo ScheduleVO
     * @param check String check
     * @return 성공 시 200, 실패 시 500
     */
    @PostMapping("/del-todo")
    public ResponseEntity<?> deleteTodo(ScheduleVO vo, @RequestParam(value = "check", required = false) String check) {
        if (check != null && check.length() > 0) {
            /* 매년 반복 일정 삭제 */
            if (service.deleteE(vo) > 0) {
                return ResponseEntity.status(HttpStatus.OK).body("성공");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
            }
        } else {
            /* 반복 아닌 일정 삭제 */
            if (service.deleteD(vo) > 0) {
                return ResponseEntity.status(HttpStatus.OK).body("성공");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
            }
        }
    }

}
