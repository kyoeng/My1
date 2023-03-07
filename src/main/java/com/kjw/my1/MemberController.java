package com.kjw.my1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.MemberService;
import vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@Slf4j
@Controller
public class MemberController {

    // 필드
    private final MemberService service;
    private final PasswordEncoder passwordEncoder;


    // 생성자
    @Autowired
    public MemberController(MemberService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * 로그인 화면으로 향하기 위한 컨트롤러
     *
     * @param mv Model 객체
     * @return login.jsp로 포워딩
     */
    @GetMapping(value = "/login")
    public ModelAndView loginF(ModelAndView mv) {
        mv.setViewName("/member/login");
        return mv;
    } // loginF


    /**
     * 로그인을 위한 컨트롤러
     *
     * @param mv      Model 객체
     * @param vo      Member VO
     * @param request HttpServletRequest
     * @return 성공 : 세션에 등록 | 실패 : 실패 메시지
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(ModelAndView mv, MemberVO vo, HttpServletRequest request) {
        String uri = "/member/login";
        String inputPw = vo.getPassword();
        vo = service.selectOne(vo);

        if (vo != null) {
            // 해당 아이디가 있는 경우
            if (passwordEncoder.matches(inputPw, vo.getPassword())) {
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
    } // login


    /**
     * 회원가입 화면으로 향하기 위한 컨트롤러
     *
     * @param mv ModelAndView 객체
     * @return joinForm.jsp로 포워딩
     */
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public ModelAndView joinF(ModelAndView mv) {
        mv.setViewName("/member/joinForm");
        return mv;
    } // joinF

    /**
     * 회원가입 시 아이디 중복 체크를 위한 컨트롤러
     *
     * @param mv ModelAndView 객체
     * @param id 입력받은 ID
     * @return 중복 없은 경우 : code에 200 | 중복이 있는 경우 : code에 202
     */
    @GetMapping("/idcheck")
    public ModelAndView idCheck(ModelAndView mv, @RequestParam("id") String id) {
        if (service.idCheck(id) < 1) {
            mv.addObject("code", "200");
        } else {
            mv.addObject("code", "202");
        }

        mv.setViewName("jsonView");
        return mv;
    }

    /**
     * 회원가입을 위한 컨트롤러
     *
     * @param mv      ModelAndView
     * @param vo      Member VO
     * @param request HttpServletRequest
     * @param rttr    RedirectAttributes
     * @return 성공 : 성공메시지, 로그인 화면으로 | 실패 : 실패메시지, 회원가입 초기 화면으로
     * @throws IOException
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ModelAndView join(ModelAndView mv, MemberVO vo, HttpServletRequest request, RedirectAttributes rttr) throws IOException {
        // => 개발환경 (배포전) => C:\DevJ\workspace\my1\out\artifacts\my1_war_exploded
        // => 톰캣서버에 배포 후 : 서버내에서의 위치가 됨 => C:\DevJ\IDE\apache-tomcat-9.0.70-windows-x64\apache-tomcat-9.0.70\webapps\프로젝트명\
        String uploadPath = request.getSession().getServletContext().getRealPath("/");

        if (uploadPath.contains("workspace")) {
            uploadPath = "C:\\DevJ\\workspace\\my1\\out\\artifacts\\my1_war_exploded\\resources\\uploadImgs";
        } else {
            uploadPath += "resources\\uploadImgs";
        }

        File file = new File(uploadPath);
        if (!file.exists()) file.mkdir();    // 해당 폴더 없을 시 폴더 생성

        String fileName1 = "";                                    // 실제 저장할 절대 경로 지정 ( 업로드 없을 시를 위한 공백 처리 )
        String fileName2 = "resources/uploadImgs/user.png";        // 업로드 파일 없을 시 default 파일 미리 지정 ( DB 저장 용도 )

        MultipartFile uploadF = vo.getUpload_imageF();

        if (uploadF != null && !uploadF.isEmpty()) {
            // 업로드 파일이 있을 시
            fileName1 = uploadPath + "/" + uploadF.getOriginalFilename();
            uploadF.transferTo(new File(fileName1));

            fileName2 = "resources/uploadImgs/" + uploadF.getOriginalFilename();
        }

        vo.setUpload_image(fileName2);
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        String uri = "redirect:login";

        if (service.join(vo) > 0) {
            rttr.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인 후 이용하세요.");
        } else {
            uri = "redirect:join";
            rttr.addFlashAttribute("message", "회원가입에 실패하였습니다. 다시 시도해주세요.");
        }

        mv.setViewName(uri);
        return mv;
    } // join


    /**
     * 로그아웃을 위한 컨트롤러
     *
     * @param mv      ModelAndView
     * @param request HttpServletRequest
     * @return login컨르롤러로 리다이렉트
     */
    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView mv, HttpServletRequest request) {
        request.getSession().invalidate();
        String uri = "redirect:login";
        mv.setViewName(uri);
        return mv;
    }


    /**
     * 내정보 보기로 향하기 위한 컨트롤러
     *
     * @param mv      ModelAndView
     * @param vo      Member VO
     * @param request HttpServletRequest
     * @return myInfo.jsp로 포워딩
     */
    @GetMapping("/my-info")
    public ModelAndView myInfo(ModelAndView mv, MemberVO vo, HttpServletRequest request) {
        vo.setId((String) request.getSession().getAttribute("loginID"));
        mv.addObject("myInfo", service.selectOne(vo));
        mv.setViewName("/member/myInfo");
        return mv;
    }


    /**
     * 회원 정보 수정을 위한 컨트롤러
     *
     * @param mv      ModelAndView
     * @param vo      Member VO
     * @param request HttpServletRequest
     * @param rttr    RedirectAttributes
     * @return
     */
    @PostMapping("/update/my-info")
    public ModelAndView updateMyInfo(ModelAndView mv, MemberVO vo, HttpServletRequest request, RedirectAttributes rttr) throws IOException {
        MultipartFile uploadF = vo.getUpload_imageF();

        // 수정할 이미지를 올린 경우
        if (uploadF != null && !uploadF.isEmpty()) {
            // => 개발환경 (배포전) => C:\DevJ\workspace\my1\out\artifacts\my1_war_exploded
            // => 톰캣서버에 배포 후 : 서버내에서의 위치가 됨 => C:\DevJ\IDE\apache-tomcat-9.0.70-windows-x64\apache-tomcat-9.0.70\webapps\프로젝트명\
            String uploadPath = request.getSession().getServletContext().getRealPath("/");

            if (uploadPath.contains("workspace")) {
                uploadPath = "C:\\DevJ\\workspace\\my1\\out\\artifacts\\my1_war_exploded\\resources\\uploadImgs";
            } else {
                uploadPath += "resources\\uploadImgs";
            }

            String fileName1 = uploadPath + "/" + uploadF.getOriginalFilename();            // 실제 저장할 절대 경로 지정
            String fileName2 = "resources/uploadImgs/" + uploadF.getOriginalFilename();        // DB에 저장할 경로 만들기

            uploadF.transferTo(new File(fileName1));

            vo.setUpload_image(fileName2);


            // 수정할 이미지를 올리지 않은 경우
        } else {
            MemberVO vo2 = new MemberVO();
            vo2.setId(vo.getId());
            vo2 = service.selectOne(vo2);

            vo.setUpload_image(vo2.getUpload_image());
        }

        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        String uri = "redirect:/";

        if (service.update(vo) > 0) {
            rttr.addFlashAttribute("message", "수정이 완료되었습니다.");
            request.getSession().setAttribute("myImg", service.selectOne(vo).getUpload_image());
        } else {
            uri = "redirect:my-info";
            rttr.addFlashAttribute("message", "수정에 실패하였습니다.");
        }

        mv.setViewName(uri);
        return mv;
    }


    @PostMapping("del-member")
    public ModelAndView deleteMember(MemberVO vo, HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
        if (service.delete(vo) > 0) {
            request.getSession().invalidate();
            mv.addObject("code", 200);
        } else {
            mv.addObject("code", 202);
        }

        mv.setViewName("jsonView");
        return mv;
    }
}

