package com.kavanant.codesystem.icd10;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface Icd10CodeService {

	// List<Icd10Code> getIcdCodeByCodeOrShort(String codeOrShort, Pageable
	// pageable);

	Icd10Code addICDCode(Icd10Code icdCode);

	Icd10Code updateICDCodeById(Icd10Code icdCode);

	Optional<Icd10Code> getIcdCodeById(int id);

	Page<Icd10Code> findIcdWithPagenation(Integer pageSize, Integer pageNumber);

	// Page<Icd10Code> getIcdWithSearchPagenation(String codeOrShort, Integer
	// pageSize, Integer pageNumber);

	List<Icd10Code> getICDs();

	Page<Icd10Code> getIcdWithSearchPagenation(String codeOrShort, Integer pageSize, Integer pageNumber, String sortBy);

}
//Page<Icd10Code> getAllIcdCodesByPagination(Pageable pageable);