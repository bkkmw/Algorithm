package boj;

import java.io.*;
import java.util.*;

public class MainB_22943 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_22943.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int ans = solve(K, M);
		System.out.println(ans);
	}
	
	public static int solve(int K, int M) {
		int max = (int)Math.pow(10, K)-1;
		int ret = 0;
		
		boolean[][] check = new boolean[max+1][2];
		
		List<Integer> prime_list = get_primes(max);
		int size = prime_list.size();
		
		Integer[] primes = prime_list.toArray(new Integer[size]);
		
		for(int i=0; i<size; i++) {
			int a = primes[i];
			for(int j=i; j<size; j++) {
				int b = primes[j];
				
				if(i!=j && (a+b <= max)) {
					check[a+b][0] = true;
				}
				
				if(a*b % M == 0) continue;
				
				// casting
				long prod = (long)a*b;
				while(prod <= max) {
					check[(int)prod][1] = true;
					prod *= M;
				}
			}
		}
		
		for(int i=(int)Math.pow(10, K-1); i<=max; i++) {
			if(check[i][0] && check[i][1] && verify_digits(i))
				ret ++;
		}
		
		return ret;
	}
	
	public static List<Integer> get_primes(int max) {
		List<Integer> ret = new LinkedList<Integer>();
		boolean[] check = new boolean[max+1];
		
		for(int i=2; i<=max; i++) {
			// prime number
			if(!check[i]) {
				ret.add(i);
			}
			
			// filter
			int k =1;
			while(k*i <= max) {
				check[k*i] = true;
				k++;
			}
		}
		return ret;
	}
	
	public static boolean verify_digits(int num) {
		boolean[] check = new boolean[10];
		while(true) {
			int digit = num%10;
			if(check[digit]) return false;
			
			check[digit] = true;
			
			if(num < 10) break;
			num /= 10;
		}
		
		return true;
	}
	
	public static int[] init_arr(int K) {
		int size = (int)Math.pow(10, K);
		
		return new int[size];
	}
}
