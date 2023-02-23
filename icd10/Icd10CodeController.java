package com.kavanant.codesystem.icd10;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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

import com.kavanant.codesystem.cpt.CptCode;

@RestController

public class Icd10CodeController {

	@Autowired
	Icd10CodeService icd10CodeService;

	@GetMapping("/icdcode/{id}")
	public ResponseEntity<Optional<Icd10Code>> getIcdCodes(@PathVariable int id) {
		Optional<Icd10Code> icdCode = icd10CodeService.getIcdCodeById(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Optional<Icd10Code>>(icdCode, headers, HttpStatus.OK);
	}

	@GetMapping("/icdcode/list")
	public List<Icd10Code> getICDs() {
		return icd10CodeService.getICDs();
	}

	@GetMapping("/icdcode/pagination")
	public Page<Icd10Code> getIcdCodesByPagination(@RequestParam(defaultValue = "10") Integer pageSize,

			@RequestParam(defaultValue = "0") Integer pageNumber) {
		return icd10CodeService.findIcdWithPagenation(pageSize, pageNumber);
	}

	@GetMapping("/icdcode/search")
	public Page<Icd10Code> getIcdCodeByCodeOrShort(@RequestParam(value = "searchstring") String codeOrShort,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "sortBy", required = false, defaultValue = "Newest First") String sortBy) {
		return icd10CodeService.getIcdWithSearchPagenation(codeOrShort, pageSize, pageNumber, sortBy);
	}

	@PostMapping("/icdcode")
	public ResponseEntity<Icd10Code> addICDCode(@RequestBody Icd10Code icdCode) {

		Icd10Code icdCodeResponse = icd10CodeService.addICDCode(icdCode);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Icd10Code>(icdCodeResponse, headers, HttpStatus.OK);

	}

	@PutMapping("/icdcode")
	public ResponseEntity<Icd10Code> updateICDCodeById(@RequestBody Icd10Code icdCode) {

		Icd10Code updatedIcd = icd10CodeService.updateICDCodeById(icdCode);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Icd10Code>(updatedIcd, headers, HttpStatus.OK);
	}
	/*
	 * @GetMapping("/icdcode/search") public ResponseEntity<List<Icd10Code>>
	 * getIcdCodeByCodeOrShort(
	 * 
	 * @RequestParam(value = "searchstring", required = false) String codeOrShort,
	 * 
	 * @RequestParam(defaultValue = "10") Integer
	 * pageSize, @RequestParam(defaultValue = "0") Integer pageNumber) { Pageable
	 * pageable = PageRequest.of(pageNumber, pageSize); HttpHeaders headers = new
	 * HttpHeaders(); List<Icd10Code> icdCodeResponse =
	 * icd10CodeService.getIcdCodeByCodeOrShort(codeOrShort, pageable); return new
	 * ResponseEntity<>(icdCodeResponse, headers, HttpStatus.OK);
	 * 
	 * }
	 */

	/*
	 * @GetMapping("/icdcode/pagination") public ResponseEntity<Page<Icd10Code>>
	 * getAllIcdCodesByPagination(Pageable pageable) {
	 * 
	 * // Pageable pageable = PageRequest.of(pageNumber, pageSize); Page<Icd10Code>
	 * icdCodes = icd10CodeService.getAllIcdCodesByPagination(pageable); HttpHeaders
	 * headers = new HttpHeaders(); return new
	 * ResponseEntity<Page<Icd10Code>>(icdCodes, headers, HttpStatus.OK); }
	 */

}
