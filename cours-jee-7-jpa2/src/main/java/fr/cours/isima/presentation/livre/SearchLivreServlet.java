package fr.cours.isima.presentation.livre;

import javax.persistence.NoResultException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cours.isima.business.Livre;
import fr.cours.isima.business.Livres;
import fr.cours.isima.common.ApplicationsObjects;

@WebServlet("/searchLivre")
public class SearchLivreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Livre livre;
		try{
			livre = getApplicationObjects().get(Livres.class).findByTitre(req.getParameter("titre_rech"));
			resp.getWriter().write(livre.getTitre() + " - " + livre.getIsbn() + " - " + livre.getGenre().getLibelle() + " - " + livre.getEtat() + " - Dernier Emprunteur : " + livre.getDernierEmprunteur() );
		}catch(final NoResultException e){
			resp.getWriter().write("Ce livre n'existe pas dans la librairie");
		}
		
	}

	final protected ApplicationsObjects getApplicationObjects() {
        return (ApplicationsObjects) getServletContext().getAttribute("app-objects");
    }
}
