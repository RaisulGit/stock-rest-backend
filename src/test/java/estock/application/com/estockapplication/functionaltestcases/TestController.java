package estock.application.com.estockapplication.functionaltestcases;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.estock.application.controller.CompanyDetailsController;
import com.estock.application.controller.StockPriceDetailsController;
import com.estock.application.dto.CompanyDetailsDTO;
import com.estock.application.services.CompanyDetailsService;
import com.estock.application.services.StockMarketService;

import estock.application.com.estockapplication.utiltestcases.MasterData;

@WebMvcTest({CompanyDetailsController.class, StockPriceDetailsController.class})
@AutoConfigureMockMvc
public class TestController {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CompanyDetailsService companyDetailsService;

	@MockBean
	private StockMarketService stockMarketService;
	
	@Test 
	public void testAddCompany() throws Exception 
	{ 
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
	
        Mockito.when(companyDetailsService.saveCompanyDetails(companyDto)).thenReturn(companyDto);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	}
}
