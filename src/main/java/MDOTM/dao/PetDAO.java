package MDOTM.dao;

import MDOTM.model.Pet;

import java.util.List;

public interface PetDAO {


    void save(Pet theStudent);

    Pet findById(Long id);

    List<Pet> findAll();

    List<Pet> findBySpecies(String species);

    List<Pet> findByOwnerName(String ownerName);

    void update(Pet theStudent);

    void delete(Long id);

    int deleteAll();


}
