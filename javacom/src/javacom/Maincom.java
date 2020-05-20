package javacom;

import java.util.Scanner;

public class Maincom {

	public static void main(String[] args) {
		Scanner in =new Scanner(System.in);
		int a =in.nextInt();
		int b =in.nextInt();
		int c =in.nextInt();
		if(1<=a && a<=10 && 1<=b && b<=10 && 1<=c&& c<=10) {
			int[] max =new int[4];
			max[0] = a+b*c;
			max[1] =a*(b+c);
			max[2] =a*b*c;
			max[3] =(a+b)*c;
			
			int min=max[0];
			for(int i=1;i<max.length;i++) {
				if(min<=max[i]) {
					min=max[i];
				}	
			}
			
			System.out.println(min);
			
		}

	}

}
