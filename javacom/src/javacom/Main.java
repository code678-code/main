package javacom;


import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k =in.nextInt();
		int[] it = new int[n];
		for(int i=0;i<n;i++) {
			it[i] = in.nextInt();
		}
		int min=it[0];		
		for(int j=0;j<k;j++) {
			for(int i=0;i<it.length;i++) {//找出最小值
				if(min>=it[i] && it[i]!=0) {
					min=it[i];
				}	
			}
			System.out.println(min);
			for(int i=0;i<k;i++) {
				if(it[i]-min>0) {
					it[i] = it[i] - min;				
				}else {
					it[i] =0;
				}
			}
			for(int i=0;i<it.length;i++) {
				if(min<=it[i]) {
					min = it[i];
				}
			}
			
		}
		in.close();
		
	}

	
	

}
