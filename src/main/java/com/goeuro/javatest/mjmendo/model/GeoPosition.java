package com.goeuro.javatest.mjmendo.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Use this class to store geo position data.
 * 
 * @author chelazo
 *
 */
public class GeoPosition {
	
	private Double latitude;
	private Double longitude;

	
	@JsonCreator
    public GeoPosition(@JsonProperty("latitude") Double latitude, @JsonProperty("longitude") Double longitude)
    {
      this.latitude = latitude;
      this.longitude = longitude;
    }


	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoPosition other = (GeoPosition) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}
	
	
	
}
