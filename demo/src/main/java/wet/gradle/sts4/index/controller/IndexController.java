package wet.gradle.sts4.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {

	@GetMapping("/vue")
    public String vue(){

        return "index.html";
    }
}
