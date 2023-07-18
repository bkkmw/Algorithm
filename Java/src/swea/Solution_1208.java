package swea;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;

class Solution_1208
{
	static int cnt;
	static int[] arr = new int[100];
	static int min, min_idx;
	static int max, max_idx;
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_1208.txt"));

		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			clear();
			cnt = sc.nextInt();
			sc.nextLine();
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i<100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] <= min) {
					min = arr[i]; 
					min_idx = i;
				}
				if(arr[i] >= max) {
					max = arr[i]; 
					max_idx = i;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, dump());
			

		}
		sc.close();
	}
	
	static void clear() {
		for(int i=0; i<100; i++) {
			arr[i] = 0;
		}
		min = 101;
		max = 0;
		min_idx = -1;
		max_idx = -1;
	}
	
	static int dump() {
		while(cnt > 0) {
			arr[max_idx]--;
			arr[min_idx]++;
			update_();
			cnt--;
			if(max-min <= 1) return max-min;
		}
		return max-min;
	}
	
	static void update() {
		min = 101;
		max = 0;
		min_idx = -1;
		max_idx = -1;
		for(int i=0; i<100; i++) {
			if(arr[i] >= max) {
				max = arr[i]; 
				max_idx = i;
			}
			if(arr[i] <= min) {
				min = arr[i]; 
				min_idx = i;
			}
		}
	}

	static void update_() {
		int temp_m = 101;
		int temp_M = 0;
		int midx = -1;
		int Midx = -1;
		boolean Maximum = false;
		boolean minimum = false; 
		for(int i=0; i<100; i++) {
			if(Maximum && minimum) return;
			if(arr[i] == max) {
				Maximum = true;
				max_idx = i;
			}
			if(arr[i] == min) {
				minimum = true;
				min_idx = i;
			}
			if(arr[i] >= temp_M) {
				temp_M = arr[i];
				Midx = i;
			}
			if(arr[i] <= temp_m) {
				temp_m = arr[i];
				midx = i;
			}
		}
		max = temp_M;
		max_idx = Midx;
		min = temp_m;
		min_idx = midx;
	}
}

/*
1. Find min, max
2. while(cnt)
	2.1. Max--, min++, cnt--
	2.2. update min, max
	2.3. if(diff <= 1) terminate & return diff
	2.4. if(cnt == 0) terminate &* return diff
*/