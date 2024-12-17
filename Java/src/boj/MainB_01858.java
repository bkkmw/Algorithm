package boj;

import java.io.*;
import java.util.*;

public class MainB_01858 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01858.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] coords = new int[N][3];
		
		for(int i=0; i<N; i++) { 
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.parseInt(st.nextToken());
			coords[i][1] = Integer.parseInt(st.nextToken());
			coords[i][2] = i;
		}
		
		int[] ans = solve(coords);
		
		StringBuilder sb = ans2sb(ans);
		System.out.print(sb.toString());
		br.close();
	}
	
	public static int[] solve(int[][] coords) {
		int[] ret = new int[2];
		// 1 / 60000 can be equal to zero
//		float max_gradient = 0;
		float max_gradient = -1;
		int[][] max_grad_coord = new int[2][2];
		int[] max_grad_diff = new int[2];
		int N = coords.length;
		
		Arrays.sort(coords, (o1, o2) -> o1[0] - o2[0]);
		
		for(int i=0; i<N-1; i++) {
			int x1=coords[i][0], y1=coords[i][1];
			int x2=coords[i+1][0], y2=coords[i+1][1];
			
			float gradient = Math.abs((y2-y1) / (x2-x1));
			
			if(gradient > max_gradient) {
				max_gradient = gradient;
				
				max_grad_diff[0] = (x2-x1); max_grad_diff[1] = (y2-y1);
				max_grad_coord[0][0] = x1; max_grad_coord[0][1] = y1;
				max_grad_coord[1][0] = x2; max_grad_coord[1][1] = y2;
				
				
				ret[0] = coords[i][2]; ret[1] = coords[i+1][2];
				sort_ab(ret);
				
			} else if(check_same_abs_grad(x2-x1, y2-y1, max_grad_diff[0], max_grad_diff[1])) {
				int na = coords[i][2] > coords[i+1][2] ? coords[i+1][2] : coords[i][2];
				int nb = coords[i][2] < coords[i+1][2] ? coords[i+1][2] : coords[i][2];
				
				if(check_in_line(max_grad_coord[0][0], max_grad_coord[0][1], x1, y1, (y2-y1)/(x2-x1))) {
					int[] indexes = new int[] {	ret[0], ret[1], na, nb };
					
					Arrays.sort(indexes);
					
					ret[0] = indexes[0];
					ret[1] = ret[0] == indexes[1] ? indexes[2] : indexes[1];
					continue;
				}
				
				if(ret[0] > na) {
					ret[0] = na; ret[1] = nb;
				} else if(ret[0] == na) {
					ret[1] = Math.min(ret[1], nb);
				}
			}
			
		}
		
		ret[0]++;
		ret[1]++;
		
		return ret;
	}
	
	public static boolean check_same_abs_grad(int dx1, int dy1, int dx2, int dy2) {
//		boolean ret = false;
		try {
			float grad1 = Math.abs(dy1 / dx1);
			float grad2 = Math.abs(dy2 / dx2);
			
			return grad1 == grad2;			
		} catch(Exception e) {
			return false;
		}
	}
	
	public static boolean check_in_line(int x1, int y1, int x2, int y2, float grad) {
		if(x1 == x2 && y2 == y1) return true;
		
		return (y2-y1) / (x2-x1) == grad;
	}
	
	public static void sort_ab(int[] ab) {
		int temp = ab[0];
		
		ab[0] = ab[0] < ab[1] ? ab[0] : ab[1];
		ab[1] = ab[1] == ab[0] ? temp : ab[1];
	}
	
	public static StringBuilder ans2sb(int[] ans) {
		StringBuilder ret = new StringBuilder();
		
		ret.append(ans[0]).append(" ").append(ans[1]);
		
		return ret;
	}
}
