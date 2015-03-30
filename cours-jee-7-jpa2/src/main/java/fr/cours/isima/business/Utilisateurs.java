package fr.cours.isima.business;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import fr.cours.isima.persistence.UtilisateurDao;


public class Utilisateurs {
	
	private final UtilisateurDao UtilisateurDao;

    public Utilisateurs(UtilisateurDao utilisateurDao) {
        Preconditions.checkNotNull(utilisateurDao, "UtilisateurDao");
        this.UtilisateurDao = utilisateurDao;


    }
	public List<Utilisateur> findAllUtilisateurs() {
        return UtilisateurDao.findAllUtilisateurs().stream().map(UtilisateurBean -> new Utilisateur(UtilisateurBean, UtilisateurDao)).collect(Collectors.toList());
    }

}
