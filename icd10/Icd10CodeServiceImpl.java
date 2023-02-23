package com.kavanant.codesystem.icd10;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class Icd10CodeServiceImpl implements Icd10CodeService {
	@Autowired
	Icd10CodeRepository icd10CodeRepository;

	/*
	 * @Override public Page<Icd10Code> getAllIcdCodesByPagination(Pageable
	 * pageable) {
	 * 
	 * return icd10CodeRepository.findAll(pageable); }
	 */

	/*
	 * @Override public List<Icd10Code> getIcdCodeByCodeOrShort(String codeOrShort,
	 * Pageable pageable) {
	 * 
	 * return icd10CodeRepository.getIcdCodeByCodeOrShort(codeOrShort,
	 * pageable).getContent(); }
	 */

	@Override
	public Icd10Code addICDCode(Icd10Code icdCode) {
		// TODO Auto-generated method stub

		Optional<Icd10Code> existingIcd = icd10CodeRepository.getIcdByCodeAndVersionState(icdCode.getIcdCode());

		if (existingIcd.isPresent()) {
			return existingIcd.get();
		} else {

			Icd10Code savedIcd = icd10CodeRepository.save(icdCode);
			savedIcd.setRefId(savedIcd.getId());
			savedIcd.setOriginalRefId(savedIcd.getId());
			// savedCpt.setVersionState("VALID");
			return icd10CodeRepository.save(savedIcd);
		}
	}

	@Override
	public Icd10Code updateICDCodeById(Icd10Code icdCode) {
		Optional<Icd10Code> existingicd = icd10CodeRepository.findById(icdCode.getId());

		existingicd.ifPresent(existingIcd -> {
			// Updating Existing Code
			existingIcd.setVersionState("InValid");
			// existingCptCode.setRetired("Y");
			icd10CodeRepository.save(existingIcd);

			// Inserting Modified Code As New One
			icdCode.setRefId(existingIcd.getId());
			icdCode.setOriginalRefId(existingIcd.getOriginalRefId());
			icdCode.setVersionState("Valid");
			icdCode.setId(0);
			icd10CodeRepository.save(icdCode);

		});

		return icdCode;

	}

	@Override
	public Optional<Icd10Code> getIcdCodeById(int id) {
		// TODO Auto-generated method stub
		return icd10CodeRepository.findById(id);
	}

	@Override
	public Page<Icd10Code> findIcdWithPagenation(Integer pageSize, Integer pageNumber) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return icd10CodeRepository.findAll(pageable);
	}

	@Override
	public Page<Icd10Code> getIcdWithSearchPagenation(String codeOrShort, Integer pageSize, Integer pageNumber,
			String sortBy) {
		// TODO Auto-generated method stub
		Pageable pageable;
		// = PageRequest.of(pageNumber, pageSize, sortBy);
		if (sortBy.equals("Oldest First")) {
			// System.out.println(sortBy + "SortBy");
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
		}

		return icd10CodeRepository.getIcdCodeByCodeOrShort(codeOrShort, pageable);
	}

	@Override
	public List<Icd10Code> getICDs() {
		// TODO Auto-generated method stub
		return icd10CodeRepository.findAllActiveRecords();
	}

}
/*
 * @Override public Page<Icd10Code> getIcdWithSearchPagenation(String
 * codeOrShort, Integer pageSize, Integer pageNumber, String sortField, String
 * sortDir) { // TODO Auto-generated method stub Pageable pageable =
 * PageRequest.of(pageNumber, pageSize, sortDir.equals("asc") ?
 * Sort.by(sortField).ascending() : Sort.by(sortField).descending()); return
 * icd10CodeRepository.getIcdCodeByCodeOrShort(codeOrShort, pageable); }
 */