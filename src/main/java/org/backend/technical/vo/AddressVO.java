package org.backend.technical.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressVO {

	@JsonProperty("street")
	private String street;

	@JsonProperty("housenumber")
	private String housenNumber;

	@JsonProperty("postalcode")
	private String postalCode;

	@JsonProperty("city")
	private String city;

	@JsonProperty("geoLocation")
	private GeoLocationVO geoLocation;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenNumber() {
		return housenNumber;
	}

	public void setHousenNumber(String housenNumber) {
		this.housenNumber = housenNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public GeoLocationVO getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocationVO geoLocation) {
		this.geoLocation = geoLocation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressVO [street=");
		builder.append(street);
		builder.append(", housenNumber=");
		builder.append(housenNumber);
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(", city=");
		builder.append(city);
		builder.append(", geoLocation=");
		builder.append(geoLocation);
		builder.append("]");
		return builder.toString();
	}

}
