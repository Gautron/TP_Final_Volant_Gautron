package fr.cours.isima.fill.database;

import fr.cours.isima.common.ApplicationsObjects;
import fr.cours.isima.persistence.LivreDao;
import fr.cours.isima.persistence.LivreBean;
import fr.cours.isima.persistence.GenreBean;
import fr.cours.isima.persistence.GenreDao;
import fr.cours.isima.persistence.UtilisateurBean;
import fr.cours.isima.persistence.UtilisateurDao;

/**
 * Cette classe peut etre utiliser pour reinitialiser la base de donnes et en
 * creer une novuelles
 * 
 * @author Quentin - Jonathan
 *
 */
public class FillDataBase {

    public static void main(String[] args) {
        final ApplicationsObjects objects = ApplicationsObjects.loadAll();

        // On nettoie la base
        objects.get(LivreDao.class).deleteAll();
        final GenreDao genreDao = objects.get(GenreDao.class);
        genreDao.deleteAll();

        final LivreDao livreDao =  objects.get(LivreDao.class);
        livreDao.deleteAll();
        
        final UtilisateurDao utilisateurDao =  objects.get(UtilisateurDao.class);
        utilisateurDao.deleteAll();
        
        // Creation d'un genre Autobiographique
        final GenreBean autobiographique = new GenreBean();
        autobiographique.setId(1);
        autobiographique.setLibelle("Autobiographique");
        genreDao.save(autobiographique);

        // Creation d'un genre Dramatique
        final GenreBean dramatique = new GenreBean();
        dramatique.setId(2);
        dramatique.setLibelle("Dramatique");
        genreDao.save(dramatique);
        
        // Creation d'un genre Comique
        final GenreBean comique = new GenreBean();
        comique.setId(3);
        comique.setLibelle("Comique");
        genreDao.save(comique);
        
        
        // Creation d'un genre Fantastique
        final GenreBean fantastique = new GenreBean();
        fantastique.setId(4);
        fantastique.setLibelle("Fantastique");
        genreDao.save(fantastique);
        
        // Creation d'un genre Tragique
        final GenreBean tragique = new GenreBean();
        tragique.setId(5);
        tragique.setLibelle("Tragique");
        genreDao.save(tragique);
        
        // Creation d'un genre Poétique
        final GenreBean poetique = new GenreBean();
        poetique.setId(6);
        poetique.setLibelle("Poétique");
        genreDao.save(poetique);
        
        // Creation d'un livre
        final LivreBean livre1 = new LivreBean();
        livre1.setId(1);
        livre1.setIsbn("12345678998");
        livre1.setTitre("Les Mots"); 
        livre1.setGenre(autobiographique);
        livre1.setEtat("Emprunte");
        livre1.setDernierEmprunteur("bill");
        livreDao.save(livre1);
        
     // Creation d'un Utilisateur
        final UtilisateurBean user1 = new UtilisateurBean();
        user1.setId(1);
        user1.setUsername("root");
        utilisateurDao.save(user1);
    }
}
