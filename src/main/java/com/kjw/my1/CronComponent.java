package com.kjw.my1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.CalendarService;
import vo.ScheduleVO;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CronComponent {

    // 필드
    private final CalendarService service;
    private ScheduleVO vo = new ScheduleVO();

    // 생성자
    public CronComponent(CalendarService service) {
        this.service = service;
    }


    @Scheduled(cron = "0 59 23 * * ?")
    public void test() {
        // 오늘 날짜 가져오기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        vo.setTodo_date(dateFormat.format(date));

        if (service.deleteCron(vo) > 0) {
            System.out.println("cron 실행");
        } else {
            System.out.println("cron 에러");
        }
    }

}
