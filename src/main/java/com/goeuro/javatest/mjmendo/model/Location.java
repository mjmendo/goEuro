package com.goeuro.javatest.mjmendo.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Use this class main entity to store json response from goEuro suggest location api.
 * 
 * @author chelazo
 *
 */
public class Location {
	
	@JsonProperty("_id") 
	private Long id;
	private String key;
	private String name;
	private String fullName;
	@JsonProperty("iata_airport_code") 
	private String iataAirportCode;
	private String type;
	private String country;
	@JsonProperty("geo_position") 
	private GeoPosition geoPosition;
	private Long locationId;
	private Boolean inEurope;
	private String countryCode;
	private Boolean coreCountry;
	private String distance;
	
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}



	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}



	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	/**
	 * @return the iataAirportCode
	 */
	public String getIataAirportCode() {
		return iataAirportCode;
	}



	/**
	 * @param iataAirportCode the iataAirportCode to set
	 */
	public void setIataAirportCode(String iataAirportCode) {
		this.iataAirportCode = iataAirportCode;
	}



	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}



	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}



	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}



	/**
	 * @return the geoPosition
	 */
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}



	/**
	 * @param geoPosition the geoPosition to set
	 */
	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}



	/**
	 * @return the locationId
	 */
	public Long getLocationId() {
		return locationId;
	}



	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}



	/**
	 * @return the inEurope
	 */
	public Boolean getInEurope() {
		return inEurope;
	}



	/**
	 * @param inEurope the inEurope to set
	 */
	public void setInEurope(Boolean inEurope) {
		this.inEurope = inEurope;
	}



	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}



	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	/**
	 * @return the coreCountry
	 */
	public Boolean getCoreCountry() {
		return coreCountry;
	}



	/**
	 * @param coreCountry the coreCountry to set
	 */
	public void setCoreCountry(Boolean coreCountry) {
		this.coreCountry = coreCountry;
	}



	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}



	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [id=" + id + ", key=" + key + ", fullName=" + fullName
				+ ", iataAirportCode=" + iataAirportCode + ", type=" + type
				+ ", country=" + country + ", geoPosition=" + geoPosition
				+ ", locationId=" + locationId + ", inEurope=" + inEurope
				+ ", countryCode=" + countryCode + ", coreCountry="
				+ coreCountry + ", distance=" + distance + "]";
	}
	
	
	

}
