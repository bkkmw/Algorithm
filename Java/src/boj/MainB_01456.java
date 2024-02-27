package boj;

import java.io.*;
import java.util.*;

public class MainB_01456 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01456.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		long ans = solve(A, B);
		System.out.println(ans);
	}
	
	public static long solve(long A, long B) {
		long ret = 0;
		int max = (int)Math.sqrt(B);
		
		List<Integer> primes = get_primes(max);
		
		Iterator<Integer> it = primes.iterator();
		
		while(it.hasNext()) {
			ret += count_almost_primes(it.next(), A, B);
		}
		
		return ret;
	}
	
	public static int count_almost_primes(int prime, long min, long max) {
		int ret = 0;
		long val = (long)prime * prime;
		
		while(val <= max) {
			ret += (val < min)? 0 : 1;
			if(val > Long.MAX_VALUE/prime) break;
			val *= prime;
		}
		
		return ret;
	}
	
	public static List<Integer> get_primes(int max) {
		List<Integer> ret = new LinkedList<Integer>();
		boolean[] checked = new boolean[max+1];
		
		for(int i=2; i<=max; i++) {
			if(!checked[i]) ret.add(i);
			
			checked[i] = true;
			int k = 2;
			while(i*k <= max) {
				checked[i*k] = true;
				k++;
			}
		}
		
		return ret;
	}
}
