package swea;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_1289
{
	static int[] mem = new int[50];
	static int[] target = new int[50];
	
	static void clear() {
		for(int i=0; i<50; i++) {
			mem[i] = 0;
			target[i] = 0;
		}
	}
	
	static void flip(int n, int idx) {
		int tar = (mem[idx] == 1) ? 0: 1;
		for(int i=idx; i<n; i++) {
			mem[i] = tar;
		}
	}
	
	static int solve(int n) {
		int ret = 0;
		for(int i=0; i<n; i++) {
			if(target[i] != mem[i]) {
				flip(n, i);
				ret ++;
			}
		}
		
		return ret;
	}
	
	public static void main(String args[]) throws Exception
	{

		
		System.setIn(new FileInputStream("input/swea/input_1289.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		int len = 0;
		T=sc.nextInt();
		sc.nextLine();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			clear();
			String tar = sc.nextLine();
			len = tar.length();
			for(int i=0; i<len; i++) {
				target[i] = (int)tar.charAt(i) - 48;
			}
			
			System.out.printf("#%d %d\n", test_case, solve(len));

		}
	}
}