package com.kavanant.codesystem.icd10;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Icd10CodeRepository extends JpaRepository<Icd10Code, Integer> {

	@Query(value = "select * from icd10_codes where (icd_code like %:codeOrShort% or short_desc like %:codeOrShort%)AND version_state='valid'", nativeQuery = true)
	public Page<Icd10Code> getIcdCodeByCodeOrShort(String codeOrShort, Pageable pageable);

	@Query(value = "select * from icd10_codes where icd_code=:icdCode and version_state='Valid'", nativeQuery = true)
	Optional<Icd10Code> getIcdByCodeAndVersionState(String icdCode);

	@Query(value = "select * from icd10_codes where version_state='valid'", nativeQuery = true)
	public List<Icd10Code> findAllActiveRecords();

	// public Page<Icd10Code> getIcdCodeByCodeOrShort(Pageable pageable);

}
