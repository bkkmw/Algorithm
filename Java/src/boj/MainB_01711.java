package boj;

import java.util.*;
import java.io.*;

public class MainB_01711 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01711.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] pos = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(pos);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] pos) {
		int ret = 0, N = pos.length;
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					ret += check_orthogonal(pos[i], pos[j], pos[k]);
				}
			}
		}
		return ret;
	}
	
	public static int check_orthogonal(int[] a, int[] b, int[] c) {
		long ans = 0;
		
		long abc = dot_product(a, b, c);
		long bca = dot_product(b, c, a);
		long cab = dot_product(c, a, b);
		
		return (abc==0 || bca == 0 || cab ==0) ? 1 : 0;
	}
	
	public static long dot_product(int[] a, int[] b, int[] c) {
		int a1 = b[0]-a[0], a2 = b[1]-a[1];
		int b1 = c[0]-b[0], b2 = c[1]-b[1];
		return 1L*a1*b1 + 1L*a2*b2;
	}
}
