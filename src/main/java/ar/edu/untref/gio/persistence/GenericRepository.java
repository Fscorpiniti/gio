package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public abstract class GenericRepository<E extends Object> implements Repository <E> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "gioPersistenceUnit")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void add(E object) {
        entityManager.persist(object);
    }
}
