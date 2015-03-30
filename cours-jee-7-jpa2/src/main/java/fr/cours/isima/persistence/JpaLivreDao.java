package fr.cours.isima.persistence;

import java.util.List;

public class JpaLivreDao implements LivreDao {

    private final EntityManagerExecutor entityManagerExecutor = new EntityManagerExecutor();

    @Override
    public List<LivreBean> findAllLivres() {
        return entityManagerExecutor.execute(em -> em.createQuery("select l from Livre l", LivreBean.class).getResultList());
    }
    
    @Override
    public List<LivreBean> findAllGenres() {
        return entityManagerExecutor.execute(em -> em.createQuery("select l from Livre l group by l.genre", LivreBean.class).getResultList());
    }
 
    

    @Override
    public void save(LivreBean livreBean) {
        if (livreBean.getId() > 0) {
            entityManagerExecutor.update(livreBean);
        } else {
            try {
                entityManagerExecutor.insert(livreBean);
            } catch (final RuntimeException e) {
                // On repasse l'id à zero sinon on ne pourra pas enregistrer la
                // valeur

            	livreBean.setId(0);
                throw e;
            }
        }
    }

    @Override
    public LivreBean findById(final long id) {
        return entityManagerExecutor.execute(em -> em.createQuery("select l from Livre l where l.id='" + id + "'", LivreBean.class).getSingleResult());
    }
    
    @Override
    public LivreBean findByTitre(final String titre) {
    	return entityManagerExecutor.execute(em -> em.createQuery("select l from Livre l where l.titre='" + titre + "'", LivreBean.class).getSingleResult());
    }
    
    @Override
    public List<LivreBean> findByGenre(final String genre) {
        return entityManagerExecutor.execute(em -> em.createQuery("select l from Livre l where l.genre.libelle='" + genre + "'", LivreBean.class).getResultList());
    }

    @Override
    public void delete(LivreBean livreBean) {
        entityManagerExecutor.delete(livreBean);
    }

    @Override
    public Class<LivreBean> getBeanClass() {
        return LivreBean.class;
    }

    @Override
    public void deleteAll() {
        entityManagerExecutor.execute(em -> em.createQuery("delete from Livre").executeUpdate());
    }
}
