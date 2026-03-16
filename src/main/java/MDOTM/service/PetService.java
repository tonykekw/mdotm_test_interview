package MDOTM.service;

import MDOTM.dto.PetEntityDTO;


import java.util.List;

public interface PetService {

	List<PetEntityDTO> findAll();

	PetEntityDTO findById(String theId);
	
	void save(PetEntityDTO theEmployee);
	
	void deleteById(String theId);
	
}
