package fr.cours.isima.persistence;

import java.util.Collection;
import java.util.List;

import fr.cours.isima.business.Livre;

public interface LivreDao extends Dao<LivreBean> {

    /**
     * 
     * @return la liste de tous les livres
     */
    List<LivreBean> findAllLivres();

   
    
    /**
     * Sauvegarde le livre dans la couche persistance
     * 
     * @param livreBean
     */
    @Override
    void save(LivreBean livreBean);

    /**
     * 
     * @param livreBean
     */
    void delete(LivreBean livreBean);

    /**
     * 
     * @param titre
     * 
     * @return le livre dont on a renseigné le titre
     */
	LivreBean findByTitre(String titre);

	List<LivreBean> findByGenre(String genre);



	List<LivreBean> findAllGenres();


}
