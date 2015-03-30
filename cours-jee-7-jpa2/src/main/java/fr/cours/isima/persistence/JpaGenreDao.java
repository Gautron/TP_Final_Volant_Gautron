package fr.cours.isima.persistence;

import java.util.List;

public class JpaGenreDao implements GenreDao {

    private final EntityManagerExecutor entityManagerExecutor = new EntityManagerExecutor();

    @Override
    public List<GenreBean> findAllGenres() {
        return entityManagerExecutor.execute(entityManager -> entityManager.createQuery("select g from Genre g", GenreBean.class).getResultList());
    }

    @Override
    public GenreBean findById(final long id) {
        return entityManagerExecutor.execute(entityManager -> entityManager.createQuery("select g from Genre g where g.id='" + id + "'", GenreBean.class)
                .getSingleResult());
    }
    
  
    @Override
    public void save(GenreBean bean) {
        if (bean.getId() > 0) {
            entityManagerExecutor.update(bean);
        } else {
            entityManagerExecutor.insert(bean);
        }
    }

    @Override
    public Class<GenreBean> getBeanClass() {
        return GenreBean.class;
    }

    @Override
    public void deleteAll() {
        entityManagerExecutor.execute(em -> em.createQuery("delete from Genre").executeUpdate());
    }

}
