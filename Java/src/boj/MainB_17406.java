package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17406 {
	static int[][] dir_cl = {
			{0,1}, {+1,0}, {0,-1}, {-1,0}
	};
	static int[][] map = null;
	static int[][] ans = null;
	static int[] check = null;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17406.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		ans = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
				ans[i][j] = map[i][j];
			}
		}
		
		int[][] cmds = new int[K][3];
		check = new int[K];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cmds[i][0] = Integer.parseInt(st.nextToken());
			cmds[i][1] = Integer.parseInt(st.nextToken());
			cmds[i][2] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<K; i++) {
			solve(cmds, 1, i);
		}
		System.out.println(min);
	}
	
	static void solve(int[][]cmds, int depth, int idx) {
		check[idx] = 1;
		int[][] temp_map = copy(map);
		int[][] temp_ans = copy(ans);
		rotate(cmds[idx][0], cmds[idx][1], cmds[idx][2]);
		update(ans);
		
		if(depth == cmds.length) {
			int temp = calc();
			if(temp < min) min = temp;
			check[idx] = 0;
			map = copy(temp_map);
			ans = copy(temp_ans);
			return;
		}
		for(int i=0; i<cmds.length; i++) {
			if(check[i%cmds.length] == 0) {
				solve(cmds, depth+1, i%cmds.length);
			}
		}
		map = copy(temp_map);
		ans = copy(temp_ans);
		check[idx] = 0;
		return;
	}
	
	static int[][] copy(int[][] src){
		int[][] ret = new int[src.length][src[0].length];
		for(int i=0; i<src.length; i++) {
			for(int j=0; j<src[0].length; j++) {
				ret[i][j] = src[i][j];
			}
		}
		return ret;
	}
	
	static void update(int[][] ans) {
		for(int i=0; i<ans.length; i++) {
			for(int j=0; j<ans[0].length; j++) {
				map[i][j] = ans[i][j];
			}
		}
	}
	
	static void rotate(int r, int c, int s) {

		for(int tr=1; tr<=s; tr++) {
			int dir = 0;
			int cr = r-1-tr;		int cc = c-1-tr;
			int len = (2*(tr)+1)*(2*(tr)+1) - (2*(tr-1)+1)*(2*(tr-1)+1);
			int cnt = 0;
			while(cnt < len) {
				int nr = cr + dir_cl[dir][0];
				int nc = cc + dir_cl[dir][1];
				ans[nr][nc] = map[cr][cc];
				cr = nr;	cc = nc;
				cnt++;				
				if(cnt%(2*tr) == 0) dir ++;
			}
		}
	}
	
	static int calc() {
		int ret = Integer.MAX_VALUE;
		int N = ans.length;
		int M = ans[0].length;
		for(int i=0; i<N; i++) {
			int row_sum = 0;
			for(int j=0; j<M; j++) {
				row_sum += ans[i][j];
			}
			if(row_sum < ret) ret = row_sum;
		}
		return ret;
	}
	
}
