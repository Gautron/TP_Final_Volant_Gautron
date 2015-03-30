package fr.cours.isima.persistence;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 * Cette classe est chargée d'executer les requêtes et de gérer les transactions
 * ainsi que quelques autres subtitlites comme les contraintes d'unicité
 * 
 * @author Benjamin
 *
 */
class EntityManagerExecutor {

    static final String DEFAULT_PU_NAME = "jpa-tp-final";
    private final String persistenceUnitName;

    /**
     * Constructeur avec le persistenceUnitName utilisé pour les requetes. Si
     * votre fichier persistence.xml en utilise un autre que celui par defaut (
     * {@link #DEFAULT_PU_NAME}) vous pouvez spécifier un noveau
     * 
     * @param persistenceUnitName
     */
    EntityManagerExecutor(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    EntityManagerExecutor() {
        this(DEFAULT_PU_NAME);
    }

    public <T> T execute(CalledDuringTransaction<T> calledDuringTransaction) {
        // Commun à tout le monde la gestion des transactions
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);

        final EntityManager entityManager = factory.createEntityManager();

        final EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // On appelle notre morceaux de code personnalise
        final T call = calledDuringTransaction.call(entityManager);
        try {
            entityTransaction.commit();
        } catch (final RollbackException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw (ConstraintViolationException) e.getCause();
            }
            throw e;
        }
        return call;
    }

    private <T> T write(CalledDuringTransaction<T> calledDuringTransaction) {

        try {
            return execute(calledDuringTransaction);
        } catch (final RollbackException e) {
            if (e.getCause() instanceof DatabaseException) {
                handleUniqueContraints(e);
            }
            throw e;
        }

    }

    private <T> void handleUniqueContraints(final RollbackException e) {
        final DatabaseException dbe = (DatabaseException) e.getCause();
        if (DatabaseException.SQL_EXCEPTION == dbe.getErrorCode()) {
            final SQLException sqlException = (SQLException) dbe.getCause();
            if (sqlException.getMessage().contains("UNIQUE constraint failed:")) {
                final String concernedField = StringUtils.substringAfter(sqlException.getMessage(), "UNIQUE constraint failed:");
                final String isolateTableAndField = StringUtils.substringBefore(concernedField, ")").trim();
                final String fieldName = StringUtils.substringAfter(isolateTableAndField, ".").trim();
                throw new UniqueConstraintException(fieldName);
            }
        }
    }

    public <T> void insert(final T instance) {

        write(entityManager -> {
            entityManager.persist(instance);

            return null;
        });

    }

    public <T> void update(final T instance) {

        write(entityManager -> {
            entityManager.merge(instance);
            return null;
        });

    }

    public <T> void delete(final T instance) {

        write(entityManager -> {
            entityManager.remove(entityManager.merge(instance));
            return null;
        });

    }
}
