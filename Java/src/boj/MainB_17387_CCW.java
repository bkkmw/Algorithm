package boj;

import java.io.*;
import java.util.*;

public class MainB_17387_CCW {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17387.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		long[][] pos = new long[4][2];
		for(int i=0; i<4; i++) {
			if(i%2 == 0) 
				st = new StringTokenizer(br.readLine());
			
			pos[i][0] = Long.parseLong(st.nextToken());
			pos[i][1] = Long.parseLong(st.nextToken());
		}
		
		boolean ans = solve(pos);
		System.out.println(ans? 1 : 0);
		br.close();
	}
	
	public static boolean solve(long[][] pos) {
		boolean ret = false;
		
		int abc = ccw(pos[0], pos[1], pos[2]);
		int abd = ccw(pos[0], pos[1], pos[3]);
		
		int cda = ccw(pos[2], pos[3], pos[0]);
		int cdb = ccw(pos[2], pos[3], pos[1]);
		
		if(abc*abd == 0 && cda*cdb ==0) {
			ret = check_dist(pos);
			
		}
		else if(abc*abd <= 0 && cda*cdb <= 0) {
			ret = true;
		}
		
		return ret;
	}
	
	public static boolean check_dist(long[][] pos) {
		boolean ret = false;
		
		long dx = pos[1][0] - pos[0][0];
		long dy = pos[1][1] - pos[0][1];
		
		for(int i=2; i<4; i++) {
			long dx_ = pos[i][0] - pos[0][0];
			long dy_ = pos[i][1] - pos[0][1];
			
			if(dx*dx_ < 0 || dy*dy_ < 0) continue;
			if(Math.abs(dx) >= Math.abs(dx_) && Math.abs(dy) >= Math.abs(dy_)) ret = true;
		}
		
		dx = pos[3][0] - pos[2][0];
		dy = pos[3][1] - pos[2][1];
		for(int i=0; i<2; i++) {
			long dx_ = pos[i][0] - pos[2][0];
			long dy_ = pos[i][1] - pos[2][1];
			
			if(dx*dx_ < 0 || dy*dy_ < 0) continue;
			if(Math.abs(dx) >= Math.abs(dx_) && Math.abs(dy) >= Math.abs(dy_)) ret = true;
		}
		
		return ret;
	}
	
	
	public static int ccw(long[] a, long[] b, long[] c) {
		long ret = a[0]*b[1] + b[0]*c[1] + c[0]*a[1];
		ret -= (a[1]*b[0] + b[1]*c[0] + c[1]*a[0]);
		
		if(ret > 0) return 1;
		else if(ret == 0) return 0;
		else return -1;
//		return ret == 0 ? 0 : ret<0 ? -1 : 1;
	}
}
