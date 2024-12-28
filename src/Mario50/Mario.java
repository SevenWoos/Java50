package Mario50;

import java.util.Scanner;

//In a file called mario.java in a folder called mario-less, 
//Implement a program in Java that recreates a pyramid, using hashes (#) for bricks


public class Mario {

	public static void main(String[] args) {

		mario();
		
	}
	
	public static void mario() {
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
			
			System.out.println("");
		}
		
	}

}