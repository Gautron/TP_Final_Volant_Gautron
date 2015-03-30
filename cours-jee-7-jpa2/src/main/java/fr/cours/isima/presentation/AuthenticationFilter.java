package fr.cours.isima.presentation;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.*;  
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import fr.cours.isima.business.Livre;
import fr.cours.isima.business.Livres;
import fr.cours.isima.business.Utilisateur;
import fr.cours.isima.business.Utilisateurs;
import fr.cours.isima.common.ApplicationsObjects;
import fr.cours.isima.persistence.UtilisateurBean;
import fr.cours.isima.persistence.UtilisateurDao;

//@WebFilter("/index.html")
public class AuthenticationFilter implements Filter {
	
	
	
	public void init(FilterConfig arg0) throws ServletException {}  
    
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		final HttpServletResponse httpResp= (HttpServletResponse) resp;
		final HttpServletRequest httpReq= (HttpServletRequest) req;
		
		final String nick= req.getParameter("nick");
		
		int UtilisateurTrouvee = 0;
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		users = getApplicationObjects(req).get(Utilisateurs.class).findAllUtilisateurs();
		
		Iterator<Utilisateur> it = users.iterator();
		while(it.hasNext() && UtilisateurTrouvee != 1) {
			Utilisateur currentUtilisateur = it.next();
			if (currentUtilisateur.getUsername() == nick ){
				UtilisateurTrouvee = 1;
			}
						
		}
		
		
		if(UtilisateurTrouvee == 1) {
			httpResp.sendRedirect(httpReq.getContextPath() + "/listLivres");
		} else{
			httpResp.sendError(401, "Utilisateur non trouvee");
		}
	}
	
	final protected ApplicationsObjects getApplicationObjects(ServletRequest req) {
		final HttpServletRequest httpReq= (HttpServletRequest) req;
        return (ApplicationsObjects) httpReq.getSession().getServletContext().getAttribute("app-objects");
    }
	          
	
	    public void destroy() {}  
	  
}  


