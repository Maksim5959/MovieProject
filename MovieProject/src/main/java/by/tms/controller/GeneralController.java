package by.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Size;

@Controller
@RequestMapping("/general")
@Validated
public class GeneralController {

    @GetMapping
    public String testExceptionHandler() {
        throw new ArithmeticException();
    }

    @GetMapping("/test1")
    public ModelAndView testExceptionHandler1(@RequestParam("name") @Size(max = 5) String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email", email);
        modelAndView.setViewName("testPage");
        return modelAndView;

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
