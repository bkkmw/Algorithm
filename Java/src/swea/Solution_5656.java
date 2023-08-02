package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656 {
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = solve(map, N);
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int[][] map, int N) {
		int ret = 0;
		int H = map.length;
		int W = map[0].length;
//		int[][] temp = copy_map(map);
		ret = recur(map, N, 0, 0, 181);
		return ret;
	}
	
	static int recur(int[][] map, int N, int stage, int col, int min) {
		if(min == 0) return 0;
		if(stage != 0) {
			drop(map, col);
			update(map);
		}
		if(stage == N) {
			int score = count_score(map);
			return (score<min)? score : min;
		}
		
		int emp_cnt = 0;
		for(int c=0; c<map[0].length; c++) {
			if(map[map.length-1][c] == 0) {
				emp_cnt++;
				continue;
			}
			int[][] temp = copy_map(map);
			min = recur(map, N, stage+1, c, min);
			map = copy_map(temp);
		}
		if(emp_cnt == map[0].length) return 0;
		return min;
	}
	
	static void update(int[][] map) {
		int H = map.length; int W = map[0].length;
		for(int j=0; j<W; j++) {
			for(int i=H-1; i>=0; i--) {
				if(map[i][j] == 0) {
					int ni = i-1;
					while(ni>=0 && map[ni][j] == 0) ni--;
					if(ni == -1) break;
					map[i][j] = map[ni][j];
					map[ni][j] = 0;
				}
			}
		}
	}
	
	static void drop(int[][] map, int col) {
		int H = map.length; int W = map[0].length;
		boolean[][] check = new boolean[H][W];
		Queue<int[]> q = new LinkedList<int[]>();
		
		int row;
		for(row=0; row<H; row++)
			if(map[row][col] != 0) break;
		
		q.add(new int[] {row, col});
		check[row][col] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];	int c = cur[1];
			int len = map[r][c];
			map[r][c] = 0;
			for(int d=0; d<4; d++) {
				int nr = r; int nc = c;
				for(int l=1; l<len; l++) {
					nr = nr + dir_yx[d][0];
					nc = nc + dir_yx[d][1];
					if(nr<0 || nr>=H || nc<0 || nc>=W) break;
					if(check[nr][nc]) continue;
					q.add(new int[] {nr, nc});
					check[nr][nc] = true;
				}
			}
		}
	}
	
	static int count_score(int[][] map) {
		int ret = 0;
		int H = map.length;
		int W = map[0].length;
		for(int i=0; i<H; i++)
			for(int j=0; j<W; j++)
				if(map[i][j] != 0) ret++;
		return ret;
	}
	
	static int[][] copy_map(int[][] map){
		int H = map.length;
		int W = map[0].length;
		int[][] ret = new int[H][W];
		for(int i=0; i<H; i++) 
			for(int j=0; j<W; j++) 
				ret[i][j] = map[i][j];
		return ret;
	}
}
