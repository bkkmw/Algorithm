package boj;

import java.util.*;
import java.io.*;

public class MainB_17386 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17386.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] pos = new int[2][4];
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				pos[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		boolean ans;
		try {
			ans = solve(pos);		
		} catch(ArithmeticException e) {
			ans = handle_inf(pos);
		}
		System.out.println(ans? 1 : 0);
	}
	
	public static boolean solve(int[][] pos) throws ArithmeticException {
		float[][] line = new float[2][2];
		
		for(int i=0; i<2; i++) {
			if(pos[i][0] == pos[i][2])
				throw new ArithmeticException();
			line[i][0] = (float)(pos[i][3]-pos[i][1]) / (pos[i][2]-pos[i][0]);
			line[i][1] = (float)(pos[i][1] - line[i][0]*pos[i][0]);
		}
		
		if(line[0][0] == line[1][0])
			return false;
		
		float cx = (line[1][1] - line[0][1]) / (line[0][0] - line[1][0]);
		
		return in_range(cx, pos);
	}
	
	public static boolean in_range(float cx, int[][] pos) {
		int cnt = 0;
		int min, max;
				
		for(int i=0; i<2; i++) {
			min = pos[i][0]<pos[i][2]? pos[i][0] : pos[i][2];
			max = pos[i][0]>pos[i][2]? pos[i][0] : pos[i][2];
			
			if(min <= cx && cx <= max) cnt++;
		}
		
		return cnt==2? true : false;
	}
	
	public static boolean handle_inf(int[][] pos) {
		if(pos[0][0] == pos[0][2] && pos[1][0] == pos[1][2]) {
			return false;
		}
		else if(pos[0][0] == pos[0][2]) {
			return in_range(pos[0][0], pos) && get_intersection(pos, 1); 
		}
		else {
			return in_range(pos[1][0], pos) && get_intersection(pos, 0);
		}
	}
	
	public static boolean get_intersection(int[][] pos, int idx) {
		int nidx = (idx+1)%2;
		float y_pos = pos[idx][1] + (pos[idx][3]-pos[idx][1]) * (pos[nidx][0] - pos[idx][0]) / (pos[idx][2]-pos[idx][0]);
		return (pos[nidx][1] <= y_pos && y_pos <= pos[nidx][3]) || (pos[nidx][3] <= y_pos && y_pos <= pos[nidx][1]);
	}
}
