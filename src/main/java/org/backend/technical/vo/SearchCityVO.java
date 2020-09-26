package org.backend.technical.vo;

public class SearchCityVO {

	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchCityVO [cityName=");
		builder.append(cityName);
		builder.append("]");
		return builder.toString();
	}

}
