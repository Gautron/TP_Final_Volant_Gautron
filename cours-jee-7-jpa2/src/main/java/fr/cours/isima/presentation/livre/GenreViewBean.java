package fr.cours.isima.presentation.livre;

import fr.cours.isima.business.Livre;
import fr.cours.isima.business.Genre;

public class GenreViewBean {

    private final Livre livre;

    private final Genre genre;

    GenreViewBean(Livre livre, Genre genre) {
        super();
        this.livre = livre;
        this.genre = genre;
    }

    public boolean isSelected() {
        return genre.equals(livre.getGenre());
    }

    public String getLibelle() {
        return genre.getLibelle();
    }

    public long getId() {
        return genre.getId();
    }

}
