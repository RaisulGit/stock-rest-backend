package com.estock.application.controller;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.estock.application.dto.StockPriceDetailsDTO;
import com.estock.application.dto.StockPriceIndexDTO;
import com.estock.application.exception.InvalidStockException;
import com.estock.application.exception.StockNotFoundException;
import com.estock.application.services.StockMarketService;

@RestController
@RequestMapping (value = "/e-stock/api/v1/stock/")
@CrossOrigin("http://localhost:4200")
public class StockPriceDetailsController {
	
	@Autowired
	private StockMarketService stockMarketService;
	
	@PostMapping(value="/add-stock")
	public ResponseEntity<StockPriceDetailsDTO> addStockDetails(@Valid @RequestBody StockPriceDetailsDTO stockPriceDetailsDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			throw new InvalidStockException("Invalid Stock Details!!!");
		else
			return new ResponseEntity<StockPriceDetailsDTO>(stockMarketService.saveStockPriceDetails(stockPriceDetailsDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStockByCompanyCode/{companyCode}")
	public ResponseEntity<List<StockPriceDetailsDTO>> getStockByCompanyCode(@PathVariable Long companyCode) {
		List<StockPriceDetailsDTO> 	stockByCompanyCode = stockMarketService.getStockByCode(companyCode);
		return new ResponseEntity<List<StockPriceDetailsDTO>>(stockByCompanyCode, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStockPriceIndex/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<StockPriceIndexDTO> displayStockPriceIndex(@PathVariable Long companyCode,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		if(stockMarketService.getStockPriceIndex(companyCode, startDate, endDate) == null)
			throw new StockNotFoundException("Invalid Stock Code or Date!!! Please enter valid Details...");
		else	
			return new ResponseEntity<StockPriceIndexDTO>(stockMarketService.getStockPriceIndex(companyCode, startDate, endDate), HttpStatus.OK);			
	}
	
	@DeleteMapping(value = "/deleteStockById/{Id}")
	public ResponseEntity<StockPriceDetailsDTO> deleteStockById(@PathVariable Long Id) {
		return new ResponseEntity<StockPriceDetailsDTO>(stockMarketService.deleteStockById(Id), HttpStatus.OK);		
	}
}
