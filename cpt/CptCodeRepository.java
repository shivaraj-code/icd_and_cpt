package com.kavanant.codesystem.cpt;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CptCodeRepository extends JpaRepository<CptCode, Integer> {

	@Query(value = "select * from cpt_short where (short_name like %:codeOrShort% or code like %:codeOrShort%)AND version_state='valid'", nativeQuery = true)
	public Page<CptCode> getCptCodeByCodeOrShort(String codeOrShort, Pageable pageable);

	@Query(value = "select * from cpt_short where date(createdDate)=:date", nativeQuery = true)
	public Page<CptCode> getCptCodeByDate(Date date, Pageable pageable);

	@Query(value = "select * from cpt_short where code=:code", nativeQuery = true)
	public Optional<CptCode> getCptCodeByCode(String code);

	@Query(value = "select * from cpt_short where code=:code and retired_no='N'", nativeQuery = true)
	public Optional<CptCode> getCptCodeByCodeAndRetiredNo(String code);

	// @Query(value="select * from cpt_short where Code=:code and
	// Status='Y'",nativeQuery=true)
	// public Optional<CptCode> getCptCodeByCodeAndStatusYes(String code);

	@Query(value = "select * from cpt_short where code=:code and version_state='valid'", nativeQuery = true)
	public Optional<CptCode> getCptCodeByCodeAndVersionState(String code);

	@Query(value = "select * from cpt_short  where version_state='valid'", nativeQuery = true)
	public List<CptCode> findAllActiveRecords();

}
