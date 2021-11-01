package com.estock.application.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estock.application.dto.CompanyDetailsDTO;
import com.estock.application.dto.StockPriceDetailsDTO;
import com.estock.application.exception.CompanyNotFoundException;
import com.estock.application.model.CompanyDetails;
import com.estock.application.model.StockPriceDetails;
import com.estock.application.repository.CompanyDetailsRepository;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {
	@Autowired
	private CompanyDetailsRepository repository;

	@Override
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
		CompanyDetails companyDetails = new CompanyDetails();
		BeanUtils.copyProperties(companyDetailsDTO, companyDetails);		
		repository.save(companyDetails);
		return companyDetailsDTO;
	}

	@Override
	public CompanyDetailsDTO deleteCompanyById(Long companyCode) {
		Optional<CompanyDetails> companyDetails = repository.findById(companyCode);
		if(companyDetails.isPresent()){
			CompanyDetailsDTO companyDetailsDTO = new CompanyDetailsDTO();
			BeanUtils.copyProperties(companyDetails.get(), companyDetailsDTO);
			repository.delete(companyDetails.get());
			return companyDetailsDTO;
		}
		else {
			throw new CompanyNotFoundException("Company details with company code: "+companyCode+ " not found");
		}
	}

	@Override
	public CompanyDetailsDTO getCompanyDetails(Long companyCode) {
		Optional<CompanyDetails> companyDetails = repository.findById(companyCode);
		if(companyDetails.isPresent()){
			CompanyDetailsDTO companyDetailsDto = new CompanyDetailsDTO();
			BeanUtils.copyProperties(companyDetails.get(), companyDetailsDto);
			return companyDetailsDto;
		}
		else {
			throw new CompanyNotFoundException("Company details with company code: "+companyCode+ " not found");
		}
	}

	@Override
	public List<CompanyDetailsDTO> getAllCompanies() {
		List<CompanyDetails> companyInfo = repository.findAll();
		List<CompanyDetailsDTO> companyDetailsDTOs = new ArrayList<CompanyDetailsDTO>();
		for(CompanyDetails companyDetails:companyInfo) {
			CompanyDetailsDTO companyDetailsDTO = new CompanyDetailsDTO();
			BeanUtils.copyProperties(companyDetails, companyDetailsDTO);
			companyDetailsDTOs.add(companyDetailsDTO);
		}
		return companyDetailsDTOs;
	}
	
}
