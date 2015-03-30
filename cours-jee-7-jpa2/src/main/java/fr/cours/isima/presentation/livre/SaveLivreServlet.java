package fr.cours.isima.presentation.livre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import fr.cours.isima.business.Livre;
import fr.cours.isima.business.Genre;
import fr.cours.isima.persistence.UniqueConstraintException;
import fr.cours.isima.presentation.ErrorFields;
import fr.cours.isima.presentation.Form;
import fr.cours.isima.presentation.Page;

@WebServlet({ "/saveLivre" })
public class SaveLivreServlet extends LivresServlet {

    @Override
    protected Page process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long id = Long.parseLong(req.getParameter("id"));
        final Livre livre = findLivreById(id);
        final Genre genre = getGenres().findGenreById(Long.parseLong(req.getParameter("genre")));
        livre.setGenre(genre);
        livre.setTitre(req.getParameter("titre"));
        livre.setISBN(req.getParameter("isbn"));
        livre.setEtat(req.getParameter("etat"));
        livre.setDernierEmprunteur(req.getParameter("dernierEmprunteur"));
        
        try {
            // Lors de la sauvegarde on controle les champs
            livre.save();
            return redirectOnListLivres(req).build();
        } catch (final ConstraintViolationException e) {
            final ErrorFields fields = ErrorFields.withErrorsViolations(e.getConstraintViolations());
            return returnToEditPage(livre, fields);
        } catch (final UniqueConstraintException e) {
            final ErrorFields fields = ErrorFields.withUniqueContraintException(e);
            return returnToEditPage(livre, fields);
        }
    }

    private Page returnToEditPage(final Livre livre, final ErrorFields fields) {
        final LivreViewBean livViewBean = new LivreViewBean(getLivres(), getGenres(), livre);
        final Form<LivreViewBean> form = new Form<LivreViewBean>(livViewBean, fields);

        return forwardOnEdit().withForm(form).build();
    }

    private Livre findLivreById(long id) {
        if (id > 0) {
            return getLivres().findById(id);
        }
        return getLivres().createLivre();
    }
}
