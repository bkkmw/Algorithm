package boj;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainB_11659 {


	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_11659.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N+1];
		sc.nextLine();
		String line = sc.nextLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		arr[0] = 0;
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}

		for(int i=0; i<M; i++) {
			int front = sc.nextInt();
			int end = sc.nextInt();
			sb.append(arr[end] - arr[front-1]);
			sb.append("\n");
		}
		System.out.printf(sb.toString());
		sc.close();
	}
	
	
}