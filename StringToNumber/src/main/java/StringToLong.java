package main.java;

import java.util.Scanner;

/**
 * @author PPUNJAK(PIYUSH) On 05/07/2016
 * 
 * this class is used to parse a String to a Long 
 * without using Java defined methods for Long
 */
public class StringToLong {
	
	public static void main(final String[] args) {
		// MIN_VAL : -9223372036854775808L / MAX_VAL : 9223372036854775807L;
		final Scanner sc = new Scanner(System.in);
		System.out.println("Enter a String: ");
		
		/*
		 * user might attempt to pass a sentence as input, 
		 * hence using nextLine instead of next..we should be 
		 * considering the whole input and not just the first word.. 
		 */
		final String input = sc.nextLine(); 
		try {
			final Long output = convertStringToLong(input);
			System.out.println("The converted value is: " + output);
			// System.out.println(obj.convertStringToLong("23.45"));
		} catch (final NumberFormatException e) {
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	/**
	 * @return the Long representation of the input String
	 */
	public static Long convertStringToLong(final String inputStr) {
		/*
		 * if validation fails then we would return false indicating 
		 * that the string can not be converted as it contains some 
		 * characters which can not be parsed to a Long EG: [a-z]
		 */
		if (!validateInput(inputStr)) {
			throw new NumberFormatException("The provided number " + inputStr +
					" can not be converted to Long ");
		}
		
		/*
		 * if we are here then the passed the validation 
		 * and it is safe to assume the correctness of the input
		 */
		Long data = 0l;
		boolean isNegative = inputStr.charAt(0) == '-';
		int startIndex = isNegative ? 1 : 0; // taking negative numbers into consideration
		long max = Long.MAX_VALUE / 10;
		for (int i=startIndex; i < inputStr.length(); i++) {
			/*
			 * no need to calculate further as even before taking 
			 * any further characters into consideration the limit 
			 * has been reached for Long...
			 */
			if (data > max) {
				throw new NumberFormatException("Not a valid Long.");
			}
			
			// the actual calculation considering the offsets..
			data = data * 10 + inputStr.charAt(i) - '0';
			
			/*
			 * data can not be negative here as the sign part 
			 * has been removed before iteration, it being negative 
			 * clearly indicates that the Long is not able to accommodate 
			 * this value and hence returning a negative value for it... 
			 */
			if (data < 0 
					&& (!isNegative || data.compareTo(Long.MIN_VALUE) != 0)) 
			{
				throw new NumberFormatException("Not a valid Long.");
			}
		}
		
		// insuring the boundary
		if (data.compareTo(Long.MAX_VALUE) > 0 
				|| data.compareTo(Long.MIN_VALUE) < 0)
		{
			throw new NumberFormatException("Not a valid Long.");
		}
		
		return isNegative ? -data : data; // add negation for negative numbers
	}

	/**
	 * @return true if the passed String can be parsed to Long
	 * 
	 * Positive Test Cases:
	 * 1) 12345
	 * 2) -1234
	 * 
	 * Negative Test Cases:
	 * 1) 123.24 (decimal not supported)
	 * 2) +1234 (+ is not supported)
	 */
	private static boolean validateInput(String test) {
		boolean startedNum = false;
		if (test == null || test.length() == 0) {
			return false;
		}

		for (char c : test.toCharArray()) {
			switch (c) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				startedNum = true;
				break;
			case '-':
				if (startedNum) {
					return false;
				} else {
					startedNum = true;
					break;
				}
			default:
				return false;
			}
		}

		return true;
	}
}
