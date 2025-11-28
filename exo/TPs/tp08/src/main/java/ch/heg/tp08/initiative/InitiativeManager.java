package ch.heg.tp08.initiative;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitiativeManager {
  private final EntityManager entityManager;

  @Autowired
  public InitiativeManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Initiative find(Long id) {
    return entityManager.find(Initiative.class, id);
  }

  public List<Initiative> findAll() {
    return entityManager
        .createQuery("SELECT i FROM Initiative i", Initiative.class)
        .getResultList();
  }

  @Transactional
  public void create(Initiative initiative) {
    entityManager.persist(initiative);
  }

  @Transactional
  public Initiative update(Initiative initiative) {
    return entityManager.merge(initiative);
  }

  @Transactional
  public boolean delete(Long id) {
    Initiative initiative = entityManager.find(Initiative.class, id);
    if (initiative != null) {
      entityManager.remove(initiative);
      return true;
    }
    return false;
  }
}
