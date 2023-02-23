package com.kavanant.codesystem.cpt;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CptCodeCategoryRepository extends JpaRepository<CptCategory, Integer> {

	@Query(value = "CALL getCptCodeDetailsByCodeOrDesc(:codeordesc)", nativeQuery = true)
	public List<CptCategory> getCptCodeByCodeOrShortOrDescWithCtgUsingProc(String codeordesc);

}
//@Query(value = "CALL getcptcode(:codeorshort)", nativeQuery = true)
//List<CptCategory> getCptCodeByCodeOrShortWithCtg(String codeorshort);

/*
 * @Query(value = "CALL getcptcode(:codeordesc)", nativeQuery = true)
 * Page<CptCategory> getCptCodeByCodeOrShortOrDescWithCtg(String codeordesc,
 * Pageable pageable);
 */
