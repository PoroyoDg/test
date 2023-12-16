package KiemTra;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static ArrayList<Tour> listTour = new ArrayList<Tour>();

	public static void main(String[] args) {
		System.out.print("so luong:");
		Scanner sc = new Scanner(System.in);
		int n = 0;
		try {
			n = sc.nextInt();
		} catch (Exception e) {
			System.out.println(e);
		}
		for (int i = 0; i < n; i++) {
			Tour x = new Tour("2", "2", "2", "2", 2, 2);
			x.addTour(x);
			x.toString();
		}
	}
}

