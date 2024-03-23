package boj;

import java.io.*;
import java.util.*;

public class MainB_20207 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_20207.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] schedules = new int[N][2];
		
		int min=366, max=0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				int temp = Integer.parseInt(st.nextToken())-1;			
				schedules[i][j] = temp;
				
				min = temp > min ? min : temp;
				max = temp < max ? max : temp;
			}
		}
		
		int ans = solve(schedules, min, max);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] arr, int min, int max) {
		int ret = 0, N = arr.length, len = max-min;
		int[] heights = new int[len+2];
		boolean[][] checked = new boolean[1000][len+1];
		
		Arrays.sort(arr, (o1,o2) -> {
			return o1[0] != o2[0] ? o1[0]-o2[0] : o2[1]-o1[1];
		});
		
		
		for(int i=0; i<N; i++) {
			int src = arr[i][0]-min, dst = arr[i][1]-min;
			
			int h = get_h(checked, src);
			for(int j=src; j<=dst; j++) {
				checked[h][j] = true;
				heights[j] = h+1 > heights[j] ? h+1 : heights[j];
			}
		}
		
		int from = -1;
		int max_h = 0;
		heights[len+1] = 0;
		for(int i=0; i<=len+1; i++) {
			if(heights[i] == 0) {
				ret += (i-from) * max_h;
				from = -1;
				max_h = 0;
			} else {
				if(from == -1) {
					from = i;
				}
				max_h = max_h < heights[i] ? heights[i] : max_h;
			}
		}
		return ret;
	}
	
	public static int get_h(boolean[][] checked, int col) {
		int ret = 0;
		while(checked[ret][col])
			ret++;
		return ret;
	}
}
