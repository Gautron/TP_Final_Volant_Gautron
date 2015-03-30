package fr.cours.isima.presentation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import fr.cours.isima.common.AbstractUnsupportedOperationMap;
import fr.cours.isima.persistence.UniqueConstraintException;

/**
 * Cette classe permet de gerer simplement l'affichage des champs avec la jsp.
 * <ul>
 * <li>Il permet d'afficer les valeurs des champs et les eventuels messages</li>
 * </ul>
 * 
 * @author Benjamin
 *
 */
public class ErrorFields extends AbstractUnsupportedOperationMap<String, Field> {

    private final Map<String, Field> fieldsInErrors;

    private ErrorFields(Map<String, Field> infos) {
        fieldsInErrors = infos;

    }

    @Override
    public Field get(Object name) {
        if (fieldsInErrors.containsKey(name)) {
            return fieldsInErrors.get(name);
        }
        return Field.success((String) name);
    }

    /**
     * Créer un objet de champ d'erreur à partir des erreurs de bean validation
     * standard
     * 
     * @param violations
     * @return
     */
    public static ErrorFields withErrorsViolations(Set<ConstraintViolation<?>> violations) {
        final Map<String, Field> errorsFields = new HashMap<String, Field>();

        for (final ConstraintViolation<?> violation : violations) {
            final String fieldName = violation.getPropertyPath().toString();
            errorsFields.put(fieldName, Field.errorField(fieldName, violation.getMessage()));
        }
        return new ErrorFields(errorsFields);
    }

    public static ErrorFields empty() {
        return new ErrorFields(Collections.emptyMap());
    }

    /**
     * Les contraintes uniques n'étant pas gérées en standard cette méthode
     * permet de construire les champs en erreurs avec une
     * {@link UniqueConstraintException}
     * 
     * @param e
     * @return
     */
    public static ErrorFields withUniqueContraintException(UniqueConstraintException e) {
        final Map<String, Field> errorsFields = new HashMap<String, Field>();
        errorsFields.put(e.getFieldName(), Field.errorField(e.getFieldName(), "Impossible d'utiliser cette valeur elle existe déjà"));
        return new ErrorFields(errorsFields);
    }

}
