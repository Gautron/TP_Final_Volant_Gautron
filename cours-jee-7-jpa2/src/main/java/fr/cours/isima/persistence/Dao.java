package fr.cours.isima.persistence;

public interface Dao<T> {

    /**
     * Trouve l'objet par son id
     * 
     * @param id
     * @return ne retourne jamais null, doit renvoyer une exception à la place
     */
    T findById(long id);

    /**
     * 
     * @return la classe de bean utilisée pour les tests
     */
    Class<T> getBeanClass();

    /**
     * Sauvegarde le bean
     * 
     * @param bean
     */
    void save(T bean);

    /**
     * Supprime toutes les donnes de la table. Attention de ne pas l'exposer en
     * tant que service !
     */
    void deleteAll();
    
    


}
