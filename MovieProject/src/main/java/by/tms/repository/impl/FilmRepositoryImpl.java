package by.tms.repository.impl;

import by.tms.model.Film;
import by.tms.repository.FilmRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public FilmRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Film> allFilms(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Film",Film.class).setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(film);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Film film = session.get(Film.class, id);
        session.delete(film);
    }

    @Override
    public void edit(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
    }

    @Override
    public Film getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
    }

    @Override
    public int filmsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Film", Number.class).getSingleResult().intValue();
    }

    @Override
    public boolean checkTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Film where title = :title");
        query.setParameter("title", title);
        return query.list().isEmpty();
    }
}
