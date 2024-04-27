package boj;

import java.io.*;
import java.util.*;

public class MainB_20436 {
	
	public static int[][] key_pos = new int[][] {
		{0,1,0}, {1,2,4}, {0,2,2}, {0,1,2}, {0,0,2}, {0,1,3}, {0,1,4}, {1,1,5}, {1,0,7}, {1,1,6},
		{1,1,7}, {1,1,8}, {1,2,6}, {1,2,5}, {1,0,8}, {1,0,9}, {0,0,0}, {0,0,3}, {0,1,1}, {0,0,4},
		{1,0,6}, {0,2,3}, {0,0,1}, {0,2,1}, {1,0,5}, {0,2,0}
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_20436.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] lr_char = br.readLine().split(" ");
		
		String target = br.readLine();
		
		int ans = solve(target, lr_char[0].charAt(0), lr_char[1].charAt(0));
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(String target, char lchar, char rchar) {
		int ret = 0, len = target.length();
		int[] lpos = key_pos[lchar-97];
		int[] rpos = key_pos[rchar-97];
		
		for(int i=0; i<len; i++) {
			char tar = target.charAt(i);
			int[] next_pos = key_pos[tar-97];
			int dist = 0;
			if(next_pos[0] == 0) {
				dist = Math.abs(lpos[1] - next_pos[1]) + Math.abs(lpos[2] - next_pos[2]);
				lpos = next_pos;
			} else {
				dist = Math.abs(rpos[1] - next_pos[1]) + Math.abs(rpos[2] - next_pos[2]);
				rpos = next_pos;
			}
			
			dist++;
			ret += dist;
		}
		
		return ret;
	}
}
