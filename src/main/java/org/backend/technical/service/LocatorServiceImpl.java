package org.backend.technical.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.backend.technical.vo.AtmLocatorVO;
import org.backend.technical.vo.SearchCityResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class LocatorServiceImpl implements LocatorService {

	
	private static final Logger LOG = LoggerFactory.getLogger(LocatorServiceImpl.class);

	@Value("${locator.atm.url}")
	private String atmLocatorUrl;


	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public SearchCityResponseVO getLocator() {
		SearchCityResponseVO searchCityResponseVO = null;
		try {
			searchCityResponseVO = new SearchCityResponseVO();
			ResponseEntity<String> searchAtmResponse = restTemplate.exchange(atmLocatorUrl, HttpMethod.GET,null, String.class);
			searchCityResponseVO.setStatus(searchAtmResponse.getStatusCode());
			searchCityResponseVO.setResponseBody(searchAtmResponse.getBody());			
			return searchCityResponseVO;
			
		} catch (Exception e) {
			
			searchCityResponseVO = handleException(e);
			LOG.error("ERROR findCity:> {} ",searchCityResponseVO);
			return searchCityResponseVO;
		}
	}
	
	public SearchCityResponseVO handleException(Exception e) {
		
		SearchCityResponseVO searchCityResponseVO = new SearchCityResponseVO();
		searchCityResponseVO.setIsSuccess(Boolean.FALSE);

		if (e instanceof ResourceAccessException) {
			
			if (e.getCause().getClass() == org.apache.http.conn.ConnectTimeoutException.class) {
				searchCityResponseVO.setStatus(HttpStatus.GATEWAY_TIMEOUT);
				searchCityResponseVO.setMessage(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase());				
				return searchCityResponseVO;
			} else if (e.getCause().getClass() == org.apache.http.conn.HttpHostConnectException.class) {
				searchCityResponseVO.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
				searchCityResponseVO.setMessage(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
				return searchCityResponseVO;
			} else {
				searchCityResponseVO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				searchCityResponseVO.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
				return searchCityResponseVO;
			}
		} else if (e instanceof org.springframework.web.client.HttpClientErrorException) {
			
			searchCityResponseVO.setStatus(HttpStatus.valueOf(((org.springframework.web.client.HttpClientErrorException) e).getRawStatusCode()));
			searchCityResponseVO.setMessage(HttpStatus.valueOf(((org.springframework.web.client.HttpClientErrorException) e).getRawStatusCode()).getReasonPhrase());
			return searchCityResponseVO;
			
		} else {
			searchCityResponseVO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			searchCityResponseVO.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			return searchCityResponseVO;
		}
	}

	@Override
	public List<AtmLocatorVO> getAtmLocator(String cityName,String responsesBody) {
		try {
			String responseBodyPrefix = ")]}',";
			List<AtmLocatorVO> list = null;		
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			if(responsesBody.startsWith(responseBodyPrefix)) {
				list = mapper.readValue(responsesBody.replace(responseBodyPrefix,""), new TypeReference<List<AtmLocatorVO>>() {});
			}else {
				list = mapper.readValue(responsesBody, new TypeReference<List<AtmLocatorVO>>() {});
			}
			List<AtmLocatorVO> data = list.stream().filter( atmLocator -> atmLocator.getAddress().getCity().equals(cityName)).collect(Collectors.toList());
			return data;
		} catch (Exception e) {
			LOG.error("ERROR getAtmLocator:> {} ",e);
			return Collections.emptyList();
		}
		
		
	}

}
