package com.senla.bookshop.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class Config {
	private static Properties logProperty = new Properties();
	private static String logFile;

	@SuppressWarnings("static-access")
	public Config(String logFile) {
		this.logFile = logFile;
	}
	public void init(){
		try {
			logProperty.load(new FileInputStream(logFile));
			PropertyConfigurator.configure(logProperty);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
