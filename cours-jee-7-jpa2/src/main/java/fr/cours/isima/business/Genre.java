package fr.cours.isima.business;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;

import fr.cours.isima.persistence.GenreBean;
import fr.cours.isima.persistence.GenreDao;

/**
 * Le genre permet de regrouper les livres ( {@link Livre}) rendant
 * possible l'affichage thématique
 * 
 * @author Quentin
 *
 */
public class Genre {

    private final GenreBean GenreBean;
    private final GenreDao GenreDao;

    Genre(GenreDao GenreDao, GenreBean GenreBean) {
        this.GenreDao = GenreDao;
        Preconditions.checkNotNull(GenreBean);
        this.GenreBean = GenreBean;
    }

    public GenreBean getGenreBean() {
        return GenreBean;
    }

    public String getLibelle() {
        return GenreBean.getLibelle();
    }

    public long getId() {
        return GenreBean.getId();
    }

    public void save() {
        GenreDao.save(GenreBean);
    }

    @Override
    public int hashCode() {
        return HashCode.fromLong(GenreBean.getId()).asInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Genre) {
            final Genre that = (Genre) obj;
            return Objects.equal(GenreBean.getId(), that.GenreBean.getId());
        }
        return false;
    }
}
