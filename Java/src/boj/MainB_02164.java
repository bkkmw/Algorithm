package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainB_02164 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		System.out.printf("%d\n", solve(N));

	}

	static int solve(int N) {
		if (N == 1)	return 1;
		else if (N==2) return 2;
		int even = N / 2;
		int[] check = new int[even];

		if (N % 2 == 0) {
			check[0] = 1;
		}

		int idx = 0;

		for (int i = check[0]; i < even - 1; i++) {
			while (check[(idx) % even] != 0) {
				idx++;
			}
			idx = idx % even + 1;
			while (check[(idx) % even] != 0) {
				idx++;
			}
			idx = idx % even;
			check[idx++] = 1;

		}
		while (check[(idx) % even] != 0) {
			idx++;
		}
		return 2*(idx%even + 1);

	}
}
