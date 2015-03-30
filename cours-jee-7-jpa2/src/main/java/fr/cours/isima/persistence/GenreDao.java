package fr.cours.isima.persistence;

import java.util.List;

public interface GenreDao extends Dao<GenreBean> {

    List<GenreBean> findAllGenres();

   

}
