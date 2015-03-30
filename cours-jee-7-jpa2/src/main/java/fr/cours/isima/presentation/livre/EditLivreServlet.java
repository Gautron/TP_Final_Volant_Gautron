package fr.cours.isima.presentation.livre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cours.isima.business.Livre;
import fr.cours.isima.presentation.Form;
import fr.cours.isima.presentation.Page;

@WebServlet("/editLivre")
public class EditLivreServlet extends LivresServlet {

    @Override
    protected Page process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Livre livre = getLivres().findById(Long.parseLong(req.getParameter("id")));
        final LivreViewBean livreViewBean = new LivreViewBean(getLivres(), getGenres(), livre);
        return forwardOnEdit().withForm(Form.success(livreViewBean)).build();
    }
}
