package com.estock.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estock.application.dto.CompanyDetailsDTO;
import com.estock.application.exception.CompanyNotFoundException;
import com.estock.application.exception.InvalidCompanyException;
import com.estock.application.services.CompanyDetailsService;

@RestController
@RequestMapping(value = "/e-stock/api/v1/company")
@CrossOrigin("http://localhost:4200")
public class CompanyDetailsController {
	
	@Autowired
	private CompanyDetailsService companyDetailsService;

	@PostMapping(value="/add-company")
	public ResponseEntity<CompanyDetailsDTO> addCompanyDetails(@Valid @RequestBody CompanyDetailsDTO companyDetailsDTO, BindingResult bindingResult)  {

		if(bindingResult.hasErrors())
			throw new InvalidCompanyException("Invalid Company Details!!!");
		else
			return new ResponseEntity<CompanyDetailsDTO>(companyDetailsService.saveCompanyDetails(companyDetailsDTO), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteCompany/{companyCode}")
	public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@PathVariable("companyCode") Long companyCode) {	
		return new ResponseEntity<CompanyDetailsDTO>(companyDetailsService.deleteCompanyById(companyCode), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCompanyInfoById/{companyCode}")
	public ResponseEntity<CompanyDetailsDTO> getCompanyDetailsById(@PathVariable("companyCode") Long companyCode) {
		
		if(companyDetailsService.getCompanyDetails(companyCode) == null)
			throw new CompanyNotFoundException("Invalid Company Code!! Please enter valid companyCode...");
		else	
			return new ResponseEntity<CompanyDetailsDTO>(companyDetailsService.getCompanyDetails(companyCode), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllCompanies")	
	public ResponseEntity<List<CompanyDetailsDTO>> getAllCompanies() {		
		return new ResponseEntity<List<CompanyDetailsDTO>>(companyDetailsService.getAllCompanies(), HttpStatus.OK);
	}
}
