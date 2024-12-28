package Credit;

// Printed on credit cards is a number that’s also stored in a database somewhere 
// American Express uses 15-digit numbers
// MasterCard uses 16-digit numbers
// Visa uses 13- and 16-digit numbers. 
// And those are decimal numbers (0 through 9), not binary, which means, for instance, that American Express could print as many as 10^15 = 1,000,000,000,000,000 unique cards! (That’s, um, a quadrillion.)

// All American Express numbers start with 34 or 37
// Most MasterCard numbers start with 51, 52, 53, 54, or 55 (they also have other potential starting numbers which we won’t concern ourselves with for this problem)
// All Visa numbers start with 4. 

// Credit card numbers also have a “checksum” built into them, a mathematical relationship between at least 1 number and others. 
// That checksum enables computers (or humans who like math) to detect typos (e.g., transpositions), if not fraudulent numbers, 
// without having to query a database, which can be slow. 


// LUHN'S ALGORITHM
// Most cards use an algorithm invented by Hans Peter Luhn of IBM.
// According to Luhn’s algorithm, you can determine if a credit card number is (syntactically) valid as follows:

// 1) Multiply every other digit by 2, starting with the number’s second-to-last digit, and then add those products’ digits together.
// 2) Add the sum to the sum of the digits that weren’t multiplied by 2.
// 3) If the total’s last digit is 0 (or, put more formally, if the total modulo 10 is congruent to 0), the number is valid!

//Example with a Visa: 4003600000000014.

// Multiply each of the underlined digits by 2:

// 1•2 + 0•2 + 0•2 + 0•2 + 0•2 + 6•2 + 0•2 + 4•2
// Gives us:
// 2 + 0 + 0 + 0 + 0 + 12 + 0 + 8

// Add those products digits (i.e., not the products themselves) together:
// 2 + 0 + 0 + 0 + 0 + 1 + 2 + 0 + 8 = 13

// Add that sum (13) to the sum of the digits that weren’t multiplied by 2 (starting from the end):
// 13 + 4 + 0 + 0 + 0 + 0 + 0 + 3 + 0 = 20

// Last digit in that sum (20) is a 0, so card is legit!

import java.util.Scanner;


public class Credit {

	public static void main(String[] args) {
		
		cardCheck();		
	}
	
	public static void cardCheck() {
		Scanner scanner = new Scanner(System.in);
		long number;
		System.out.println("Number: ");
		number = scanner.nextLong();
		
		// Check type of credit card, if the card is valid.
		if(luhn_algo(number)) {
			if(isVisa(number)) {
				System.out.println("VISA");
			} 
			else if(isAmex(number)) {
				System.out.println("AMEX");
			}
			else if(isMastercard(number)) {
				System.out.println("MASTERCARD");
			}
			else {
				System.out.println("INVALID");
			}
		}
		else {
			System.out.println("INVALID");
		}
		
	}
	
	// Function that performs Luhn's Algorithm
	public static boolean luhn_algo(long number) {
		long numberCopy = number;
		
		int firstSum = 0;
		int secondSum = 0;
		
		long digit;
		int firstValue = 0;
		int firstSumDigit = 0;
		boolean first = false;
		long sum = 0;
		
		
		while(numberCopy > 0) {
			digit = numberCopy % 10;
			if(first) {
				// The first value we get when we multiply every other digit by 2
				firstValue += 2* digit;
				while(firstValue > 0) {
					// Getting the digits of that first sum.
					firstSumDigit = firstValue % 10;
					firstSum += firstSumDigit;
					firstValue = firstValue / 10;
				}
				first = false;
			}
			else {
				secondSum += digit;
				first = true;
			}
			numberCopy = numberCopy / 10;
		}
		
		sum = firstSum + secondSum;
		
		// Check if the last digit is 0. If so, the credit card is valid.
		if(sum % 10 == 0) {
			return true;
		} 
		else {
			return false;
		}
		
	}
	
	
	// Check if card is Visa
	public static boolean isVisa(long number) {
		// Visa uses 13 or 16 digits
		// All Visa cards start with 4
		long digit = 0;
		int counter = 0;
		
		while(number > 0) {
			digit = number % 10;
			number = number / 10;
			counter+=1;
		}
		if(digit == 4 && (counter == 13 || counter == 16)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	// Check if card is AMEX
	public static boolean isAmex(long number) {
		// AMEX uses 15 digits
		// Most start with 34 or 37
		int firstCounter = 0;
		int secondCounter = 0;
		long digit;
		
		// Loop through until we reach the first 2 digits
		while(firstCounter < 13) {
			digit = number % 10;
			number = number / 10;
			firstCounter+=1;
		}
		
		// Check if the 1st 2 digits is 34 or 37.
	    // Check if the second digit is 7 or 4.
	    // Reset counter
		while(number > 0) {
			digit = number % 10;
			number = number / 10;
			secondCounter+=1;
			if(digit == 7 || digit == 4) {
				digit = number % 10;
				if(digit == 3) {
					secondCounter+=1;
					break;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		if(secondCounter > 2) {
			return false;
		}
		else {
			if(firstCounter + secondCounter == 15) {
				return true;
			} 
			else {
				return false;
			}
		}
		
	}
	
	
	// Check if card is MasterCard
	public static boolean isMastercard(long number) {
		// MasterCard uses 16 digits
		// Most start with 51, 52, 53, 54, 55
		int firstCounter = 0;
		int secondCounter = 0;
		long digit;
		// Loop through until we reach the first 2 digits
		while(firstCounter < 14) {
			digit = number % 10;
			number = number / 10;
			firstCounter+=1;
		}
		
		// Reset counter
		while(number > 0) {
			digit = number % 10;
			number = number / 10;
			secondCounter+=1;
			// Check if second to first digit is 1, 2, 3, 4, 5
			if(digit == 1|| digit == 2 || digit == 3 || digit == 4 || digit == 5) {
				digit = number % 10;
				if(digit == 5) {
					secondCounter+=1;
					break;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		if(secondCounter > 2) {
			return false;
		}
		else {
			if(firstCounter + secondCounter == 16) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
}
