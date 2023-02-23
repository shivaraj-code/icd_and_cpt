package com.kavanant.codesystem.medicines;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

	// @Query(value = "select * from medicines_new where (name like %:NDCorName% or
	// ndc like %:NDCorName%) AND version_state='Validated'", nativeQuery = true)
	// Page<Medicine> getNDCorName(String NDCorName, Pageable pageable);

	@Query("select m from Medicine m where(m.name like %:NDCorName% or m.ndc like %:NDCorName%)AND m.versionState='Validated'")
	Page<Medicine> getNDCorName(String NDCorName, Pageable pageable);

	/*
	 * @Query(value =
	 * "select * from medicines_new where aiid=:aiid and version_state='Validated'",
	 * nativeQuery = true) Optional<Medicine> getMedicineByCodeAndVersionState(int
	 * aiid);
	 */
	@Query("select m from Medicine m where m.aiid=:aiid and m.versionState='Validated'")
	Optional<Medicine> getMedicineByCodeAndVersionState(int aiid);

	/*
	 * @Query(value = "select * from medicines_new where VersionState='Validated'",
	 * nativeQuery = true) List<Medicine> findAllActiveRecords();
	 */
	@Query("select m from Medicine m where m.versionState='Validated'")
	List<Medicine> findAllActiveRecords();
}
