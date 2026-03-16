package MDOTM.controller;

import MDOTM.dto.PetEntityDTO;
import MDOTM.exception.PetNotValidException;
import MDOTM.service.impl.PetServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Tag(name = "Pet Controller", description = "Pet Directory")
@Controller
@RequestMapping("/pets")
public class ManagePetController {

    @Autowired
    private  PetServiceImpl petServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(ManagePetController.class);


    public ManagePetController(PetServiceImpl petServiceImpl) {
        this.petServiceImpl = petServiceImpl;
    }

    @GetMapping("/list")
    public String listPets(Model theModel) {

        logger.info("Request GET /list");

        List<PetEntityDTO> thePets = petServiceImpl.findAll();
        theModel.addAttribute("petEntityDTOs", thePets);

        return "pets/list-pets";
    }

    @Tag(name = "Pets API", description = "Endpoint JSON Swagger")
    @GetMapping("/api/list")
    @Operation(summary = "Returns all Pets")
    @ResponseBody
    public List<PetEntityDTO> listPetsJApi() {
        return petServiceImpl.findAll();
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        PetEntityDTO petEntityDTO= new PetEntityDTO();
        theModel.addAttribute("petEntityDTO", petEntityDTO);

        return "pets/pet-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("petId") String theId,Model theModel) {


        PetEntityDTO petEntityDTO = petServiceImpl.findById(theId);

        theModel.addAttribute("petEntityDTO", petEntityDTO);

        return "pets/pet-update";
    }

    @PostMapping("/save")
    public String savePet(@ModelAttribute("petEntityDTO") PetEntityDTO thePet,  BindingResult result,
                          Model model) {

        logger.info("Request POST /save");

        try {
            petServiceImpl.save(thePet);
        } catch (PetNotValidException ex) {
            result.reject("error.pet", ex.getMessage());
            model.addAttribute("petEntityDTO", thePet);
            return "pets/pet-form";
        }

        return "redirect:/pets/list";

    }


    @Tag(name = "Pets API", description = "Endpoint JSON Swagger")
    @PostMapping("/api/save")
    @Operation(summary = "Saves one Pet")
    @ResponseBody
    public ResponseEntity<?> saveApi(@RequestBody PetEntityDTO thePet) {

        try {
            petServiceImpl.save(thePet);
            return ResponseEntity.ok(thePet);
        } catch (PetNotValidException ex) {

            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }

    }


    @PostMapping("/delete")
    public String delete(@RequestParam("petId") String theId) {

        logger.info("Request POST /delete");
        petServiceImpl.deleteById(theId);
        return "redirect:/pets/list";

    }

    @Tag(name = "Pets API", description = "Endpoint JSON Swagger")
    @PostMapping("/api/delete")
    @Operation(summary = "Deletes one Pet")
    @ResponseBody
    public String deleteApi(@RequestBody String theId) {
         petServiceImpl.deleteById(theId);
         return theId;
    }


    @Tag(name = "Pets API", description = "Endpoint JSON Swagger")
    @GetMapping("/api/onePet")
    @Operation(summary = "Gets one Pet")
    @ResponseBody
    public PetEntityDTO getOne(@RequestParam("petId") String theId) {
       return petServiceImpl.findById(theId);
    }


    @PostMapping("/update")
    public String updatePet(@ModelAttribute("petEntityDTO") PetEntityDTO thePet,  BindingResult result,
                            Model model) {

        logger.info("Request POST /update");

        try {
            petServiceImpl.update(thePet);
        } catch (PetNotValidException ex) {
            result.reject("error.pet", ex.getMessage());
            model.addAttribute("petEntityDTO", thePet);
            return "pets/pet-update";
        }

        return "redirect:/pets/list";
    }

    @Tag(name = "Pets API", description = "Endpoint JSON Swagger")
    @PostMapping("/api/update")
    @Operation(summary = "Updates one Pet")
    @ResponseBody
    public ResponseEntity<?> updateApi(@ModelAttribute("petEntityDTO") PetEntityDTO thePet) {


        try {
            petServiceImpl.update(thePet);
            return ResponseEntity.ok(thePet);
        } catch (PetNotValidException ex) {

            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }

    }

}
