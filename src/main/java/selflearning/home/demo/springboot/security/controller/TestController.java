package selflearning.home.demo.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/welcome/hello")
    public String Welcome(){
        return "Welcome to my page";
    }
}
