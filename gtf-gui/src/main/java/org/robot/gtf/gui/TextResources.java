package org.robot.gtf.gui;

public class TextResources {

	
	private static final String APP_NAME = "Generic Testdata Framework GUI";
	
	private static final String APP_VERSION = "Version 0.1";
	
	private static final String ABOUT_TEXT = "This is the Generic Testdata Framework GUI";
	
	
	public static String getAppName() {
		return APP_NAME;
	}
	
	public static String getAppVersion() {
		return APP_VERSION;
	}
	
	public static String getAboutText() {
		return "<b>" + APP_NAME + " " + APP_VERSION + "</b><br>" + ABOUT_TEXT;
	}
	
	
}
