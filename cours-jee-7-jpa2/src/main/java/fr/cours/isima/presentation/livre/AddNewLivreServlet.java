package fr.cours.isima.presentation.livre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cours.isima.business.Livre;
import fr.cours.isima.business.Livres;
import fr.cours.isima.presentation.Form;
import fr.cours.isima.presentation.Page;

@WebServlet("/addNewLivre")
public class AddNewLivreServlet extends LivresServlet {

    @Override
    protected Page process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Livres livres = getLivres();
        final Livre livre = livres.createLivre();
        final Form<LivreViewBean> formViewBean = Form.success(new LivreViewBean(livres, getGenres(), livre));

        // Un petit moyen d'encapsuler
        return forwardOnEdit().withForm(formViewBean).build();
    }

}
