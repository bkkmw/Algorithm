package boj;

import java.io.*;
import java.util.*;

public class MainB_01025 {
	
	public static int[][] dir_yx = new int[][] {
		{+1,+1}, {+1,-1}, {-1,+1}, {-1,-1}
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01025.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] mat = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				mat[i][j] = line.charAt(j) - 48;
			}
		}

		int ans = solve(mat);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][] mat) {
		int ret = -1;
		int N = mat.length, M = mat[0].length;
		
		for(int istride=0; istride<=N; istride++) {
			for(int jstride=0; jstride<=M; jstride++) {
				if(istride==0 && jstride==0) continue;
				int temp = find_max_square(mat, istride, jstride);
				ret = Math.max(ret, temp);
			}
		}
		
		return ret;
	}
	
	public static int find_max_square(int[][] mat, int istride, int jstride) {
		int ret = -1, N = mat.length, M = mat[0].length;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int d=0; d<4; d++) {
					int num = get_number(mat, i, j, istride, jstride, d);
					ret = Math.max(get_square(num), ret);
				}
			}
		}
		
		return ret;
	}
	
	public static int get_number(int[][] mat, int i, int j, int istride, int jstride, int dir) {
		int ret = 0, N = mat.length, M = mat[0].length;
				
		while(i>-1 && i<N && j>-1 && j<M) {
			ret *= 10;
			ret += mat[i][j];
			
			i+=istride*dir_yx[dir][0];
			j+=jstride*dir_yx[dir][1];
		}
		
		return ret;
	}
	
	public static int get_square(int num) {
		if(num ==0) 
			return num;
		while(num > 0) {
			double sqrt = Math.sqrt(num);
			if(sqrt == Math.ceil(sqrt)) {
				return num;
			}
			num /= 10;
		}
		return -1;
	}
}
