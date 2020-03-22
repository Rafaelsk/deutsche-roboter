package org.zeitgeistonline.deutschesiri;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Endpoints {
    @GetMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!!";
    }
}
