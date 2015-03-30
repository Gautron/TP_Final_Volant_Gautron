package fr.cours.isima.presentation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Modelise la redirection vers une autre jsp avec la notion de formulaire.
 * 
 * Cela permet de faire une redirection un peu plus sympathique qu'avec l'api
 * standard
 * 
 * @author Benjamin
 *
 * @param <T>
 */
public class Page {

    private final String target;

    private final Form<?> form;

    private final Action action;
    private final Map<String, Object> beans;

    private Page(String target, Action action, Form<?> form, Map<String, Object> beans) {
        super();
        this.target = target;
        this.form = form;
        this.action = action;
        this.beans = beans;
    }

    public void goToPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (form != null) {
            req.setAttribute("form", form);
        }
        beans.entrySet().stream().forEach((entry) -> req.setAttribute(entry.getKey(), entry.getValue()));

        action.process(target, req, resp);
    }

    public static <T> PageBuilder redirectOn(String pagePath) {
        return new PageBuilder(pagePath, Action.REDIRECT);
    }

    public static <T> PageBuilder forwardOn(String pagePath) {
        return new PageBuilder(pagePath, Action.FORWARD);
    }

    /**
     * Vous avez déjà vu dans un TP le pattern builder (la correction sur la
     * navigation).
     * 
     * Il permet de construire un objet de façon fluent
     * 
     * @author Benjamin
     *
     * @param <T>
     */
    public static class PageBuilder {
        private final String pagePath;
        private final Action action;
        private Form<?> form;
        private final Map<String, Object> beans = new HashMap<String, Object>();

        public PageBuilder(String pagePath, Action action) {
            this.pagePath = pagePath;
            this.action = action;
        }

        public PageBuilder withForm(Form<?> form) {
            if (action.equals(Action.REDIRECT)) {
                throw new IllegalArgumentException("Il est impossible d'utiliser l'instruction redirect avec une form. Les donnees ne seront pas transmises");
            }
            this.form = form;
            return this;
        }

        public Page build() {
            return new Page(pagePath, action, form, beans);
        }

        public PageBuilder withBean(String beanName, Object bean) {
            beans.put(beanName, bean);
            return this;
        }
        
        public PageBuilder withTwoBean(String beanName1, Object bean1, String beanName2, Object bean2) {
            beans.put(beanName1, bean1);
            beans.put(beanName2, bean2);
            return this;
        }

    }

    private enum Action {
        FORWARD {
            @Override
            void process(String target, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.getRequestDispatcher(target).forward(req, resp);
            }
        },
        REDIRECT {
            @Override
            void process(String target, HttpServletRequest req, HttpServletResponse resp) throws IOException {
                resp.sendRedirect(target);
            }
        };

        abstract void process(String target, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    }
}
