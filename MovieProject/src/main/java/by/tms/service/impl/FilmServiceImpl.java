package by.tms.service.impl;

import by.tms.model.Film;
import by.tms.repository.FilmRepository;
import by.tms.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> allFilms(int page) {
        return filmRepository.allFilms(page);
    }

    @Override
    public void add(Film film) {
        filmRepository.add(film);
    }

    @Override
    public void delete(int id) {
        filmRepository.delete(id);
    }

    @Override
    public void edit(Film film) {
        filmRepository.edit(film);
    }

    @Override
    public Film getById(int id) {
        return filmRepository.getById(id);
    }

    public int filmsCount() {
        return filmRepository.filmsCount();
    }

    @Override
    public boolean checkTitle(String title) {
        return filmRepository.checkTitle(title);
    }
}
