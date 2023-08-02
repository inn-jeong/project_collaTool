package com.example.project_collatool.controller;

import com.example.project_collatool.converter.UserConverter;
import com.example.project_collatool.dto.UserDto;
import com.example.project_collatool.dto.request.UserRequestDto;
import com.example.project_collatool.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;
    private final UserConverter convert;

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/view")
    public String loginPage(HttpServletRequest request, Model model){
        String login_try = request.getParameter("login_try");
        String register = request.getParameter("register");
        if (login_try != null) {
            if (login_try.equals("yes")) {
                log.info("@# login_try===>"+login_try);
                model.addAttribute("login_try", "yes");
            }else if (login_try.equals("no")){
                model.addAttribute("login_try", "no");
            }
        }
        if (register != null && register.equals("ok")) {
            log.info("@# register===>"+register);
            model.addAttribute("register", "ok");
        }
        return "user/login";
    }

//    @PostMapping("/process")
//    public String loginProcess(UserDto user){
//        log.info("@# u_id ===>"+user.getUId());
//        log.info("@# u_pwd ===>"+user.getUPwd());
//        int result = service.loginCheck(user);
//        log.info("@#  result ====>"+result);
//        if(result == 1){
//
//            return "redirect:/main";
//        }
//        return "redirect:/login/view";
//    }

    @GetMapping("/register_page")
    public String registerPage(Model model){
        UserRequestDto requestDto = new UserRequestDto();
        model.addAttribute("userDto",requestDto);

        return "user/user_register";
    }

    @PostMapping("/joinProc")
    public String joinProc(@Valid UserRequestDto userDto, Errors errors, HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (errors.hasErrors()) {// 유효성 통과 못 했을 경우
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userDto", userDto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = service.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            log.info("@# message ===>"+model.getAttribute("valid_u_id"));
            /* 회원가입 페이지로 다시 리턴 */
            return "user/user_register";
        }
        //비밀번호 확인
        if(!userDto.getUPwd().equals(userDto.getUPwdCheck())){
            model.addAttribute("userDto", userDto);
            model.addAttribute("valid_uPwdCheck", "비밀번호를 확인해주세요.");
            return "user/user_register";
        }
        log.info("@# register ===>"+userDto);
//        service.userInsert(convert.toDto(userDto));
        service.create(convert.toDto(userDto));
        log.info("@# register success=============");
        return "redirect:/login/view?register=ok";
    }

    @RequestMapping("/findId_page")
    public String findId_page(HttpServletRequest request, Model model){

        return "user/findId";
    }

}
