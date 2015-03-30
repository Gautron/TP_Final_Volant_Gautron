package fr.cours.isima.persistence;

/**
 * Permet de gérer correctement les contraintes d'unicite
 * 
 * @author Benjamin
 *
 */
public class UniqueConstraintException extends RuntimeException {

    private final String fieldName;

    UniqueConstraintException(String field) {
        super();
        this.fieldName = field;
    }

    public String getFieldName() {
        return fieldName;
    }
}
