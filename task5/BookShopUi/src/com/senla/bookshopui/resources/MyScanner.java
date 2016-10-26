package com.senla.bookshopui.resources;

import java.util.Scanner;


public class MyScanner {
	private static String MESSAGE = "Try again!";
	private static Scanner scn = new Scanner(System.in);
	private static Boolean isNext = scn.hasNext();

	public static int scanInt() {
		if (scn.hasNextInt()) {
			int a = scn.nextInt();
			return a;
		} else {
			Printer.print(MESSAGE);
			return scanInt();
		}
	}

	public static int positive() {
		int a = scanInt();
		if (a > 0)
			return a;
		else {
			Printer.print(MESSAGE);
			return positive();
		}
	}
	public static boolean isNext(){
		return isNext;
	}

	public static void setIsNext(Boolean isNext) {
		MyScanner.isNext = isNext;
	}
	

}
