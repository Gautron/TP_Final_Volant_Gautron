package fr.cours.isima.presentation;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

/**
 * Utilisee par {@link ErrorFields} C'est une classe technique representant un
 * champ de formulaire en erreur. Le but est d'afficher simplement un message
 * d'erreur sur le bon champ
 * 
 * @author Benjamin
 *
 */
public class Field {

    private final String fieldName;
    private final String errorMessage;

    private Field(String fieldName, String errorMessage) {
        Preconditions.checkNotNull(fieldName, "fieldName");
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public String getName() {
        return fieldName;
    }

    public boolean isError() {
        return StringUtils.isNotBlank(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getKindOfMessage() {
        if (isError()) {
            return "has-error";
        }
        return StringUtils.EMPTY;
    }

    static Field errorField(String fieldName, String errorMessage) {
        Preconditions.checkNotNull(errorMessage, "errorMessage");
        return new Field(fieldName, errorMessage);
    }

    static Field success(String name) {
        return new Field(name, null);
    }

}
