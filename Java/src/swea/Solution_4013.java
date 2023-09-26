package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4013 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			int K = Integer.parseInt(br.readLine());
			int[][] status = new int[4][8];
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<8; j++) {
					status[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] cmd = new int[K][2];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				cmd[i][0] = Integer.parseInt(st.nextToken())-1;
				cmd[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int ans = solve(status, cmd);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	static int solve(int[][] status, int[][] cmd) {
		int K = cmd.length;
		int[] rotate_status;
		for(int i=0; i<K; i++) {
			rotate_status = check_status(status, cmd[i]);
			status = rotate(status, rotate_status);
		}
		return calc_score(status);
	}
	
	static int[] check_status(int[][] status, int[] cmd) {
		int[] ret = new int[4];
		ret[cmd[0]] = cmd[1];
		int next = cmd[0] - 1;
		while(next >= 0) {
			if(status[next][2] != status[next+1][6]) {
				ret[next] = -ret[next+1];
				next --;
			}
			else break;
		}
		next = cmd[0] + 1;
		while(next < 4) {
			if(status[next][6] != status[next-1][2]) {
				ret[next] = -ret[next-1];
				next ++;
			}
			else break;
		}
		return ret;
	}
	
	static int[][] rotate(int[][] status, int[] r_status){
		for(int i=0; i<4; i++) {
			status[i] = rotate_one(status[i], r_status[i]);
		}
		return status;
	}
	
	static int[] rotate_one(int[] row_status, int dir) {
		int[] ret = new int[8];
		for(int i=8; i<16; i++) {
			ret[(i+dir)%8] = row_status[i%8];
		}
		return ret;
	}
	
	static int calc_score(int[][] status) {
		int ret = 0;
		for(int i=0; i<4; i++) {
			ret += (1 << i) * status[i][0];
		}
		return ret;
	}
}
