package com.senla.bookshopui.resources;

import java.util.Scanner;

public class MyScanner {
	private static String MESSAGE = "Try again!";
	private static Scanner scn = new Scanner(System.in);

	public static int scanInt() {
		if (scn.hasNextInt()) {
			int a = scn.nextInt();
			return a;
		} else {
			System.out.println(MESSAGE);
			return scanInt();
		}
	}

	public static int positive() {
		int a = scanInt();
		if (a > 0)
			return a;
		else {
			System.out.println(MESSAGE);
			return positive();
		}
	}

}
