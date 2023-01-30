package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainB_02954 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_02954.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int L = input.length();
		
		char[] output = new char[L];
		int len = 0;
		
		char prev = input.charAt(0);
		char curr;
		
		output[len++] = prev;
		
		for(int i=1; i<L; i++) {
			prev = input.charAt(i-1);
			curr = input.charAt(i);
			output[len++] = curr;
			
			if(input.charAt(i) != 'p') continue;
			
			if(prev == 'a' || prev == 'e' || prev == 'i' || prev =='o' || prev == 'u') {
				if(i == L-1) continue;
				if(input.charAt(i+1) == prev) {
					len -= 1;
					i += 2;
					if(i < L) {
						output[len++] = input.charAt(i);
					}
				}
			}
		}
		
		Arrays.toString(output);
		System.out.println(Arrays.copyOfRange(output, 0, len));
	}
}
