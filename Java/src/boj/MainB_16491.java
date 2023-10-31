package boj;

import java.util.*;
import java.io.*;

public class MainB_16491 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16491.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] robot = new int[N][2];
		int[][] shelter = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			robot[i][0] = Integer.parseInt(st.nextToken());
			robot[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			shelter[i][0] = Integer.parseInt(st.nextToken());
			shelter[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] ans = solve(robot, shelter);
		print_ans(ans);
		
		br.close();
	}
	
	public static int[] solve(int[][] robot, int[][] shelter) {
		int N = robot.length;
		int[] ret = init_arr(N, -1);
		
		find_shelter(robot, shelter, ret, 0);
		
		return shelter2robot(ret);
	}
	
	public static int[] shelter2robot(int[] shelter) {
		int N = shelter.length;
		int[] ret = new int[N];
		for(int i=0; i<N; i++) {
			ret[shelter[i]] = i+1;
		}
		return ret;
	}
	
	public static boolean find_shelter(int[][] robot, int[][] shelter, int[] occupied, int idx) {
		int N = robot.length;
		
		if(idx >= N) return true;
		
		for(int i=0; i<N; i++) {
			if(occupied[i] > -1) continue;
			occupied[i] = idx;
			if(check_collision(robot, shelter, occupied, i)) {
				occupied[i] = -1;
				continue;
			}
			
			if(find_shelter(robot, shelter, occupied, idx+1))
				return true;
			
			occupied[i] = -1;
		}
		return false;
	}
	
	public static boolean check_collision(int[][] robot, int[][] shelter, int[] occupied, int idx) {
		boolean ret = false;
		int N = occupied.length;
		int[] src = shelter[idx];
		int[] dst = robot[occupied[idx]];
		
		for(int i=0; i<N; i++) {
			if(occupied[i] == -1 || i == idx) continue;
			int[] src_ = shelter[i];
			int[] dst_ = robot[occupied[i]];
			if(find_intersection(src, dst, src_, dst_)) return true;
		}
		
		return ret;
	}
	
	public static boolean find_intersection(int[] a, int[] b, int[] c, int[] d) {
		int abc = ccw(a, b, c);
		int abd = ccw(a, b, d);
		int cda = ccw(c, d, a);
		int cdb = ccw(c, d, b);
		
		if(abc*abd == 0 && cda*cdb == 0) {
			if(check_dist(new int[][] {a, b, c, d})) return true;
		}
		else if(abc*abd <= 0 && cda*cdb <= 0)
			return true;
		
		return false;
	}
	
	public static int ccw(int[] a, int[] b, int[] c) {
		int ret = a[0]*b[1] + b[0]*c[1] + c[0]*a[1];
		ret -= (a[1]*b[0] + b[1]*c[0] + c[1]*a[0]);
		
		return ret == 0? 0 : ret<0? -1 : 1;
	}
		
	public static boolean check_dist(int[][] pos) {
		boolean ret = false;
		
		int dx = pos[1][0] - pos[0][0];
		int dy = pos[1][1] - pos[0][1];
		
		for(int i=2; i<4; i++) {
			int dx_ = pos[i][0] - pos[0][0];
			int dy_ = pos[i][1] - pos[0][1];
			
			if(dx*dx_ < 0 || dy*dy_ < 0) continue;
			if(Math.abs(dx) >= Math.abs(dx_) && Math.abs(dy) >= Math.abs(dy_)) ret = true;
		}
		
		dx = pos[3][0] - pos[2][0];
		dy = pos[3][1] - pos[2][1];
		for(int i=0; i<2; i++) {
			int dx_ = pos[i][0] - pos[2][0];
			int dy_ = pos[i][1] - pos[2][1];
			
			if(dx*dx_ < 0 || dy*dy_ < 0) continue;
			if(Math.abs(dx) >= Math.abs(dx_) && Math.abs(dy) >= Math.abs(dy_)) ret = true;
		}
		
		return ret;
	}
	
	public static void print_ans(int[] ans) {
		int len = ans.length;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<len; i++) 
			sb.append(ans[i]).append("\n");
		System.out.println(sb.toString());
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		for(int i=0; i<N; i++)
			ret[i] = val;
		return ret;
	}
}
