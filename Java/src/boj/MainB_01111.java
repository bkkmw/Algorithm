package boj;

import java.util.*;
import java.io.*;

public class MainB_01111 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01111.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		int[] seq = new int[N];
		for(int i=0; i<N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		int ans;
		try {
			ans = solve(N, seq);
			System.out.println(ans);
		} catch(ArithmeticException e) {
			boolean wrong = false;
			for(int i=0; i<N-1; i++) {
				if(seq[i] != seq[i+1])
					wrong = true;
			}
			System.out.println(wrong? "B" : seq[0]);
		} catch(DuplicatedAnswerException e) {
			System.out.println("A");
		} catch(WrongSequenceException e) {
			System.out.println("B");
		}
		
		br.close();
	}
	
	public static int solve(int N, int[] seq) throws ArithmeticException, DuplicatedAnswerException, WrongSequenceException {
		if(N==1) throw new DuplicatedAnswerException();
		if(N==2) {
			if(seq[0] == seq[1]) return seq[1];
			else throw new DuplicatedAnswerException();
		}
		
		if((seq[2]-seq[1]) % (seq[1]-seq[0]) != 0)
			throw new WrongSequenceException();
		
		int a = (seq[2]-seq[1]) / (seq[1]-seq[0]);
		int b = (seq[2]-a*seq[1]);
		
		for(int i=2; i<N-1; i++) 
			if(seq[i+1] != a*seq[i] + b) throw new WrongSequenceException();
		
		return a*seq[N-1] + b;
	}
	
	static class DuplicatedAnswerException extends Exception {
	}
	static class WrongSequenceException extends Exception {
	}
}

