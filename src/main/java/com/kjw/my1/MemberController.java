package com.kjw.my1;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.MemberService;
import vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@Controller
public class MemberController {

    // service 객체 참조 변수
    private final MemberService service;
    // password 암호화를 위한 객체 참조 변수
    private final PasswordEncoder passwordEncoder;


    // 생성자
    @Autowired
    public MemberController(MemberService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }


    // 로그인 관련 Method -------------------------------------
    @GetMapping(value = "/login")
    public ModelAndView loginF(ModelAndView mv) {

        mv.setViewName("/member/login");
        return mv;

    } // loginF()


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(ModelAndView mv, MemberVO vo, HttpServletRequest request) {

        String uri = "/member/login";
        String inputPw = vo.getPassword();
        vo = service.selectOne(vo);

        if(vo != null) {
            // 해당 아이디가 있는 경우
            if(passwordEncoder.matches(inputPw, vo.getPassword())) {
                // 패스워드도 일치하는 경우
                uri = "redirect:/";
                request.getSession().setAttribute("loginID", vo.getId());
                request.getSession().setAttribute("myImg", vo.getUpload_image());
            } else {
                // 패스워드는 일치하지 않는 경우
                mv.addObject("message", "확인 후 다시 시도해주세요.");
            }
        } else {
            // 아이디가 없는 경우
            mv.addObject("message", "확인 후 다시 시도해주세요.");
        }

        mv.setViewName(uri);
        return mv;

    } // login()
    // --------------------------------------------------


    // 회원가입 관련 Method -----------------------------

    // joinForm 으로 이동을 위한 method
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public ModelAndView joinF(ModelAndView mv) {

        mv.setViewName("/member/joinForm");
        return mv;

    } // joinF()


    // joinForm 에서 id check를 위한 method
    @GetMapping("/idcheck")
    public ModelAndView idCheck(ModelAndView mv, @RequestParam("id") String id) {
        if(service.idCheck(id) < 1) {
            mv.addObject("code", "200");
        } else {
            mv.addObject("code", "201");
        }

        mv.setViewName("jsonView");
        return mv;
    }


    // 최종 회원가입을 위한 method
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ModelAndView join(ModelAndView mv, MemberVO vo,
                             HttpServletRequest request, RedirectAttributes rttr) throws IOException {

        // => 개발환경 (배포전) => C:\DevJ\workspace\my1\out\artifacts\my1_war_exploded
        // => 톰캣서버에 배포 후 : 서버내에서의 위치가 됨 => C:\DevJ\IDE\apache-tomcat-9.0.70-windows-x64\apache-tomcat-9.0.70\webapps\프로젝트명\
        String uploadPath = request.getSession().getServletContext().getRealPath("/");

        if(uploadPath.contains("workspace")) {
            uploadPath = "C:\\DevJ\\workspace\\my1\\out\\artifacts\\my1_war_exploded\\resources\\uploadImgs";
        } else {
            uploadPath += "resources\\uploadImgs";
        }

        File file = new File(uploadPath);
        if(!file.exists()) file.mkdir();	// 해당 폴더 없을 시 폴더 생성

        String fileName1 = "";									// 실제 저장할 절대 경로 지정 ( 업로드 없을 시를 위한 공백 처리 )
        String fileName2 = "resources/uploadImgs/user.png";		// 업로드 파일 없을 시 default 파일 미리 지정 ( DB 저장 용도 )

        MultipartFile uploadF = vo.getUpload_imageF();

        if(uploadF != null && !uploadF.isEmpty()) {
            // 업로드 파일이 있을 시
            fileName1 = uploadPath + "/" + uploadF.getOriginalFilename();
            uploadF.transferTo(new File(fileName1));

            fileName2 = "resources/uploadImgs/" + uploadF.getOriginalFilename();
        }

        vo.setUpload_image(fileName2);
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        String uri = "redirect:login";

        // insert method의 return값이 성공 시 -2147482646으로 나옴
        if(service.insert(vo) < 0) {
            rttr.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인 후 이용하세요.");
        } else {
            uri = "redirect:join";
            rttr.addFlashAttribute("message", "회원가입에 실패하였습니다. 다시 시도해주세요.");
        }

        mv.setViewName(uri);
        return mv;

    } // join()
    // --------------------------------------------------

}

