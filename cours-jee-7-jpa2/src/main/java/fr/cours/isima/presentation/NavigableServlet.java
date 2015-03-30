package fr.cours.isima.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cours.isima.common.ApplicationsObjects;

/**
 * La servlet standarde de base qui attend l'utilisation d'une {@link Page} en
 * retour.
 * 
 * @author Benjamin
 *
 */
public abstract class NavigableServlet extends HttpServlet {

    @Override
    final protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    final protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Page page = process(req, resp);
        page.goToPage(req, resp);
    }

    final protected ApplicationsObjects getApplicationObjects() {
        return (ApplicationsObjects) getServletContext().getAttribute("app-objects");
    }

    protected abstract Page process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
