package generic_Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class contains reuasble methods to perform java related operations
 * 
 * @author NANDINI
 */

public class JavaUtility {
	/**
	 * This method is used to generate random numbers within specified limit
	 * 
	 * @param limit
	 * @return
	 */

	public int generateRandom(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
	}

	/**
	 * This method fetches current date and time in specified format
	 * 
	 * @return
	 */

	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
		return sdf.format(date);
	}

}
