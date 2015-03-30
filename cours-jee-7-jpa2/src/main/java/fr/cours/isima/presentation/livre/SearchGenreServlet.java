package fr.cours.isima.presentation.livre;

import javax.persistence.NoResultException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cours.isima.business.Genre;
import fr.cours.isima.business.Livre;
import fr.cours.isima.business.Livres;
import fr.cours.isima.common.ApplicationsObjects;

@WebServlet("/searchGenre")
public class SearchGenreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Livre> livres = new ArrayList<Livre>();
		try {
			livres = getApplicationObjects().get(Livres.class).findByGenre(req.getParameter("genre_rech"));
			Iterator<Livre> it = livres.iterator();
			String result = "";
			while(it.hasNext()) {
				Livre currentLivre = it.next();
				result += currentLivre.getTitre() + " - " + currentLivre.getIsbn() + " - " + currentLivre.getGenre().getLibelle() + " - " + currentLivre.getEtat() + " - Dernier Emprunteur : " + currentLivre.getDernierEmprunteur()  + "<br>";			
			}
			if(result.equals("")){
				result = "Pas de livre de ce genre";
			}
			resp.getWriter().write(result);
		}catch(final NoResultException e){
			resp.getWriter().write("Pas de livre de ce genre");
		}
		
        
	}
	
	final protected ApplicationsObjects getApplicationObjects() {
        return (ApplicationsObjects) getServletContext().getAttribute("app-objects");
    }
}
