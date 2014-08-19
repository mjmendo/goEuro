/**
 * 
 */
package com.goeuro.javatest.mjmendo.service;

/**
 * This interface represents the service that solves the challenge.
 * 
 * @author mjmendo.dev@gmail.com
 *
 */
public interface GoEuroTestService {

	/**
	 * @param searchText 
	 * 
	 * @throws GoEuroException if any error occurs while procesing searchText
	 */
	void performTestAsRequested(String searchText);

}
