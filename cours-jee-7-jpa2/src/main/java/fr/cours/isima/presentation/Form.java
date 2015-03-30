package fr.cours.isima.presentation;


/**
 * Le formulaire est séparé en deux entités :
 * <ul>
 * <li>le bean qui est simplement la donnee que l'on souhaite afficher dans le
 * formulaire.</li>
 * <li>les champs en erreurs par exemple dans le cas d'une validation</li>
 * </ul>
 * Les objets form ont toujours un scope "request", il serve à l'affichage
 * 
 * @author Benjamin
 *
 * @param <T>
 */
public class Form<T> {

    /**
     * Le bean que l'on souhaite afficher. En general c'est un view bean
     */
    private final T bean;

    /**
     * Les champs en erreurs comme lors d'une validation
     */
    private final ErrorFields errors;

    public Form(T bean, ErrorFields errors) {
        super();
        this.bean = bean;
        this.errors = errors;
    }

    public ErrorFields getErrors() {
        return errors;
    }

    public T getBean() {
        return bean;
    }

    public static <T> Form<T> success(T t) {
        return new Form(t, ErrorFields.empty());
    }
}
