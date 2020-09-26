package org.backend.technical.service;

import java.util.List;

import org.backend.technical.vo.AtmLocatorVO;
import org.backend.technical.vo.SearchCityResponseVO;

public interface LocatorService {

	public SearchCityResponseVO getLocator();
	
	public List<AtmLocatorVO> getAtmLocator(String cityName,String responsesBody);
}
