package estock.application.com.estockapplication.boundarytestcases;

import java.io.IOException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.estock.application.dto.CompanyDetailsDTO;
import com.estock.application.dto.StockPriceDetailsDTO;
import com.estock.application.dto.StockPriceIndexDTO;
import estock.application.com.estockapplication.utiltestcases.MasterData;

public class BoundaryTests {
	private Validator validator;
	
    @BeforeEach
    public void setUp() {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@Test
	void testStockExchangeLength() throws Exception 
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setStockExchange("SE");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		Assert.assertFalse(violations.isEmpty());
	}

	@Test
	void testCompanyNameLength() throws Exception 
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setCompanyName("CO");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		Assert.assertFalse(violations.isEmpty());
		}

	@Test
	void testCompanyCEOLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setCompanyCEO("NAV");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		Assert.assertFalse(violations.isEmpty());
		}

	@Test
	void testTurnoverLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setTurnover(null);
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		Assert.assertFalse(violations.isEmpty());
		}	
	
	@Test
	void testBoardOfDirectorsLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setBoardOfDirectors("AAA");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		Assert.assertFalse(violations.isEmpty());
		}
	
	@Test
	public void testCompanyProfileLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setCompanyProfile("Ba");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		Assert.assertFalse(violations.isEmpty());
	}

    @Test
    public void testPostCompanyDetailsFailed() throws IOException {
    	CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
    	companyDetails.setCompanyName(null);
        Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
        Assert.assertFalse(violations.isEmpty());
        }
	@Test
	void testCurrentStockPriceLength() throws Exception
	{
		StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
		stockPrice.setCurrentStockPrice(null);
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
        Assert.assertFalse(violations.isEmpty());
        }
	@Test
	public void testStockPriceDateLength() throws Exception
	{
		StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
		stockPrice.setStockPriceDate(null);
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
        Assert.assertFalse(violations.isEmpty());
        }

	@Test
	void testStockPriceTimeLength() throws Exception
	{
		StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
		stockPrice.setStockPriceTime(null);
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
        Assert.assertFalse(violations.isEmpty());
        }

    @Test
    public void testPostStockPriceDetailsFailed() throws IOException {
    	StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
    	stockPrice.setId(null);
    	Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
    	Assert.assertFalse(violations.isEmpty());
    	}

	@Test
	public void testStockPriceListLength() throws Exception
	{
		StockPriceIndexDTO stockPriceIndex = MasterData.getStockPriceIndexDTO();
		stockPriceIndex.setStockPriceList(null);
        Set<ConstraintViolation<StockPriceIndexDTO>> violations = validator.validate(stockPriceIndex);
        Assert.assertFalse(violations.isEmpty());
        }
	@Test
	public void testMaxStockPriceLength() throws Exception
	{
		StockPriceIndexDTO stockPriceIndex = MasterData.getStockPriceIndexDTO();
		stockPriceIndex.setMaxStockPrice(0.1234567);
        Set<ConstraintViolation<StockPriceIndexDTO>> violations = validator.validate(stockPriceIndex);
        Assert.assertFalse(violations.isEmpty());
        }
	@Test
	public void testAvgStockPriceLength() throws Exception
	{
		StockPriceIndexDTO stockPriceIndex = MasterData.getStockPriceIndexDTO();
		stockPriceIndex.setAvgStockPrice(0.1234567);
        Set<ConstraintViolation<StockPriceIndexDTO>> violations = validator.validate(stockPriceIndex);
        Assert.assertFalse(violations.isEmpty());
        }
	@Test
	public void testMinStockPriceLength() throws Exception
	{
		StockPriceIndexDTO stockPriceIndex = MasterData.getStockPriceIndexDTO();
		stockPriceIndex.setMinStockPrice(0.1234567);
        Set<ConstraintViolation<StockPriceIndexDTO>> violations = validator.validate(stockPriceIndex);
        Assert.assertFalse(violations.isEmpty());
	}
}
