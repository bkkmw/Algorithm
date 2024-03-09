package boj;

import java.io.*;
import java.util.*;

public class MainB_01747 {
	
	public static int MAX_ANS = 1003001;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01747.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int ans = solve(N);
		System.out.println(ans);
	}
	
	public static int solve(int N) {
		int ret = 0;
		
		List<Integer> primes = get_primes(MAX_ANS);
		
		Iterator<Integer> it = primes.iterator();
		
		while(it.hasNext()) {
			int prime = it.next();
			if(prime < N) continue;
			if(is_palindrome(prime)) {
				return prime;
			}
		}
		
		return ret;
	}
	
	public static boolean is_palindrome(int num) {
		String str = Integer.toString(num);
		int len = str.length();
		
		for(int i=0; i<len/2; i++) {
			if(str.charAt(i) != str.charAt(len-1-i)) return false;
		}
		
		return true;
	}
	
	public static List<Integer> get_primes(int max) {
		List<Integer> ret = new ArrayList<Integer>();
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
}
