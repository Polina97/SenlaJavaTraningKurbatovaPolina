package main;

import java.util.Random;

public class RandomManager {
	private int number;
	private int sum;
	private Random rnd = new Random();
	private StringBuilder builder = new StringBuilder();

	public void printNumberAndSum() {
		number = getThreeDigitNumber();
		sum = getSum(number);
		System.out.println(builder.append(number).append(" Sum: ").append(sum));

	}

	private int getThreeDigitNumber() {
		int n;
		do {
			n = rnd.nextInt(999);
		} while (n < 100);
		return n;
	}

	private int getSum(int n) {
		int sum = 0;
		while (n > 0) {
			sum = sum + (n % 10);
			n /= 10;
		}
		return sum;

	}
}
