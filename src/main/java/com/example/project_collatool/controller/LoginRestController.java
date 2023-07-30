package com.example.project_collatool.controller;

import com.example.project_collatool.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginRest")
public class LoginRestController {
    private final LoginService service;

    @PostMapping("/userCheck")
    public ResponseEntity<String> userCheck(@RequestParam("uId") String uId){
        log.info("@# userCheck uId ===>" +uId);
        int re = service.userCheck(uId);
        if(re == 1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
