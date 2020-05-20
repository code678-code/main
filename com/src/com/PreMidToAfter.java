package com;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PreMidToAfter {
	
	private static int endsum;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cin = in.nextInt();
		int[][] it = new int[cin][2];
		for(int i=0;i<cin;i++) {
			for(int j=0;j<2;j++) {
				it[i][j] = in.nextInt();
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<cin;i++) {
			for(int j=0;j<it[i][0];j++) {
				list.add(it[i][1]);
			}
		}
		int st = list.size();
		System.out.println(st);
		int[] si = new int[st*st];
		int x=0;
		int[] su = new int[st];
		for(int i=0;i<list.size();i++) {
			su[i] = list.get(i);
		}
		System.out.println(su.length+"------");
		for(int i=0;i<su.length;i++) {
			for(int j=0;j<su.length;i++) {
				int sum = su[i] + su[j];
				si[x] =sum;
				System.out.println("=="+sum);
				x++;
				System.out.println(x);
			}
		}

		for(int i=0;i<si.length;i++) {
			endsum +=si[i];
		}
		int end=endsum/si.length;
		System.out.println(end);
		
	}

}

