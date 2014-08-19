package com.goeuro.javatest.mjmendo.main;

import java.util.logging.Logger;

import com.goeuro.javatest.mjmendo.service.GoEuroTestService;
import com.goeuro.javatest.mjmendo.service.impl.GoEuroTestServiceImpl;

/**
 * @author mjmendo.dev@gmail.com
 * 
 */
public class App {
	private final static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		
		if (args == null || args.length < 1) {
			logger.severe("Search text is expected as first argument for this program.");
			System.exit(-1);
		}

		new App().init(args[0]);
	}

	public void init(String arg) {
		GoEuroTestService goEuroTestService = new GoEuroTestServiceImpl();

		try {
			goEuroTestService.performTestAsRequested(arg);

			logger.info("Successful termination! Find result file at ./output.csv");

		} catch (Throwable e) {
			logger.severe("Unsuccesful termination: \n" + e.getMessage());
		}

		System.exit(0);
	}

}
