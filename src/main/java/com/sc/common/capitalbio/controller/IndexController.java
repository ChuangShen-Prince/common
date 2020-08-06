package com.sc.common.capitalbio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/index")
	@ResponseBody
    public String index()
    {
        return "Hello World!";
    }
}
