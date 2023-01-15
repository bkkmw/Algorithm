package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainB_01446 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01446.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[][] shortcuts = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			shortcuts[i][0] = Integer.parseInt(st.nextToken());
			shortcuts[i][1] = Integer.parseInt(st.nextToken());
			shortcuts[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(shortcuts, N, D);
		System.out.println(ans);
		br.close();
	}
	
	static int solve(int[][] shortcuts, int N, int D) {
		int ret = 0;
		
		Arrays.sort(shortcuts, (o1, o2) -> o1[0] - o2[0]);
		
		ret = drive(shortcuts, N, D, 0, 0, 0, 10001);
		return ret;
	}

	static int drive(int[][] shortcuts, int N, int D, int idx, int pos, int cnt, int min) {
		
		int i = idx;
		
		for(i = idx; i<N; i++)
			if((shortcuts[i][0] >= pos) && (shortcuts[i][1] <= D)) break; 
		
		if(i == N) {
			cnt += (D - pos);
			return (cnt < min)? cnt : min;
		}
		
		int moved = shortcuts[i][0] - pos;
		
		min = drive(shortcuts, N, D, i+1, shortcuts[i][1], cnt + moved + shortcuts[i][2], min);
		min = drive(shortcuts, N, D, i+1, pos, cnt, min);
		
		return min;
	}
}
