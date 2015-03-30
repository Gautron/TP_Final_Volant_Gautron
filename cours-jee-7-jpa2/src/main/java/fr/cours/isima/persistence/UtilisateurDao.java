package fr.cours.isima.persistence;

import java.util.Collection;
import java.util.List;

import fr.cours.isima.business.Utilisateur;

public interface UtilisateurDao extends Dao<UtilisateurBean> {
	
	List<UtilisateurBean> findAllUtilisateurs();

}
