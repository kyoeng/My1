package com.kjw.my1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // 오늘 날짜 가져오기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        // todo_dispo, todo_every 테이블에서 현재 날짜와 맞는 데이터 받아오기 ( 구현해야 함 )

        // 오늘 날짜 넘기기
        model.addAttribute("today", dateFormat.format(date));


        return "index";
    }

}
