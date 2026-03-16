package MDOTM.service.impl;

import MDOTM.dao.jpa.PetRepository;
import MDOTM.dto.PetEntityDTO;
import MDOTM.model.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @Test
    void findAllTest() {

        Pet pet = new Pet();
        pet.setName("Fido");
        pet.setAge(3);

        when(petRepository.findAll()).thenReturn(List.of(pet));

        List<PetEntityDTO> result = petService.findAll();

        assertEquals(1, result.size());
        assertEquals("Fido", result.get(0).getName());

        verify(petRepository).findAll();
    }


    @Test
    void findByIdTest() {

        Pet pet = new Pet();
        pet.setName("Fido");

        when(petRepository.findById("1111111111111")).thenReturn(Optional.of(pet));

        PetEntityDTO result = petService.findById("1111111111111");

        assertEquals("Fido", result.getName());

        verify(petRepository).findById("1111111111111");
    }

    @Test
    void findByIdException() {

        when(petRepository.findById("1111111111111")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> petService.findById("1111111111111"));
    }

    @Test
    void saveTest() {

        PetEntityDTO dto = new PetEntityDTO();
        dto.setName("Leone");
        dto.setSpecies("Cane");
        dto.setOwnerName("Miuriel");
        dto.setAge(2);
        petService.save(dto);
        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void deleteByIdTest() {

        petService.deleteById("1111111111111");
        verify(petRepository).deleteById("1111111111111");

    }


}