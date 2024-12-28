package Mario50;

import java.util.Scanner;

public class MarioMore {

	public static void main(String[] args) {
		
		mario_more();

	}

	public static void mario_more() {
		Scanner scanner = new Scanner(System.in);
		int height;
		
		do {
			System.out.println("Height: ");
			height = scanner.nextInt();
		}
		while (height < 1 || height > 8);
		
		int spaces;
		for(int i = 0; i <= height; i++) {
			spaces = height-i;
			for(int j = 0; j < i; j++) {
				while(spaces > 0) {
					System.out.print(" ");
					spaces--;
				}
				System.out.print("#");
			}
			
			// Printing the 2 spaces between the pyramids
			System.out.print("  ");
			
			// For loop that prints out the right pyramid.
			for(int k = 0; k < i; k++) {
				System.out.print("#");
			}
			// Prints new line
			System.out.println("");
		}
		
	}
	
}
