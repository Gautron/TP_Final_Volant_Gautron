package fr.cours.isima.presentation.livre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cours.isima.presentation.Page;

@WebServlet("/deleteLivre")
public class DeleteLivreServlet extends LivresServlet {

    @Override
    protected Page process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getLivres().deleteLivreById(Long.parseLong(req.getParameter("id")));
        return redirectOnListLivres(req).build();
    }

}
