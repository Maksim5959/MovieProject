package by.tms.controller;

import by.tms.exception.BusinessException;

import by.tms.model.Film;

import by.tms.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@Controller
@Validated
public class FilmController {

    private final FilmService filmService;

    private int page;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    //    @RequestMapping(value = "/", method = RequestMethod.GET)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page) {
        List<Film> films = filmService.allFilms(page);
        int filmsCount = filmService.filmsCount();
        int pagesCount = (filmsCount + 9) / 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }


    @GetMapping("/test/{id}")
    public String testExceptionHandler(@PathVariable("id") @Max(10) int id) {

        if (id > 9) {
            throw new BusinessException("Bad request", id);
        } else if (id < 9 && id > 5) {
            throw new ArithmeticException();
        } else {
            return "testPage";
        }
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") @Valid Film film, BindingResult bindingResult) {
        boolean b = bindingResult.hasErrors();
        ModelAndView modelAndView = new ModelAndView();
        if (filmService.checkTitle(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmService.add(film);
        } else {
            modelAndView.addObject("message", "part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/add");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id,
                                 @ModelAttribute("message") String message) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmService.checkTitle(film.getTitle()) || filmService.getById(film.getId()).getTitle().equals(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmService.edit(film);
        } else {
            modelAndView.addObject("message", "part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/edit/" + +film.getId());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        int filmsCount = filmService.filmsCount();
        int page = ((filmsCount - 1) % 10 == 0 && filmsCount > 10 && this.page == (filmsCount + 9) / 10) ?
                this.page - 1 : this.page;
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("page", page);
        filmService.delete(id);
        return modelAndView;
    }
}
