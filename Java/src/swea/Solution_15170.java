package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_15170 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_15170.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			int[][] gate = new int[3][2];
			for(int i=0; i<3; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				gate[i][0] = Integer.parseInt(st.nextToken())-1;
				gate[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int ans = solve(gate, N);
						
			sb.append('#').append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	static int solve(int[][] gate, int N) {
		int ret = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i == j) continue;
				for(int k=0; k<3; k++) {
					if(i == k || j == k) continue;
					int[] gate_order = new int[] {i, j, k};
					boolean[] check = new boolean[N];
					ret = put(gate, check, gate_order, 0, 0, 0, ret);
					
				}
					
			}
		}
		return ret;
	}
	
	static int put(int[][] gate, boolean[] check, int[] order, 
			int g_cnt, int p_cnt, int score, int min) {
		if(gate[order[g_cnt]][1] == p_cnt) {
			g_cnt ++;
			p_cnt = 0;
		}
		if(g_cnt == 3) return (score < min)? score : min;
		
		int g_pos = gate[order[g_cnt]][0];
		int[] left = check_pos(check, g_pos, -1);
		int[] right = check_pos(check, g_pos, +1);
		
		if(left[0] == right[0]) {
			check[left[0]] = true;
			min = put(gate, check, order, g_cnt, p_cnt+1, score+left[1], min);
			check[left[0]] = false;
		}
		else if(left[1] == right[1] && left[0] != -1 && right[0] != check.length) {
			int rem = gate[order[g_cnt]][1] - p_cnt;
			if(rem == 1) {
				check[left[0]] = true;
				min = put(gate, check, order, g_cnt, p_cnt+1, score+left[1], min);
				check[left[0]] = false;
				check[right[0]] = true;
				min = put(gate, check, order, g_cnt, p_cnt+1, score+right[1], min);
				check[right[0]] = false;
				
			}
			else {
				check[left[0]] = true;
				check[right[0]] = true;
				min = put(gate, check, order, g_cnt, p_cnt+2, score+left[1]+right[1], min);
				check[left[0]] = false;
				check[right[0]] = false;
			}
		}
		else {
			int[] next;
			if(left[0] == -1) next = right;
			else if(right[0] == check.length) next = left;
			else next = (left[1] < right[1])? left : right;
			
			check[next[0]] = true;
			min = put(gate, check, order, g_cnt, p_cnt+1, score+next[1], min);
			check[next[0]] = false;
		}
		
		return min;
	}
	
	static int[] check_pos(boolean[] check, int g_pos, int dir) {
		int[] ret = new int[2];
		ret[0] = g_pos;
		ret[1] = 1;
		while(ret[0]>=0 && ret[0]<check.length && check[ret[0]] == true) {
			ret[0] += dir;
			ret[1] ++;
		}
		return ret;
	}
	
}
