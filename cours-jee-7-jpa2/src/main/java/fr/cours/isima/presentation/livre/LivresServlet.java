package fr.cours.isima.presentation.livre;

import javax.servlet.http.HttpServletRequest;

import fr.cours.isima.business.Livres;
import fr.cours.isima.business.Genres;
import fr.cours.isima.presentation.NavigableServlet;
import fr.cours.isima.presentation.Page;
import fr.cours.isima.presentation.Page.PageBuilder;

abstract class LivresServlet extends NavigableServlet {

    final protected Livres getLivres() {
        return getApplicationObjects().get(Livres.class);
    }

    final protected Genres getGenres() {
        return getApplicationObjects().get(Genres.class);
    }

    protected final PageBuilder forwardOnEdit() {
        return Page.forwardOn("/jsp/editLivre.jsp");
    }

    protected final PageBuilder redirectOnListLivres(HttpServletRequest req) {
        return Page.redirectOn(req.getContextPath() + "/listLivres");
    }

    protected final PageBuilder forwardOnList() {
        return Page.forwardOn("/jsp/listLivres.jsp");
    }
}
