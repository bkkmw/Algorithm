package boj;

import java.io.*;
import java.util.*;

public class MainB_30826 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_30826.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(br.readLine());
		
		int ans = solve(N);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(long N) {
		long count = 9, num_of_digit = 1;
		List<Integer> arr = new ArrayList<Integer>();
		
		while(N > count * num_of_digit) {
			N -= (count * num_of_digit);
			num_of_digit++;
			if(num_of_digit % 2 == 1)
				count *= 10;
		}
		
		count /= 9;
		
		int digit = (int)((N+count*num_of_digit-1)/(count*num_of_digit));
		arr.add(digit);

		N -= (digit-1)*count*num_of_digit;
		count /= 10;
						
		while((N-1)/num_of_digit > 0) {
			digit = (int)((N-1)/(count*num_of_digit));
			N -= (digit)*count*num_of_digit;
			count /= 10;
			
			arr.add(digit);
			
			if(N <= num_of_digit) break;
		}
		
		for(int i=arr.size(); i<(num_of_digit+1)/2; i++) {
			arr.add(0);
		}
		
		int idx = N > num_of_digit/2 ? (int)(num_of_digit-N) : (int)N-1;

		return arr.get(idx);
	}
}
