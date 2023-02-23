package com.kavanant.codesystem.medicines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicineController {

	@Autowired
	private MedicineService medicineservice;

	@GetMapping("/medicine/pagination")
	public ResponseEntity<Page<Medicine>> getMedicineByPagination(
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Medicine> medicine = medicineservice.getAllMedicineByPagination(pageable);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Page<Medicine>>(medicine, headers, HttpStatus.OK);
	}

	@GetMapping("/medicine/search")
	public ResponseEntity<Page<Medicine>> getNDCorName(
			@RequestParam String NDCorName,
			@RequestParam(defaultValue = "10") Integer pageSize, 
			@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "sortBy", required = false, defaultValue = "Newest First") String sortBy,
			@RequestParam(value = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {
		HttpHeaders headers = new HttpHeaders();
		Page<Medicine> medResponse = medicineservice.getNDCorName(NDCorName, pageSize, pageNumber, sortBy, sortOrder);
		return new ResponseEntity<>(medResponse, headers, HttpStatus.OK);
	}

	@GetMapping("/medicine/list/{aiid}")
	public ResponseEntity<Medicine> getMedicineByAiid(@PathVariable int aiid) {

		Medicine medicines = medicineservice.getMedicineByAiid(aiid);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Medicine>(medicines, headers, HttpStatus.OK);
	}

	@PutMapping("/medicine/update")
	public ResponseEntity<Medicine> updateMedicine(@RequestBody Medicine medicine) {

		Medicine updatedMed = medicineservice.updateMedicine(medicine);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Medicine>(updatedMed, headers, HttpStatus.OK);
	}

	@PostMapping("/medicine/add")
	public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medrequest) {
		Medicine medresponse = medicineservice.addMedicine(medrequest);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Medicine>(medresponse, headers, HttpStatus.CREATED);
	}

	@GetMapping(path = "/medicine/list")
	public ResponseEntity<List<Medicine>> getMedicines() {

		List<Medicine> medlist = medicineservice.getMedicines();
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<Medicine>>(medlist, headers, HttpStatus.OK);
	}

}
