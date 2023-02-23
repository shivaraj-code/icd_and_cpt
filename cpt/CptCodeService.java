package com.kavanant.codesystem.cpt;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CptCodeService {

	Optional<CptCode> getCptCodeById(int id);

	// List<CptCode> getCptCodeByCodeOrShortOrDesc(String codeOrShortOrDesc,
	// Pageable pageable);

	// Page<CptCategory> getCptCodeByCodeOrShortOrDescWithCtg(String codeordesc,
	// Pageable pageable);

	CptCode addCptCode(CptCode cptCode);

	CptCode updateCptCodeById(CptCode cptCode);

	Page<CptCode> findCptWithPagenation(Integer pageSize, Integer pageNumber);

	// Page<CptCode> getCptWithSearchPagenation(String codeOrShort, Integer
	// pageSize, Integer pageNumber);

	List<CptCode> getCPTs();

	Page<CptCode> getCptWithSearchPagenation(String codeOrShort, Integer pageSize, Integer pageNumber, String sortBy);

	Page<CptCategory> getCptCodeByCodeOrShortOrDescWithCtg(String codeordesc, Pageable pageable);

	

	// Page<CptCode> getAllCptCodesByPagination(Pageable pageable);

}
