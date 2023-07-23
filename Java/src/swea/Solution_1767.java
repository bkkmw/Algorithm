package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1767 {
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,1}, {1,0}, {0,-1}
	};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_1767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int[][] cores = new int[12][3];
			int cidx = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) cores[cidx++] = new int[]{i, j, 0};
				}
			}
			int ans = solve(map, cores, cidx);
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static int max_core;
	
	static int solve(int[][] map, int[][] cores, int c_cnt) {
		int ret = Integer.MAX_VALUE;
		cores = Arrays.copyOfRange(cores, 0, c_cnt);
		cores = update_core(cores, map.length);
		int bound_core = max_core;
		
		for(int d=0; d<4; d++) ret = connect(map,cores,0, d, 0, ret, bound_core);
		
		return ret;
	}
	
	static int connect(int[][] map, int[][] cores,
			int idx, int dir, int len, int min, int on) {
		
		if(idx == cores.length) {
			return return_len(len, min, on);
		}
		
		if(on + cores.length - idx < max_core) return min;
		
		if(cores[idx][2] != 1) {
			if(check_conn(map, cores[idx][0], cores[idx][1], dir)) {
				len += connect(map, cores[idx][0], cores[idx][1], dir);
				on++;
				cores[idx][2] = 1;
			}
		}
		
		for(int d=0; d<4; d++) min = connect(map, cores, idx+1, d, len, min, on);
		
		if(cores[idx][2] == 1) {
			len -= disconnect(map, cores[idx][0], cores[idx][1], dir);
			on --;
			cores[idx][2] = 0;
		}
		
		return min;
	}
	
	static int return_len(int clen, int minlen, int ccore) {
		if(ccore < max_core) return minlen;
		else if(ccore == max_core) return Math.min(minlen, clen);
		else {
			max_core = ccore;
			return clen;
		}
	}
	
	static boolean check_conn(int[][] map, int i, int j, int dir) {
		i += dir_yx[dir][0];
		j += dir_yx[dir][1];
		while(i>=0 && i<map.length && j>=0 && j<map.length) {
			if(map[i][j] != 0) return false;
			i += dir_yx[dir][0];
			j += dir_yx[dir][1];
		}
		return true;
	}
	
	static int connect(int[][] map, int i, int j, int dir) {
		int ret = 0;
		i += dir_yx[dir][0];
		j += dir_yx[dir][1];
		while(i>=0 && i<map.length && j>=0 && j<map.length && map[i][j] == 0) {
			map[i][j] = 9;
			i += dir_yx[dir][0];
			j += dir_yx[dir][1];
			ret ++;
		}
		return ret;
	}
	
	static int disconnect(int[][] map, int i, int j, int dir) {
		int ret = 0;
		i += dir_yx[dir][0];
		j += dir_yx[dir][1];
		while(i>=0 && i<map.length && j>=0 && j<map.length) {
			if(map[i][j] == 1) return ret;
			map[i][j] = 0;
			i += dir_yx[dir][0];
			j += dir_yx[dir][1];
			ret ++;
		}
		return ret;
	}
	
	static int[][] update_core(int[][] cores, int N) {
		int[][] ret = new int[cores.length][3];
		int cnt = 0;
		for(int i=0; i<cores.length; i++) {
			int cy = cores[i][0];	int cx = cores[i][1];
			if(cy == 0 || cy == N-1 || cx == 0 || cx == N-1) {
				max_core++;
				continue;
			}
			ret[cnt++] = cores[i];
		}
		return Arrays.copyOfRange(ret, 0, cnt);
	}
}
