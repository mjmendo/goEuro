package com.goeuro.javatest.mjmendo;

import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.goeuro.javatest.mjmendo.service.GoEuroTestService;
import com.goeuro.javatest.mjmendo.service.impl.GoEuroException;
import com.goeuro.javatest.mjmendo.service.impl.GoEuroTestServiceImpl;

/**
 * Test the main method of the service
 * 
 * @author chelazo
 *
 */
public class TestGoEuropeServiceMainMethod {
	
	private GoEuroTestService service;
	
	@Before
	public void before(){
		service = new GoEuroTestServiceImpl();
	}

	@Test
	public void mainMethodShouldCreateAnOutputFile() {
		service.performTestAsRequested("berlin");
		File outputFile = new File("./output.csv");
		Assert.assertTrue("Output file does not exist", outputFile.exists());
		Assert.assertTrue("Output file could not be deleted in file system", outputFile.delete());
	}
	
	@Test(expected=GoEuroException.class)
	public void serviceShouldThrowExceptionWhenNoSearchTextIsProvided(){
		service.performTestAsRequested("");
	}
	
}
