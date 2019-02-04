package metube.repository;

import metube.domein.enteties.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {

    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metube")
                .createEntityManager();
    }

    @Override
    public Optional<Tube> findByName(String name) {


        Optional<Tube>tube =  Optional.of(
                this.entityManager
                        .createQuery("SELECT t FROM tubes t Where t.name =: name",Tube.class)
                .setParameter("name",name)
                .getSingleResult());
        return tube;
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public List<Tube> findAll() {
      List<Tube>result =  this.entityManager
                .createQuery("SELECT t FROM tubes t",Tube.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<Tube> findById(String id) {
        Optional<Tube>tube = Optional.of(
                this.entityManager
                        .createQuery("SELECT t FROM tubes t WHERE t.id = :id ",Tube.class)
                        .setParameter("id",id)
                        .getSingleResult());
        return tube;
    }
}
