package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_1247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int[][] coor;
			int N;
			int ans;
			
			N = Integer.parseInt(br.readLine());
			coor = new int[N+2][2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N+2; i++) {
				coor[i][0] = Integer.parseInt(st.nextToken());
				coor[i][1] = Integer.parseInt(st.nextToken());
			}
			ans = solve(coor, N);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static int solve(int[][] coor, int N) {
		int ret = Integer.MAX_VALUE;
		
		int[] num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = i+2;
		}
		do {
			int temp = calc(coor, num);
			if(temp < ret) ret = temp;
		}while(next(num));
		
		return ret;
	}
	
	static int calc(int[][] coor, int[] num) {
		int ret =0;
		int x = coor[0][0];
		int y = coor[0][1];
		for(int i=0; i<coor.length-2; i++) {
			int idx = num[i];
			ret += (Math.abs(x - coor[idx][0]) + Math.abs(y - coor[idx][1]));
			x = coor[idx][0];		y = coor[idx][1];
		}
		ret += (Math.abs(x - coor[1][0]) + Math.abs(y - coor[1][1]));
		return ret;
	}
	
	static boolean next(int[] num) {
		int N = num.length;
		int i = N-1;
		while(i>0 && num[i-1]>=num[i]) --i;
		
		if(i==0) return false; 		
		int j= N-1;
		while(num[i-1]>=num[j]) --j;
		
		swap(num, i-1, j);

		int k = N-1;
		while(i<k) swap(num, i++, k--);
		return true;
	}
	
	public static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

}
