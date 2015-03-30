package fr.cours.isima.business;

import java.util.List;
import java.util.stream.Collectors;

import fr.cours.isima.persistence.GenreDao;


public class Genres {
    private final GenreDao genreDao;

    public Genres(GenreDao genreDao) {
        super();
        this.genreDao = genreDao;
    }

    /**
     * 
     * @return la liste de toutes les Genres d'article
     */
    public List<Genre> findAllGenres() {
        return genreDao.findAllGenres().stream().map((gen) -> new Genre(genreDao, gen)).collect(Collectors.toList());
    }

    public Genre findGenreById(long id) {
        return new Genre(genreDao, genreDao.findById(id));
    }


}
