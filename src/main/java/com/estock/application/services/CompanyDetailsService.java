package com.estock.application.services;

import java.util.List;

import com.estock.application.dto.CompanyDetailsDTO;

public interface CompanyDetailsService {
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO);
	public CompanyDetailsDTO deleteCompanyById(Long companyCode);
	public CompanyDetailsDTO getCompanyDetails(Long companyCode);
	public List<CompanyDetailsDTO> getAllCompanies();
}
