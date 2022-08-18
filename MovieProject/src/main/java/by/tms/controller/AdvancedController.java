package by.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdvancedController {


    @GetMapping("/test1")
    public String testExceptionHandler1() {
        throw new ArithmeticException();
    }
    @GetMapping("/test2")
    public String testExceptionHandler2() {
        throw new ArithmeticException();
    }
    @GetMapping("/test3")
    public String testExceptionHandler3() {
        throw new ArithmeticException();
    }
}
