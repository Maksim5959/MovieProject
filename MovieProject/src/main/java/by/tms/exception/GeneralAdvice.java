package by.tms.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GeneralAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleBusinessException(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception.getClass().getSimpleName());
        modelAndView.setViewName("testPage");
        return modelAndView;
    }
}
