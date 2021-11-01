package com.estock.application.services;

import java.time.LocalDate;
import java.util.List;
import com.estock.application.dto.StockPriceDetailsDTO;
import com.estock.application.dto.StockPriceIndexDTO;


public interface StockMarketService {
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO);
	public StockPriceDetailsDTO deleteStockById(Long Id);
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode);
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate);
}
