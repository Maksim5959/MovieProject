package by.tms.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleBusinessException(BusinessException businessException){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("businessException", businessException);
        modelAndView.setViewName("testPage");
        return modelAndView;
    }

    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView handleArithmeticException (ArithmeticException arithmeticException) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("arithmeticException" , arithmeticException);
        modelAndView.setViewName("testPage");
        return modelAndView;
    }
}
