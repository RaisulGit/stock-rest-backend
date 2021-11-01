package com.estock.application.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estock.application.dto.CompanyDetailsDTO;
import com.estock.application.dto.StockPriceDetailsDTO;
import com.estock.application.dto.StockPriceIndexDTO;
import com.estock.application.exception.CompanyNotFoundException;
import com.estock.application.model.CompanyDetails;
import com.estock.application.model.StockPriceDetails;
import com.estock.application.repository.CompanyDetailsRepository;
import com.estock.application.repository.StockPriceRepository;

@Service
public class StockMarketServiceImpl implements StockMarketService{
	@Autowired
	private StockPriceRepository stockRepository;
	
	@Autowired
    private CompanyDetailsRepository companyRepository;
	
	@Override
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		StockPriceDetails stockPriceDetails = new StockPriceDetails();
		BeanUtils.copyProperties(stockPriceDetailsDTO, stockPriceDetails);		
		stockRepository.save(stockPriceDetails);
		return stockPriceDetailsDTO;
	}

	@Override
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode) {
		List<StockPriceDetails> stockPriceDetailsList = stockRepository.findStockByCompanyCode(companyCode);
		List<StockPriceDetailsDTO> stockPriceDetailsDTOs = new ArrayList<StockPriceDetailsDTO>();
		if(stockPriceDetailsList.size()>0){
			for(StockPriceDetails stockPriceDetails: stockPriceDetailsList) {
				StockPriceDetailsDTO stockPriceDetailsDTO = new StockPriceDetailsDTO();
				BeanUtils.copyProperties(stockPriceDetails, stockPriceDetailsDTO);
				stockPriceDetailsDTOs.add(stockPriceDetailsDTO);
			}
			return stockPriceDetailsDTOs;
		}
		else {
			throw new CompanyNotFoundException("Stock details with company code: "+companyCode+ " not found");
		}
	}
	
	@Override
	public StockPriceDetailsDTO deleteStockById(Long Id) {
		Optional<StockPriceDetails> stockPriceDetails = stockRepository.findById(Id);
		if(stockPriceDetails.isPresent()){
			StockPriceDetailsDTO stockPriceDetailsDTO = new StockPriceDetailsDTO();
			BeanUtils.copyProperties(stockPriceDetails.get(), stockPriceDetailsDTO);
			stockRepository.delete(stockPriceDetails.get());
			return stockPriceDetailsDTO;
		}
		else {
			throw new CompanyNotFoundException("Stock details with Id: "+Id+ "not found");
		}
	}
	
	public StockPriceDetailsDTO getStockPriceDetailsDTO(StockPriceDetails stockDetails)	{
		return new StockPriceDetailsDTO(stockDetails.getId(), stockDetails.getCompanyCode(), stockDetails.getCurrentStockPrice(), stockDetails.getStockPriceDate(), stockDetails.getStockPriceTime());
	};	
	
	public Double getMaxStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMaxStockPrice(companyCode, startDate, endDate);
	};
	public Double getAvgStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findAvgStockPrice(companyCode, startDate, endDate);
	};
	public Double getMinStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMinStockPrice(companyCode, startDate, endDate);
	};
	
	
	@Override
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate) {
		
		StockPriceIndexDTO stockPriceIndexDto = new StockPriceIndexDTO();
		CompanyDetails companyDetails = companyRepository.findCompanyDetailsById(companyCode);
		CompanyDetailsDTO companyDetailsDTO = new CompanyDetailsDTO();
		BeanUtils.copyProperties(companyDetails, companyDetailsDTO);
		
		stockPriceIndexDto.setCompanyDto(companyDetailsDTO);
		
		List<StockPriceDetailsDTO> stockPriceList = getStockByCode(companyCode);
		stockPriceIndexDto.setStockPriceList(stockPriceList);

		Double maxStockPrice = getMaxStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setMaxStockPrice(maxStockPrice);
		
		Double avgStockPrice = getAvgStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setAvgStockPrice(avgStockPrice);

		Double minStockPrice = getMinStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setMinStockPrice(minStockPrice);

		return stockPriceIndexDto;
	}
}
