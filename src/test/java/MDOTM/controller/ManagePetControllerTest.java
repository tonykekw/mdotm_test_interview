package MDOTM.controller;

import MDOTM.dto.PetEntityDTO;
import MDOTM.service.impl.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class ManagePetControllerTest {

    private PetServiceImpl petService;
    private ManagePetController controller;

    @BeforeEach
    void setup() {
        petService = mock(PetServiceImpl.class);
        controller = new ManagePetController(petService);
    }

    @Test
    void listPetsTest() {

        List<PetEntityDTO> mockPets = Collections.singletonList(new PetEntityDTO(1L, "Leone","Miuriel",5, "Dog"));
        Mockito.when(petService.findAll()).thenReturn(mockPets);
        Model model = new ExtendedModelMap();
        String view = controller.listPets(model);
        assertEquals("pets/list-pets", view);
        assertEquals(mockPets, model.getAttribute("petEntityDTOs"));
    }
    @Test
    void saveTest() {
        PetEntityDTO pet = new PetEntityDTO();
        pet.setName("Leone");
        pet.setSpecies("Dog");
        pet.setAge(3);
        PetServiceImpl service = mock(PetServiceImpl.class);
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);
        ManagePetController controller = new ManagePetController(service);
        String view = controller.savePet(pet, bindingResult, model);
        verify(service).save(pet);
        assertEquals("redirect:/pets/list", view);
    }

    @Test
    void deleteTest() {
        String redirect = controller.delete(1L);
        assertEquals("redirect:/pets/list", redirect);
        verify(petService).deleteById(1L);
    }

}

