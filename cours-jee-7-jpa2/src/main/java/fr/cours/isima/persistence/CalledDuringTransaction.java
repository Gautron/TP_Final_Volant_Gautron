package fr.cours.isima.persistence;

import javax.persistence.EntityManager;

public interface CalledDuringTransaction<T> {

    T call(EntityManager entityManager);
}
