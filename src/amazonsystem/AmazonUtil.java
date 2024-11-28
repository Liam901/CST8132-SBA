package amazonsystem;


public class AmazonUtil {
	
	static public float convertStrToFloat(String str) throws AmazonException {
		if (isValidFloat(str)) {
			return Float.parseFloat(str);
		} else {
			throw new AmazonException("Invalid input: please input a float");
		}
	}
	
	static public boolean isValidFloat(String str) {
		if(str.isBlank() || str.isEmpty()) {
			return false;
		} else {
			try {
				Float.parseFloat(str);
			} catch (Exception e) {
				return false;
			}
			if (Float.parseFloat(str) <= 0) {
				return false;
			} else {
			return true;
			}
		}
	}
	
	static public boolean isValidInt(String str) {
		if(str.isBlank() || str.isEmpty()) {
			return false;
		} else {
			try {
				Integer.parseInt(str);
			} catch (Exception e) {
				return false;
			}
			if (Integer.parseInt(str) < 0) {
				return false;
			} else {
			return true;
			}
		}
	}
	
	static public boolean isValidInt(String str, int max, int min) {
		int i;
		if(str.isBlank() || str.isEmpty()) {
			return false;
		} else {
			try {
				i =Integer.parseInt(str);
			} catch (Exception e) {
				return false;
			}
			if (i > max || i < min) {
				return false;
			} else {
			return true;
			}
		}
	}
	
	static public boolean isValidString(String str) {
		if(str.isBlank() || str.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	static public String[] lineReader(String line, int numCols) {
		String [] lines = new String[numCols];
		int start = 0, end = 0;
		for (int i = 0; i < numCols; i++) {
			if(end != 0) {
			start = end + 1;
			}
			if (line.charAt(end) == '"') {
				start++;
			}
			if (line.charAt(start) == '"') {
				start++;
				end = line.indexOf('"', start);
			} else {
			end = line.indexOf(',', start + 1);
			if(end == -1) {
				end = line.length();
			}
			}
			lines[i]=line.substring(start, end);
			}
		return lines;
	}
	
	
}
