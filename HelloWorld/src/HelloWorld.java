import java.util.*;
public class HelloWorld {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		System.out.print("Input a decimal number: ");
		int dec = s.nextInt();
		System.out.println(dec); //Prints decimal value
		//Find smallest power of 2 larger than dec
		int dec1 = dec;
		System.out.print("0b");
		int p = 1;
		while (p <= dec1) {
			p = p * 2;
		}
		int a = p / 2;
		Boolean flag = true;
		while (flag) {
			if (dec1 >= a) {
				dec1 -= a;
				System.out.print("1");
			} else {
				System.out.print("0");
			}
			a /= 2;
			if (a < 1) {
				flag = false;
			}
		}
		
		//Octal
		System.out.println("");
		int dec2 = dec;
		int remainder;
		int quotient = dec2;
		String octal = "";
		while (quotient != 0) {
			quotient = dec2 / 8;
			remainder = dec2 % 8;
			String str1 = Integer.toString(remainder);
			octal += str1;
			dec2 = quotient;
		}
		int length = octal.length();
		String reverse = "";
		for (int i = length - 1; i >= 0; i--) {
			reverse = reverse + octal.charAt(i);
		}
		System.out.print("0o");
		System.out.print(reverse);
		
		//Hexadecimal
		System.out.println("");
		int dec3 = dec;
		quotient = dec3;
		String hexadecimal = "";
		while (quotient != 0) {
			quotient = dec3 / 16;
			remainder = dec3 % 16;
			if (remainder >= 10) {
				if (remainder == 10) {
					hexadecimal += "A";		
				} else if (remainder == 11) {
					hexadecimal += "B";
				} else if (remainder == 12) {
					hexadecimal += "C";
				} else if (remainder == 13) {
					hexadecimal += "D";
				} else if (remainder == 14) {
					hexadecimal += "E";
				} else if (remainder == 15) {
					hexadecimal += "F";
				}
			} else {
				String str1 = Integer.toString(remainder);
				hexadecimal += str1;
			}
			dec3 = quotient;
		}
		length = hexadecimal.length();
		String rev = "";
		for (int i = length - 1; i >= 0; i--) {
			rev = rev + hexadecimal.charAt(i);
		}
		System.out.print("0x");
		System.out.print(rev);
	}
}
