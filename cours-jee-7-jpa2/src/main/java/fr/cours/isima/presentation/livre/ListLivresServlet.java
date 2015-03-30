package fr.cours.isima.presentation.livre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cours.isima.presentation.Page;

@WebServlet(urlPatterns = { "/listLivres", "/index.html" })
public class ListLivresServlet extends LivresServlet {

    @Override
    protected Page process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	return forwardOnList().withTwoBean("livres", getLivres().findAllLivres(), "genres", getLivres().findAllGenres()).build();
    }

}
