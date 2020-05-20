package Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] cin = in.nextLine().split(" ");
		int[] arr = new int[cin.length];
		for(int i=0;i<cin.length;i++) {
			arr[i] = Integer.valueOf(cin[i]);
		}
		System.out.println(Water(arr));
		in.close();
	}

	private static int Water(int[] arr) {
		List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		int start = 0;
		int totalWater = 0;
		while (start < arr.length - 2) {
			while (start < arr.length - 2) {
				if (arr[start] <= arr[start + 1]) {
					start++;
				} else {
					break;
				}
			}
			int end = start + 1;
			for (int temp = end; temp < arr.length; temp++) {
				if (arr[temp] >= arr[start]) {
					end = temp;
					break;
				}
				int max = arr[end];
				if (max < arr[temp]) {
					max = arr[temp];
					end = temp;
				}
			}
			List<Integer> tempList = new ArrayList<Integer>();
			for (int j = start; j <= end; j++) {
				tempList.add(arr[j]);
			}
			start = end;
			list.add((ArrayList<Integer>) tempList);
		}

		for (int i = 0; i < list.size(); i++) {
			List<Integer> l = list.get(i);
			int endWater = (Integer) l.get(l.size() - 1);
			int startWater = (Integer) l.get(0);
			if (startWater <= endWater) {
				for (int j = 0; j < l.size(); j++) {
					if (startWater <= endWater) {
						if ((Integer) l.get(j) < startWater) {
							totalWater = totalWater
									+ (startWater - (Integer) l.get(j));
						}
					}
				}
			} else {
				for (int k = l.size() - 1; k >= 0; k--) {
					if ((Integer) l.get(k) < endWater) {
						totalWater = totalWater
								+ (endWater - (Integer) l.get(k));
					}
				}
			}
		}
		return totalWater;
	}
}
