package BUS;

public class Validation {
	
	public static boolean isPositiveNumber(String number) {
		try {
			double test = Double.parseDouble(number);
			return(test > 0);
		} catch (Exception e) {
			return(false);
		}
	}
	
	public static boolean isEmpty(String string) {
		return(string == null || string.isEmpty());
	}
	
	public static boolean isPhoneNumber(String phoneNumber) {
		if(phoneNumber.matches("^0\\d{9,10}$")) {
			return(true);
		}
		return(false);
	}
}
