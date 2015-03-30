package fr.cours.isima.persistence;

import java.util.List;

public class JpaUtilisateurDao implements UtilisateurDao{
	
	private final EntityManagerExecutor entityManagerExecutor = new EntityManagerExecutor();

    @Override
    public List<UtilisateurBean> findAllUtilisateurs() {
        return entityManagerExecutor.execute(em -> em.createQuery("select u from Utilisateur u", UtilisateurBean.class).getResultList());
    }
    
    
    @Override
    public void save(UtilisateurBean utilisateurBean) {
        if (utilisateurBean.getId() > 0) {
            entityManagerExecutor.update(utilisateurBean);
        } else {
            try {
                entityManagerExecutor.insert(utilisateurBean);
            } catch (final RuntimeException e) {
                // On repasse l'id à zero sinon on ne pourra pas enregistrer la
                // valeur

            	utilisateurBean.setId(0);
                throw e;
            }
        }
    }
    
    @Override
    public UtilisateurBean findById(final long id) {
        return entityManagerExecutor.execute(em -> em.createQuery("select l from Livre l where l.id='" + id + "'", UtilisateurBean.class).getSingleResult());
    }

    @Override
    public Class<UtilisateurBean> getBeanClass() {
        return UtilisateurBean.class;
    }

    @Override
    public void deleteAll() {
        entityManagerExecutor.execute(em -> em.createQuery("delete from Livre").executeUpdate());
    }
 

}
