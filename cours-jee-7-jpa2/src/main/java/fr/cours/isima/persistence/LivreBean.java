package fr.cours.isima.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Livre")
@Table(name = "LIVRE")
public class LivreBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Numéro ISBN obligatoire")
    @Column(name = "isbn", unique = true)
    @Size(min = 10, max = 13)
    private String isbn;

    @Column(name = "titre")
    @Size(max = 150, message = "Le titre doit faire moins de 150 caracteres")
    private String titre;

    @JoinColumn(name = "genre", nullable = false, referencedColumnName = "id")
    @OneToOne(targetEntity = GenreBean.class)
    @NotNull(message = "Genre obligatoire")
    private GenreBean genre;
    
    @Column(name = "etat")
    private String etat;
    
    @Column(name = "dernierEmprunteur")
    private String dernierEmprunteur;

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setGenre(GenreBean genre) {
        this.genre = genre;
    }

    public GenreBean getGenre() {
        return genre;
    }
    
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }
    
    public void setDernierEmprunteur(String dernierEmprunteur) {
        this.dernierEmprunteur = dernierEmprunteur;
    }

    public String getDernierEmprunteur() {
        return dernierEmprunteur;
    }
}
