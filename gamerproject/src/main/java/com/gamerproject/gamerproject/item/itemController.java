package com.gamerproject.gamerproject.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class itemController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
