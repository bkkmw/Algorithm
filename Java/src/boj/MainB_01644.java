package boj;

import java.io.*;
import java.util.*;

public class MainB_01644 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int ans = solve(N);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int N) {
		int ret = 0;
		
		int[] primes = get_primes(N);
		
		int size = primes.length;
		int lidx = 0;
		int ridx = 0;
		
		while(ridx < size) {
			int sum = 0;
			for(int i=0; i<ridx-lidx+1; i++) {
				sum += primes[lidx+i];
			}
			
			if(sum == N) {
//				System.out.printf("%d [%d] %d [%d] %d\n", lidx, primes[lidx], ridx, primes[ridx], N);
				ret ++;
			}
			if(sum < N) ridx++;
			else lidx++;
		}
		
		return ret;
	}
	
	public static int[] get_primes(int N) {
		boolean[] is_not_prime = new boolean[N+1];
		is_not_prime[1] = true;
		
		for(int i=2; i<N+1; i++) {
			if(is_not_prime[i]) continue;
			
			for(int j=2; i*j<N+1; j++) {
				is_not_prime[i*j] = true;
			}
		}
		
		int[] prime_list = new int[N+1];
		int idx = 0;
		
		for(int i=2; i<N+1; i++) {
			if(is_not_prime[i]) continue;
			prime_list[idx++] = i;
		}
		
		return Arrays.copyOfRange(prime_list, 0, idx);
	}
}
