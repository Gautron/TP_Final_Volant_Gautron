package fr.cours.isima.presentation.livre;

import java.util.List;
import java.util.stream.Collectors;

import fr.cours.isima.business.Livre;
import fr.cours.isima.business.Livres;
import fr.cours.isima.business.Genres;
import fr.cours.isima.business.Genre;

/**
 * Le view bean facilite l'affichage et permet d'aider a creer des jsp plus
 * simples et plus coherentes
 * 
 * @author Quentin - Jonathan
 *
 */
public class LivreViewBean {

    private final Livres livres;

    private final Livre livre;

    private final Genres genres;

    public LivreViewBean(Livres livres, Genres genres, Livre livre) {
        this.livres = livres;
        this.genres = genres;
        this.livre = livre;
    }

    public String getIsbn() {
        return livre.getIsbn();
    }

    public String getTitre() {
        return livre.getTitre();
    }

    public Long getId() {
        return livre.getId();
    }

    public Genre getGenre() {
        return livre.getGenre();
    }
    
    public String getEtat(){
    	return livre.getEtat();
    }
    
    public String getDernierEmprunteur(){
    	return livre.getDernierEmprunteur();
    }

    public List<GenreViewBean> getAllGenres() {
        return genres.findAllGenres().stream().map((c) -> new GenreViewBean(livre, c)).collect(Collectors.toList());
    }

}
