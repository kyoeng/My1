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

        // ���� ��¥ �ð� ������ ����
        Calendar calendar = Calendar.getInstance();
        // Ķ������ ��ĭ�� ���� DateVO Type
        DateVO calendarDate;

        // vo�� ���޵� �����Ͱ� ���� ��� ������ ��¥�� �ð����� �����
        if(vo.getDate().equals("") && vo.getMonth().equals("")) {
            vo = new DateVO(String.valueOf(calendar.get(Calendar.YEAR)),
                    String.valueOf(calendar.get(Calendar.MONTH)),
                    String.valueOf(calendar.get(Calendar.DATE)), null);
        }

        // ��, ��, ���� ���� �����͸� ���� Map ����
        Map<String, Integer> todayInfo = vo.todayInfo(vo);
        // return �� ���� DateVO Ÿ���� List
        List<DateVO> dateList = new ArrayList<>();

        // 1�Ͽ� �ش��ϴ� ���� ������ �� ������ ���� �� List �� add
        for(int i = 1; i < todayInfo.get("start"); i++) {
            calendarDate = new DateVO(null, null, null, null);
            dateList.add(calendarDate);
        }

        // 1�Ϻ��� ���ϱ��� ������ ����
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

        // ���� ���� �� ������ ������ ���� ���
        int index = 7 - dateList.size() % 7;

        // ���� ���� Ķ������ ���� �� ������ ����
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
