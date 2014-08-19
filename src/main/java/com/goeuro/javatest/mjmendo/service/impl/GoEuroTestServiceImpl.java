package com.goeuro.javatest.mjmendo.service.impl;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.goeuro.javatest.mjmendo.model.Location;
import com.goeuro.javatest.mjmendo.service.GoEuroTestService;

/**
 * This class has all the code for the challenge
 * 
 * @author mjmendo.dev@gmail.com
 *
 */
public class GoEuroTestServiceImpl implements GoEuroTestService {
	

	private final static Logger logger = Logger.getLogger(GoEuroTestServiceImpl.class.getName());
	
	private final String GOEURO_API_DEFAULT_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
	private final String OUTPUT_FILE_NAME = "./output.csv";
	private final String FILE_HEADERS = "Id,Name,Type, Latitude,Longitude";
	private final String SEPARATOR = ",";

	@Override
	public void performTestAsRequested(String searchText) {
		
		if(searchText == null || searchText.isEmpty())
			throw new GoEuroException("Search text should be present");
		
		try{
			String jsonResponse = callRestApi(GOEURO_API_DEFAULT_URL + searchText);
            List<Location> locations = parseJson(jsonResponse);
            
            if(locations.isEmpty()) logger.warning("No results were found for search text = " + searchText);
            
            StringBuilder fileContent = buildCSVFileContent(locations);
            writeToFile(fileContent);
			
		} catch (Exception e){
			throw new GoEuroException("Error occurred: " + e.getMessage());
		}
    }
	
	/**
	 * @param url to the rest api
	 * @return string containing json response.
	 * @throws Exception if any error occurs in communication with the rest api
	 */
	protected String callRestApi(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        HttpResponse result = httpClient.execute(request);
        
        StatusLine status = result.getStatusLine();
        if( status.getStatusCode() != 200 ){
        	String errorMsg = "error while calling rest api: " + status.getStatusCode() + " - " + status.getReasonPhrase();
        	logger.severe(errorMsg);
        	throw new Exception(errorMsg);
        }
        
        return EntityUtils.toString(result.getEntity(), "UTF-8");
 
	}
	
	/**
	 * @param json to parse
	 * @return list of Location objects
	 * @throws Exception if any parsing error occurs
	 */
	protected List<Location>parseJson(String json) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, Location.class));
		
	}
	
	/**
	 * @param locations array of location objects
	 * @return string builder object representing the lines of the csv file.
	 */
	protected StringBuilder buildCSVFileContent(List<Location> locations){
		StringBuilder content = new StringBuilder();
		
		
		if(locations.isEmpty()){
			content.append("No results were found. \n");
			return content;
		}
		
		content.append(FILE_HEADERS + "\n");
		
		String line;
        for (Location location : locations) {
        	line = location.getId().toString() + SEPARATOR + 
        			location.getName() + SEPARATOR + 
        			location.getType() + SEPARATOR +
        			location.getGeoPosition().getLatitude().toString() + SEPARATOR +
        			location.getGeoPosition().getLongitude().toString();
        	
			content.append(line).append("\n");
			
		}
        
        logger.info(content.toString());
        
		return content;
	}
	
	/**
	 * Writes content into a file.
	 * 
	 * @param fileContent string buffer object representing the lines of a csv file.
	 * 
	 * @throws Exception if any error occurs while writing the file.
	 */
	protected void writeToFile(StringBuilder fileContent) throws Exception {
		Path newFile = Paths.get(OUTPUT_FILE_NAME);
        try(BufferedWriter writer = Files.newBufferedWriter(
                newFile, Charset.defaultCharset())){
			writer.append(fileContent.toString());
	        writer.flush();
        } 
	}

}
