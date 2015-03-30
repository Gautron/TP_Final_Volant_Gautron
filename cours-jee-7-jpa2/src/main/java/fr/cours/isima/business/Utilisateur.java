package fr.cours.isima.business;

import com.google.common.base.Preconditions;

import fr.cours.isima.persistence.UtilisateurBean;
import fr.cours.isima.persistence.UtilisateurDao;

public class Utilisateur {
	
	 private final UtilisateurBean UtilisateurBean;
	    private final UtilisateurDao UtilisateurDao;

	    public Utilisateur(UtilisateurBean UtilisateurBean, UtilisateurDao UtilisateurDao) {
	        Preconditions.checkNotNull(UtilisateurDao, "UtilisateurDao");
	        Preconditions.checkNotNull(UtilisateurBean, "UtilisateurBean");

	        this.UtilisateurDao = UtilisateurDao;
	        this.UtilisateurBean = UtilisateurBean;
	    }
	    
	    public void setUsername(String username) {
	        UtilisateurBean.setUsername(username);
	    }
	    
	    public String getUsername() {
	        return UtilisateurBean.getUsername();
	    }

}
