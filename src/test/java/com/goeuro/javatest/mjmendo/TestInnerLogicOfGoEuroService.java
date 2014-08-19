package com.goeuro.javatest.mjmendo;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import com.goeuro.javatest.mjmendo.model.GeoPosition;
import com.goeuro.javatest.mjmendo.model.Location;
import com.goeuro.javatest.mjmendo.service.impl.GoEuroTestServiceImpl;

/**
 * Test the service parts individually
 * 
 * @author mjmendo.dev@gmail.com
 *
 */
public class TestInnerLogicOfGoEuroService {
	
	private CustomGoEuroService service;
	
	@Before
	public void before(){
		service = new CustomGoEuroService();
	}
	
	@Test
	public void callingRestApiShouldReturnValidJSon() throws Exception {
		String result = service.callRestApi("http://api.goeuro.com/api/v2/position/suggest/en/" + "berlin");
		Assert.assertNotNull("Result should not be null", result);
		Assert.assertFalse("Result should not be empty", result.isEmpty());
		Assert.assertTrue("JSON should be valid", isValidJSON(result)); //too much?
	}
	
	@Test
	public void parsingValidJSONShouldReturnArrayOfLocations() throws Exception{
		String json = "[{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":448103,\"key\":null,\"name\":\"Berlingo\",\"fullName\":\"Berlingo, Italy\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Italy\",\"geo_position\":{\"latitude\":45.50298,\"longitude\":10.04366},\"locationId\":147721,\"inEurope\":true,\"countryCode\":\"IT\",\"coreCountry\":true,\"distance\":null},{\"_id\":425332,\"key\":null,\"name\":\"Berlingerode\",\"fullName\":\"Berlingerode, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":51.45775,\"longitude\":10.2384},\"locationId\":124675,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":425333,\"key\":null,\"name\":\"Berlingen\",\"fullName\":\"Berlingen, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":50.23894,\"longitude\":6.71934},\"locationId\":124676,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":314826,\"key\":null,\"name\":\"Berlin Tegel\",\"fullName\":\"Berlin Tegel (TXL), Germany\",\"iata_airport_code\":\"TXL\",\"type\":\"airport\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.5548,\"longitude\":13.28903},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":314827,\"key\":null,\"name\":\"Berlin Schönefeld\",\"fullName\":\"Berlin Schönefeld (SXF), Germany\",\"iata_airport_code\":\"SXF\",\"type\":\"airport\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.3887261,\"longitude\":13.5180874},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null}]";
		List<Location> locations = service.parseJson(json);
		Assert.assertNotNull("Locations array should not be null", locations);
		Assert.assertFalse("Locations array should not be emtpy", locations.isEmpty());
		Assert.assertTrue("Locations array should have 6 locations", locations.size() == 6);
	}
	
	@Test
	public void csvFileContentShouldBeBiltWhenValidLocationsAreGiven(){
		List<Location> locations = new ArrayList<Location>();
		locations.add(new Location(){{
			setId(1L);
			setName("Name 1");
			setType("Type 1");
			setGeoPosition(new GeoPosition(11.1111111, 11.11111111));
		}});
		
		locations.add(new Location(){{
			setId(1L);
			setName("Name 2");
			setType("Type 2");
			setGeoPosition(new GeoPosition(22.2222222, 22.22222222));
		}});
		
		locations.add(new Location(){{
			setId(1L);
			setName("Name 3");
			setType("Type 3");
			setGeoPosition(new GeoPosition(33.3333333, 33.33333333));
		}});
		
		StringBuilder csvFileContent = service.buildCSVFileContent(locations);
		Assert.assertNotNull("File content should not be null", csvFileContent);
		Assert.assertTrue("File content should not be empty", csvFileContent.length() > 0);
		
		String fileContent = csvFileContent.toString();
		Assert.assertTrue("File content should contain 3 locations", fileContent.split("\n").length == 4); //headers sum 1!
		
		String header = fileContent.split("\n")[0]; //get first data row
		Assert.assertEquals("Headers of csv content should be present","Id,Name,Type, Latitude,Longitude", header);
		
		
		String firstDataRow = fileContent.split("\n")[1]; //get first data row
		Assert.assertTrue("Each line of the file should have 5 fields", firstDataRow.split(",").length == 5);
		Assert.assertEquals("The id of the location is incorrect", firstDataRow.split(",")[0], locations.get(1).getId().toString());
		
		GeoPosition gp = new GeoPosition(new Double(firstDataRow.split(",")[3]), new Double(firstDataRow.split(",")[4]));
		Assert.assertEquals("The id of the geoposition data is incorrect", gp, locations.get(0).getGeoPosition());
	}
	
	//inner class: just for testing purposes!
	private class CustomGoEuroService extends GoEuroTestServiceImpl {
		public String callRestApi(String url) throws Exception{
			return super.callRestApi(url);
		}
		
		public List<Location>parseJson(String json) throws Exception{
			return super.parseJson(json);
		}
		
		public StringBuilder buildCSVFileContent(List<Location> locations){
			return super.buildCSVFileContent(locations);
		}
	}
	
	public boolean isValidJSON(final String json) {
		   boolean valid = false;
		   try {
		      final JsonParser parser = new ObjectMapper().getJsonFactory().createJsonParser(json);
		      while (parser.nextToken() != null) {}
		      valid = true;
		   } catch (Exception jpe) {
		      //do nothing (shhhhhh...)
		   } 
		   return valid;
		}

}
