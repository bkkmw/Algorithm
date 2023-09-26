package boj;

import java.util.*;
import java.io.*;

public class MainB_09207 {
	
	public static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	public static boolean[] left_pin = new boolean[] {false, false, true};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_09207.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		char[][] map = new char[5][9];
		
		for(int tc=1; tc<TC+1; tc++) {
			int pins = 0;
			int[][] pin_yx = new int[8][2];
			
			for(int i=0; i<5; i++) {
				String line = br.readLine();
				for(int j=0; j<9; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == 'o') {
						pin_yx[pins][0] = i;
						pin_yx[pins++][1] = j;
					}
				}
			}
			
			Queue<int[]>[] qs = new Queue[pins];
			for(int i=0; i<pins; i++)
				qs[i] = new LinkedList<int[]>();
			
			int ans = solve(map, pins, qs, Arrays.copyOfRange(pin_yx, 0, pins));
			sb.append(ans).append(" ").append(pins-ans).append("\n");
			br.readLine();
		}
		
		System.out.println(sb.toString());
	}
	
	public static int solve(char[][] map, int left, Queue<int[]>[] qs, int[][] pin_yx) {
		if(left == 1) return left;
		int ret = left;
		find_possible(map, pin_yx, qs[left-1]);
		
		while(!qs[left-1].isEmpty()) {
			int[] poll = qs[left-1].poll();
			move_pin(map, poll[0], poll[1], poll[2], false);
			pin_yx[poll[3]][0] = poll[0] + 2*dir_yx[poll[2]][0];
			pin_yx[poll[3]][1] = poll[1] + 2*dir_yx[poll[2]][1];
			
			int res = solve(map, left-1, qs, pin_yx);
			if(ret > res)
				ret = res;
			
			
			move_pin(map, poll[0], poll[1], poll[2], true);
			pin_yx[poll[3]][0] = poll[0];
			pin_yx[poll[3]][1] = poll[1];
			
		}
		
		return ret;
	}
	
	public static void find_possible(char[][] map, int[][] pin_yx, Queue<int[]> q) {
		int len = pin_yx.length;
		for(int i=0; i<len; i++) {
			if(map[pin_yx[i][0]][pin_yx[i][1]] != 'o') continue;
			
			for(int d=0; d<4; d++) {
				int nny = pin_yx[i][0] + 2*dir_yx[d][0];
				int nnx = pin_yx[i][1] + 2*dir_yx[d][1];
				if(nny<0 || nny>4 || nnx<0 || nnx>8) continue;
				
				int ny = nny - dir_yx[d][0];
				int nx = nnx - dir_yx[d][1];
				if(map[ny][nx] == 'o' && map[nny][nnx] == '.') {
					q.add(new int[] {pin_yx[i][0], pin_yx[i][1], d, i});
				}
			}
		}
	}
	
	public static void move_pin(char[][] map, int y, int x, int d, boolean recovery) {
		for(int i=0; i<3; i++) {
			map[y][x] = (left_pin[i] ^ recovery)? 'o' : '.';
			y += dir_yx[d][0];
			x += dir_yx[d][1];
		}
	}
}
