package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 빈으로 스프링 컨테이너에 등록됨
public class BaseController {
    @GetMapping("/")
    public String root() throws Exception{
        throw new Exception("testㅎㅎ");
    }


}
