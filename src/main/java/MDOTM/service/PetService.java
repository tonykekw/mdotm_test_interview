package MDOTM.service;

import MDOTM.dto.PetEntityDTO;


import java.util.List;

public interface PetService {

	List<PetEntityDTO> findAll();

	PetEntityDTO findById(Long theId);
	
	void save(PetEntityDTO theEmployee);
	
	void deleteById(Long theId);
	
}
