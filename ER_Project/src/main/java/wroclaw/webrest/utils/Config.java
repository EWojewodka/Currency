package wroclaw.webrest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	
	static {
		loadConfig();
	}
	
	private static Properties properties;

	/**
	 * This method allows configure stream of URL configure by
	 * <b>Config.properties</b>
	 */
	public static void loadConfig() {
		properties = new Properties();
		InputStream input = null;

		input = Config.class.getResourceAsStream("/Config.properties");
		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getString(String key) {
		return properties.getProperty(key);
	}
}
