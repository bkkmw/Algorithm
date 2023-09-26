package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3307 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_3307.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] sequence = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) 
				sequence[i] = Integer.parseInt(st.nextToken());
			
			int ans = solve(sequence);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int[] seq) {
		int N = seq.length;
		int[][][] ret = new int[N][2][2];
		// pos, selected?, prev int, length
		ret[0][0][0] = 0;
		ret[0][0][1] = 0;
		ret[0][1][0] = seq[0];
		ret[0][1][1] = 1;
		
		for(int i=1; i<N; i++) {
			// Do Not Select current elements
			if(ret[i-1][0][1] > ret[i-1][1][1]) {
				ret[i][0][0] = ret[i-1][0][0];
				ret[i][0][1] = ret[i-1][0][1];
			}
			else {
				ret[i][0][0] = ret[i-1][1][0];
				ret[i][0][1] = ret[i-1][1][1];				
			}
			// Select current elements
			int max = 0;
			for(int j=0; j<i; j++) {
				for(int k=0; k<2; k++) {
					// if invalid : continue
					if(ret[j][k][0] > seq[i]) continue;
					// if vaild : find max
					max = (max > ret[j][k][1])? max : ret[j][k][1];
				}
			}
			ret[i][1][0] = seq[i];
			ret[i][1][1] = max + 1;
		}
		
		return (ret[N-1][0][1] > ret[N-1][1][1]) ?
				ret[N-1][0][1] : ret[N-1][1][1];
	}
}
