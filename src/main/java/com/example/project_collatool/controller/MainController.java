package com.example.project_collatool.controller;

import com.example.project_collatool.converter.UserConverter;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.UserDto;
import com.example.project_collatool.dto.request.UserRequestDto;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.service.LoginService;
import com.example.project_collatool.service.MainService;
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
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;
    private final LoginService userService;
    private final UserConverter userConverter;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/")
    public String rootMain(HttpSession session){
//        UserDto user = (UserDto) session.getAttribute("user");
//        if(user != null){
//            return "redirect:/view";
//        }
//        return "redirect:/b_login";
        return "redirect:/main/view";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/view")
    public String mainView(Principal principal, Model model) {
        List<ProjectDto> projectDtoList= mainService.findAll(principal.getName());
        model.addAttribute("projectList",projectDtoList);
        return "main";
    }

    @RequestMapping("/b_login")
    public String beforeLogin(){
        return "before_login";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/user_info")
    public String userInfo(Principal principal, Model model){
        String uId = principal.getName();
        UserDto user = mainService.findUser(uId);
        model.addAttribute("userDto",user);
        return "user/user_view";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/user_modify")
    public String userModify(Principal principal, Model model){
        String uId = principal.getName();
        UserDto user = mainService.findUser(uId);
        model.addAttribute("userDto",user);
        return "user/user_modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/joinProc")
    public String joinProc(@Valid UserRequestDto userDto, Errors errors, HttpSession session, Model model) {

        if (errors.hasErrors()) {// 유효성 통과 못 했을 경우
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userDto", userDto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            log.info("@# message ===>"+model.getAttribute("valid_u_id"));
            /* 회원가입 페이지로 다시 리턴 */
            return "user/user_modify";
        }
        //비밀번호 확인
        if(!userDto.getUPwd().equals(userDto.getUPwdCheck())){
            model.addAttribute("userDto", userDto);
            model.addAttribute("valid_uPwdCheck", "비밀번호를 확인해주세요.");
            return "user/user_modify";
        }
        log.info("@# modify ===>"+userDto);
        mainService.updateUser(userConverter.toDtoModify(userDto));
        log.info("@# modify success=============");
        return "redirect:/main/user_info";
    }

}
