package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_15172 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_15172.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int[][] monster = new int[4][2];
			int[][] client = new int[4][2];
			for(int i=0; i<4; i++) {
				for(int j=0; j<2; j++) {
					monster[i][j] = -1;
					client[i][j] = -1;
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			int m_cnt = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp < 0) client[-temp-1] = new int[] {i,j};
					else if(temp > 0) {
						monster[temp-1] = new int[] {i,j};
						m_cnt ++;
					}
				}
			}
			
			int ans = solve(monster, client, m_cnt);
			
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	static int solve(int[][] monster, int[][] client, int m_cnt) {
		int ret = 0;
		int[] check = new int[9];
		int[] pos = new int[] {0, 0};
		ret = travel(monster, client, check, pos, 0, 0, m_cnt*2, 0, Integer.MAX_VALUE);
		return ret;
	}
	
	static int travel(int[][] monster, int[][] client, int[] check, int[] pos, int idx, int cnt, int tar_cnt, int score, int min) {
		if(check[idx+4] == 1) return min;
		int[] dst;
		if(idx == 0) {
			dst = pos;
		}
		else if(idx < 0) {
			dst = client[-idx-1];
			if(check[-idx+4] == 0) return min;
		}
		else dst = monster[idx-1];
		
		if(dst[0] == -1) return min;
		
		int dist = Math.abs(pos[0] - dst[0]) + Math.abs(pos[1] - dst[1]);
		if(cnt == tar_cnt) 
			return (score + dist < min)? score+dist : min;
		
		check[idx+4] = 1;
		
		min = travel(monster, client, check, dst, 1, cnt+1, tar_cnt, score+dist, min);
		min = travel(monster, client, check, dst, 2, cnt+1, tar_cnt, score+dist, min);
		min = travel(monster, client, check, dst, 3, cnt+1, tar_cnt, score+dist, min);
		min = travel(monster, client, check, dst, 4, cnt+1, tar_cnt, score+dist, min);
		min = travel(monster, client, check, dst, -1, cnt+1, tar_cnt, score+dist, min);
		min = travel(monster, client, check, dst, -2, cnt+1, tar_cnt, score+dist, min);
		min = travel(monster, client, check, dst, -3, cnt+1, tar_cnt, score+dist, min);
		min = travel(monster, client, check, dst, -4, cnt+1, tar_cnt, score+dist, min);
		
		check[idx+4] = 0;
		return min;
	}
	
}
