package com.kavanant.codesystem.cpt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.kavanant.codesystem.util.ListUtils;

@Service
public class CptCodeServiceImpl implements CptCodeService {

	@Autowired
	CptCodeRepository cptCodeRepository;

	@Autowired
	CptCodeCategoryRepository cptCodeCategoryRepository;

	@Override
	public Optional<CptCode> getCptCodeById(int id) {

		return cptCodeRepository.findById(id);
	}

	@Override
	public Page<CptCategory> getCptCodeByCodeOrShortOrDescWithCtg(String codeordesc, Pageable pageable) {

		List<CptCategory> cptList = cptCodeCategoryRepository.getCptCodeByCodeOrShortOrDescWithCtgUsingProc(codeordesc);

		// List<CptCategory> filteredCptList= ListUtils.getPage(cptList,
		// pageable.getPageNumber()+1, pageable.getPageSize());
		// return new PageImpl<CptCategory>(filteredCptList,pageable,cptList.size());

		Sort sort = pageable.getSort();

		List<CptCategory> sortedItems = cptList.stream().sorted((o1, o2) -> {
			for (Order order : sort) {
				int comparisonResult = compareItemsByProperty(o1, o2, order.getProperty());
				if (comparisonResult != 0) {
					return order.isAscending() ? comparisonResult : -comparisonResult;
				}
			}
			return 0;
		}).collect(Collectors.toList());

		int startIndex = pageable.getPageNumber() * pageable.getPageSize();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), sortedItems.size());
		List<CptCategory> pageOfItems = sortedItems.subList(startIndex, endIndex);

		return new PageImpl<>(pageOfItems, pageable, sortedItems.size());

	}

	private int compareItemsByProperty(CptCategory o1, CptCategory o2, String property) {

		switch (property) {
		case "short_name":
			return o1.getShort_name().compareTo(o2.getShort_name());

		case "originalRefId":
			return o1.getOriginal_ref_id().compareTo(o2.getOriginal_ref_id());

		// cptMajorCategory
		// ... add more cases for additional properties
		default:
			throw new IllegalArgumentException("Unknown sort property: " + property);
		}
	}

	/*
	 * @Override public List<CptCategory>
	 * getCptCodeByCodeOrShortOrDescWithCtg(String codeordesc, Pageable pageable) {
	 * 
	 * List<CptCategory> cptList =
	 * cptCodeCategoryRepository.getCptCodeByCodeOrShortOrDescWithCtgUsingProc(
	 * codeordesc); return ListUtils.getPage(cptList, pageable.getPageNumber() + 1,
	 * pageable.getPageSize());
	 * 
	 * }
	 */

	@Override
	public CptCode addCptCode(CptCode cptCode) {
		Optional<CptCode> existingCptCode = cptCodeRepository.getCptCodeByCodeAndVersionState(cptCode.getCode());

		if (existingCptCode.isPresent()) {
			return existingCptCode.get();
		} else {

			CptCode savedCpt = cptCodeRepository.save(cptCode);
			savedCpt.setRefId(savedCpt.getId());
			savedCpt.setOriginalRefId(savedCpt.getId());
			// savedCpt.setVersionState("VALID");
			return cptCodeRepository.save(savedCpt);
		}
	};

	@Override
	public CptCode updateCptCodeById(CptCode cptCode) {

		Optional<CptCode> existingCpt = cptCodeRepository.findById(cptCode.getId());

		existingCpt.ifPresent(existingCptCode -> {
			// Updating Existing Code
			existingCptCode.setVersionState("InValid");
			// existingCptCode.setRetired("Y");
			cptCodeRepository.save(existingCptCode);

			// Inserting Modified Code As New One
			cptCode.setRefId(existingCptCode.getId());
			cptCode.setOriginalRefId(existingCptCode.getOriginalRefId());
			cptCode.setVersionState("Valid");
			cptCode.setId(0);
			cptCodeRepository.save(cptCode);
			// System.out.println(cptCode.toString());
		});

		return cptCode;
	}

	@Override
	public Page<CptCode> findCptWithPagenation(Integer pageSize, Integer pageNumber) {
		// TODO Auto-generated method
		// stub
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return cptCodeRepository.findAll(pageable);
	}

	@Override
	public Page<CptCode> getCptWithSearchPagenation(String codeOrShort, Integer pageSize, Integer pageNumber,
			String sortBy) {
		// TODO Auto-generated method stub
		Pageable pageable;
		// = PageRequest.of(pageNumber, pageSize, sortBy);
		if (sortBy.equals("Oldest First")) {
			// System.out.println(sortBy + "SortBy");
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "short_name"));
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "short_name"));
		}
		return cptCodeRepository.getCptCodeByCodeOrShort(codeOrShort, pageable);
	}

	@Override
	public List<CptCode> getCPTs() {
		// TODO Auto-generated method stub
		return cptCodeRepository.findAllActiveRecords();
	}

	

	/*
	 * @Override public Page<CptCode> getAllCptCodesByPagination(Pageable pageable)
	 * {
	 * 
	 * return cptCodeRepository.findAll(pageable); }
	 */

	/*
	 * @Override public List<CptCode> getCptCodeByCodeOrShortOrDesc(String
	 * shortName, Pageable pageable) {
	 * 
	 * return cptCodeRepository.getCptCodeByCodeOrShortOrDesc(shortName,
	 * pageable).getContent(); }
	 */

	/*
	 * @Override public Page<CptCode> getAllCptCodesByPagination(Pageable pageable)
	 * { // TODO Auto-generated method stub return
	 * cptCodeRepository.findAll(pageable); }
	 */

	/*
	 * public Optional<CptCode> getCptCodeByCode(String code) {
	 * 
	 * return cptCodeRepository.getCptCodeByCode(code); }
	 */

	/*
	 * public List<CptCode> getCptCodeByDate(Date date,Pageable pageable) {
	 * 
	 * return cptCodeRepository.getCptCodeByDate(date,pageable).getContent(); }
	 */

	/*
	 * public List<CptCode> getAllCptCodes() {
	 * 
	 * return cptCodeRepository.findAll(); }
	 */

}
