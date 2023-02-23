package com.kavanant.codesystem.medicines;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	MedicineRepository medicinerepository;

	@Override
	public Page<Medicine> getAllMedicineByPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		return medicinerepository.findAll(pageable);
	}

	@Override
	public Page<Medicine> getNDCorName(String NDCorName, Integer pageSize, Integer pageNumber, String sortBy,
			String sortOrder) {
		// TODO Auto-generated method stub
		Pageable pageable;
		if (sortBy.equals("Ascending")) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "NDC"));
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "NDC"));
		}
		return medicinerepository.getNDCorName(NDCorName, pageable);
	}

	@Override
	public List<Medicine> getMedicines() {
		// TODO Auto-generated method stub
		return medicinerepository.findAllActiveRecords();
	}

	public Medicine getMedicineByAiid(int aiid) {
		// TODO Auto-generated method stub
		return medicinerepository.findById(aiid).get();
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) {
		Optional<Medicine> existingmed = medicinerepository.findById(medicine.getAiid());

		existingmed.ifPresent(existingMed -> {
			// Updating Existing Code
			existingMed.setVersionState("InValid");
			existingMed.setRetired("Y");
			// existingMed.setRetiredOn("null");
			medicinerepository.save(existingMed);

			// Inserting Modified Code As New One
			medicine.setRefId(existingMed.getAiid());
			medicine.setOriginalRefId(existingMed.getOriginalRefId());
			medicine.setVersionState("Validated");
			medicine.setRetired("N");
			// medicine.setRetiredOn(CurrentDateTimeProvider);
			medicine.setAiid(0);
			medicinerepository.save(medicine);

		});

		return medicine;
	}

	@Override
	public Medicine addMedicine(Medicine medrequest) {
		// TODO Auto-generated method stub
		Optional<Medicine> existingMedicine = medicinerepository.getMedicineByCodeAndVersionState(medrequest.getAiid());

		if (existingMedicine.isPresent()) {
			return existingMedicine.get();
		} else {
			Medicine savedMed = medicinerepository.save(medrequest);
			savedMed.setRefId(savedMed.getAiid());
			savedMed.setOriginalRefId(savedMed.getAiid());

			return medicinerepository.save(savedMed);
		}
	}

}
