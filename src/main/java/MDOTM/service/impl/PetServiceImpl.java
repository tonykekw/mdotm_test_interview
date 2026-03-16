package MDOTM.service.impl;


import MDOTM.dao.jpa.PetRepository;
import MDOTM.dto.PetEntityDTO;
import MDOTM.exception.PetNotValidException;
import MDOTM.exception.PetNotFoundException;
import MDOTM.model.Pet;
import MDOTM.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;
  private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);


  @Autowired
  public PetServiceImpl(PetRepository petRepository) {
      this.petRepository = petRepository;
  }


  /**
   * Search for every Pet Entity
   *
   * @return List<PetEntityDTO> containing all Pets
   */
    public List<PetEntityDTO> findAll() {

        logger.info("Getting all Pets from DB");

        return petRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();

    }


    /**
     * Search for a Pet Entity through ID
     *
     * @param theId unique for each Pet
     * @return PetEntityDTO for the View
     */
    public PetEntityDTO findById(String theId) {

        logger.info("Getting Pet with ID: {}", theId);

        Optional<Pet> result = petRepository.findById(theId);
        PetEntityDTO petEntityDTO;
        Pet p;
        if(result.isPresent()){

           p = result.get();

        }else{

            throw new PetNotFoundException("Pet not found " + theId);

        }

        petEntityDTO=this.toDto(p);
        return petEntityDTO;
    }

    /**
     * Saves/Updates a Pet Entity
     *
     * @param petEntityDTO before converting it to Pet
     */
    public void save(PetEntityDTO petEntityDTO) {

        logger.info("Saving a new Pet");
        checkPet(petEntityDTO);
        Pet pet;
        pet=this.toEntity(petEntityDTO);
        petRepository.save(pet);
    }

    public void checkPet(PetEntityDTO pet){

        if(pet.getAge() !=null && pet.getAge()<=0){
            throw new PetNotValidException("Pet's age must be > 0");
        }

        if(pet.getSpecies() == null ||  pet.getSpecies().trim().isEmpty()){
            throw new PetNotValidException("Pet's species must be specified");
        }

        if(pet.getName() == null || pet.getName().trim().isEmpty()){
            throw new PetNotValidException("Pet's name must not be NULL");
        }

    }

    /**
     * Deletes a Pet Entity through ID
     *
     * @param theId unique for a Pet
     */
    public void deleteById(String theId) {

        logger.info("Deleting Pet with ID: {}", theId);

        petRepository.deleteById(theId);
    }


    /**
     * Mapping of a Pet Entity to DTO
     *
     * @param pet entity
     * @return PetEntityDTO
     */
    private PetEntityDTO toDto(Pet pet){

        logger.info("Mapping Pet to DTO");

        PetEntityDTO petEntityDTO= new PetEntityDTO();

        petEntityDTO.setAge(pet.getAge());
        petEntityDTO.setSpecies(pet.getSpecies());
        petEntityDTO.setOwnerName(pet.getOwnerName());
        petEntityDTO.setId(pet.getId());
        petEntityDTO.setName(pet.getName());

        return petEntityDTO;
    }


    /**
     * Mapping of a Pet DTO to Entity
     *
     * @param petEntityDTO DTO
     * @return Pet
     */
    private Pet toEntity(PetEntityDTO petEntityDTO){

        logger.info("Mapping Pet to Entity");

        Pet pet= new Pet();
        pet.setAge(petEntityDTO.getAge());
        pet.setSpecies(petEntityDTO.getSpecies());
        pet.setOwnerName(petEntityDTO.getOwnerName());
        pet.setName(petEntityDTO.getName());

        return pet;
    }


    /**
     * Updates a Pet Entity
     *
     * @param petEntityDTO before converting it to Pet
     */
    public void update(PetEntityDTO petEntityDTO) {

        Optional<Pet> p = petRepository.findById(petEntityDTO.getId());
        if(p.isPresent()) {

            checkPet(petEntityDTO);

            Pet pet = p.get();
            pet.setName(petEntityDTO.getName());
            pet.setOwnerName(petEntityDTO.getOwnerName());
            pet.setSpecies(petEntityDTO.getSpecies());
            pet.setAge(petEntityDTO.getAge());
            petRepository.save(pet);

        }else{

            throw new RuntimeException("Pet not found during update");

        }

    }


}
