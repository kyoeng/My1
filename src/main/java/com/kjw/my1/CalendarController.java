package com.kjw.my1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vo.DateVO;

import java.util.*;

@Slf4j
@Controller
public class CalendarController {

    @RequestMapping(value = "/month-view")
    public ModelAndView monthView(ModelAndView mv, DateVO vo) {

        // 현재 날짜 시간 데이터 생성
        Calendar calendar = Calendar.getInstance();
        // 캘린더의 빈칸을 위한 DateVO Type
        DateVO calendarDate;

        // vo에 전달된 데이터가 없는 경우 현재의 날짜와 시간으로 재생성
        if(vo.getDate().equals("") && vo.getMonth().equals("")) {
            vo = new DateVO(String.valueOf(calendar.get(Calendar.YEAR)),
                    String.valueOf(calendar.get(Calendar.MONTH)),
                    String.valueOf(calendar.get(Calendar.DATE)), null);
        }

        // 년, 월, 요일 등의 데이터를 위한 Map 변수
        Map<String, Integer> todayInfo = vo.todayInfo(vo);
        // return 을 위한 DateVO 타입의 List
        List<DateVO> dateList = new ArrayList<>();

        // 1일에 해당하는 요일 전까지 빈 데이터 생성 후 List 에 add
        for(int i = 1; i < todayInfo.get("start"); i++) {
            calendarDate = new DateVO(null, null, null, null);
            dateList.add(calendarDate);
        }

        // 1일부터 말일까지 데이터 삽입
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

        // 말일 후의 빈 데이터 삽입을 위한 계산
        int index = 7 - dateList.size() % 7;

        // 말일 후의 캘린더를 위한 빈 데이터 삽입
        if((dateList.size() % 7) != 0) {
            for(int i = 0; i < index; i++) {
                calendarDate = new DateVO(null, null, null, null);
                dateList.add(calendarDate);
            }
        }

        mv.addObject("dateList", dateList);
        mv.addObject("todayInfo", todayInfo);

        mv.setViewName("calendar/month-view");
        return mv;
    }

}
