package fr.cours.isima.common;

import java.util.HashMap;
import java.util.Map;

import fr.cours.isima.business.Livres;
import fr.cours.isima.business.Genres;
import fr.cours.isima.business.Utilisateurs;
import fr.cours.isima.persistence.JpaUtilisateurDao;
import fr.cours.isima.persistence.LivreDao;
import fr.cours.isima.persistence.GenreDao;
import fr.cours.isima.persistence.JpaLivreDao;
import fr.cours.isima.persistence.JpaGenreDao;
import fr.cours.isima.persistence.UtilisateurDao;

/**
 * Cette classe sert simplement à centraliser tous les objets à creer dans
 * l'application.
 * <p>
 * Cette approche est assez proche de l'injection de dépendance
 * </p>
 * 
 * @author Quentin
 *
 */
public class ApplicationsObjects {

    /**
     * L'ensemble des objets sont tout simplement stockes sous la forme d'une
     * map.
     * 
     * Dans notre cas nous n'aurons qu'une seule implementation par classe.
     * 
     * Mais dans les containers il est possible de faire bien plus
     */
    private final Map<Class<?>, Object> objects = new HashMap<Class<?>, Object>();

    private ApplicationsObjects() {

    }

    private <T, V extends T> T addObject(Class<T> keyClass, V instance) {
        objects.put(keyClass, instance);
        return instance;
    }

    /**
     * Cette methode charge les objets. Notons que cette initialisation en dur
     * est valable dans notre application.
     * 
     * Mais elle est limitée puisque nous n'offrons pas la possiblite de les
     * définir ailleurs que dans cette méthode.
     * 
     * Ce n'est pas génant pour notre exemple.
     * 
     * @return l'ensemble des objets de l'application
     */
    public static ApplicationsObjects loadAll() {
        final ApplicationsObjects app = new ApplicationsObjects();

        // On ajoute les daos
        final LivreDao LivreDao = app.addObject(LivreDao.class, new JpaLivreDao());
        final GenreDao GenreDao = app.addObject(GenreDao.class, new JpaGenreDao());
        final UtilisateurDao UtilisateurDao = app.addObject(UtilisateurDao.class, new JpaUtilisateurDao());

        // On ajoute les services metiers
        app.addObject(Genres.class, new Genres(GenreDao));
        app.addObject(Livres.class, new Livres(LivreDao, GenreDao));
        app.addObject(Utilisateurs.class, new Utilisateurs(UtilisateurDao));

        return app;
    }

    /**
     * 
     * @param keyClass
     *            la keyClass qui permet de retrouver l'objet cherché
     * @return l'instance correspondant à la "keyclass"
     */
    public <T> T get(Class<T> keyClass) {
        if (!objects.containsKey(keyClass)) {
            throw new IllegalArgumentException("Aucun objet ne correspond a la classe " + keyClass);
        }
        return (T) objects.get(keyClass);
    }
}
