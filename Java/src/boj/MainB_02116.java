package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_02116 {
	
	public static int[] opposite = new int[] {
			5, 3, 4, 1, 2, 0
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02116.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dices = new int[N][6];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(dices);
		System.out.println(ans);
	}
	
	public static int solve(int[][] dices) {
		int ret = 0;
		int N = dices.length;
		
		for(int j=1; j<7; j++) {
			int[] res = choose_max(dices[0], j);
			int score = res[0];
			for(int i=1; i<N; i++) {
				res = choose_max(dices[i], res[1]);
				score += res[0];
			}
			
			if(ret < score)
				ret = score;
		}
		
		
		return ret;
	}
	
	public static int[] choose_max(int[] dice, int bot) {
		int ret = 6;
		int top = 0;
		for(int i=0; i<6; i++) {
			if(dice[i] == bot) {
				top = dice[opposite[i]];
				break;
			}
		}
		
		if(top == 6 || bot == 6) {
			if(top ==5 || bot == 5)
				ret = 4;
			else
				ret = 5;
		}
		
		return new int[] {ret, top};
	}
}
