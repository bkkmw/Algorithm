package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainB_16396 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_16396.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] pos = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(pos, (o1, o2)  -> {return o1[0]- o2[0];});
		
		int ans = solve(pos, N);
		System.out.println(ans);
		br.close();
	}
	
	static int solve(int[][] pos, int N) {
		int ret = 0;
		int[] cur = pos[0];
		
		for(int i=1; i<N; i++) {
			if(pos[i][0] <= cur[1]) {
				if(pos[i][1] > cur[1])
					cur[1] = pos[i][1];
			}
			else {
				ret += cur[1] - cur[0];
				cur = pos[i];
			}
		}
		
		ret += cur[1] - cur[0];
		
		return ret;
	}
}
