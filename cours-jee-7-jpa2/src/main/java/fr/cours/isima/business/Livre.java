package fr.cours.isima.business;

import com.google.common.base.Preconditions;

import fr.cours.isima.persistence.LivreBean;
import fr.cours.isima.persistence.LivreDao;
import fr.cours.isima.persistence.GenreDao;

/**
 * 
 * @author Quentin Volant
 *
 */
public class Livre {

    private final LivreBean LivreBean;
    private final LivreDao LivreDao;
    private final GenreDao GenreDao;

    public Livre(LivreBean LivreBean, LivreDao LivreDao, GenreDao GenreDao) {
        Preconditions.checkNotNull(LivreDao, "LivreDao");
        Preconditions.checkNotNull(LivreBean, "LivreBean");
        Preconditions.checkNotNull(GenreDao, "GenreDao");
        this.GenreDao = GenreDao;
        this.LivreDao = LivreDao;
        this.LivreBean = LivreBean;
    }

    public void setISBN(String ISBNLivre) {
        LivreBean.setIsbn(ISBNLivre);
    }

    public void setGenre(Genre Genre) {
        LivreBean.setGenre(Genre.getGenreBean());
    }

    public void setTitre(String titre) {
        LivreBean.setTitre(titre);
    }
    
    public void setEtat(String etat) {
        LivreBean.setEtat(etat);
    }
    
    public void setDernierEmprunteur(String dernierEmprunteur) {
        LivreBean.setDernierEmprunteur(dernierEmprunteur);
    }

    public String getTitre() {
        return LivreBean.getTitre();
    }

    public String getIsbn() {
        return LivreBean.getIsbn();
    }

    public Long getId() {
        return LivreBean.getId();
    }
    
    public String getEtat() {
        return LivreBean.getEtat();
    }
    
    public String getDernierEmprunteur() {
        return LivreBean.getDernierEmprunteur();
    }

    public void save() {
        LivreDao.save(LivreBean);
    }

    public Genre getGenre() {
        if (LivreBean.getGenre() == null) {
            return null;
        }
        return new Genre(GenreDao, LivreBean.getGenre());
    }

    public boolean exists() {
        return LivreBean.getId() > 0l;
    }

}
