package generic_Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to initialize and read data from
 * properties file
 * 
 * @author NANDINI
 */

public class PropertiesUtility {
	/**
	 * This method is used to initialize properties file
	 * 
	 * @param filepath
	 */
	private Properties property;

	public void propertiesInit(String filepath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method fetches data from properties file based on th key passed as an
	 * arguement
	 * 
	 * @param key
	 * @return
	 */

	public String readFromProperties(String key) {
		return property.getProperty(key);
	}
}
