package fr.cours.isima.business;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import fr.cours.isima.persistence.LivreBean;
import fr.cours.isima.persistence.LivreDao;
import fr.cours.isima.persistence.GenreDao;

/**
 * Cette classe de service permet d'accéder à l'ensemble des Livres et
 * Genres
 * 
 * @see LivreDao
 * @see GenreDao
 * 
 * @author Quentin VOLANT
 * 
 */
public class Livres {

    private final LivreDao LivreDao;
    private final GenreDao GenreDao;

    public Livres(LivreDao LivreDao, GenreDao GenreDao) {
        Preconditions.checkNotNull(LivreDao, "LivreDao");
        Preconditions.checkNotNull(GenreDao, "GenreDao");
        this.LivreDao = LivreDao;
        this.GenreDao = GenreDao;

    }

    public Livre createLivre() {
        final LivreBean LivreBean = new LivreBean();
        return new Livre(LivreBean, LivreDao, GenreDao);
    }

    /**
     * 
     * @return la liste de tous les Livres disponibles
     */
    public List<Livre> findAllLivres() {
        return LivreDao.findAllLivres().stream().map(LivreBean -> new Livre(LivreBean, LivreDao, GenreDao)).collect(Collectors.toList());
    }
    

    public Livre findById(long parseLong) {
        return new Livre(LivreDao.findById(parseLong), LivreDao, GenreDao);
    }

    public void deleteLivreById(long id) {
        LivreDao.delete(LivreDao.findById(id));
    }

	public Livre findByTitre(String titre) {
		return new Livre(LivreDao.findByTitre(titre), LivreDao, GenreDao);
	}

	public List<Livre> findByGenre(String genre) {
		return LivreDao.findByGenre(genre).stream().map(LivreBean -> new Livre(LivreBean, LivreDao, GenreDao)).collect(Collectors.toList());
	}

	public List<Livre> findAllGenres() {
		return LivreDao.findAllGenres().stream().map(LivreBean -> new Livre(LivreBean, LivreDao, GenreDao)).collect(Collectors.toList());
	}

}
