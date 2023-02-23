package com.kavanant.codesystem.medicines;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MedicineService {

	Page<Medicine> getAllMedicineByPagination(Pageable pageable);

	List<Medicine> getMedicines();

	Medicine getMedicineByAiid(int aiid);

	Medicine updateMedicine(Medicine medicine);

	Medicine addMedicine(Medicine medrequest);

	Page<Medicine> getNDCorName(String NDCorName, Integer pageSize, Integer pageNumber, String sortBy,
			String sortOrder);

}
