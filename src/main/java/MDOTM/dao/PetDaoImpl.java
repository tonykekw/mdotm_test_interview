package MDOTM.dao;


import MDOTM.model.Pet;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDaoImpl implements PetDAO {

    @Autowired
    private EntityManager entityManager;


    public PetDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Pet theStudent) {

    }

    @Override
    public Pet findById(Long id) {
        return null;
    }

    @Override
    public List<Pet> findAll() {
        return List.of();
    }

    @Override
    public List<Pet> findBySpecies(String species) {
        return List.of();
    }

    @Override
    public List<Pet> findByOwnerName(String ownerName) {
        return List.of();
    }

    @Override
    @Transactional
    public void update(Pet theStudent) {

    }

    @Override
    @Transactional
    public void delete(Long id) {

    }

    @Override
    @Transactional
    public int deleteAll() {
        return 0;
    }
}
