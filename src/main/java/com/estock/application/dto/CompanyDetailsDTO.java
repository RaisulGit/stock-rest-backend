package com.estock.application.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyDetailsDTO {
	private Long companyCode;

	@NotNull
	@Size(min = 5, max = 50)
	private String stockExchange;

	@NotNull
	@Size(min = 5, max = 50)
	private String companyName;

	@NotNull
	@Size(min = 3, max = 50)
	private String companyCEO;

	@NotNull
	@Column(precision = 10, scale = 2)
	private Double turnover;

	@NotNull
	@Size(min = 5, max = 200)
	private String boardOfDirectors;

	@NotNull
	@Size(min = 5, max = 255)
	private String companyProfile;

	public CompanyDetailsDTO() {
		super();
	}

	public CompanyDetailsDTO(Long companyCode, String stockExchange, String companyName, String companyCEO,
			Double turnover, String boardOfDirectors, String companyProfile) {
		super();
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.turnover = turnover;
		this.boardOfDirectors = boardOfDirectors;
		this.companyProfile = companyProfile;
	}

	public Long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}

	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}
}
