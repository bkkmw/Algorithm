package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_6808 {
	static int[] factorials = new int[] {
			1, 2, 6, 24, 120, 720, 5040, 40320 
	};
	static int[] cards = new int[19]; // total 171
	static int[] dealer = new int[10];
	static int[] player = new int[10];
	static int[] check = new int[9];
	static int pw = 0;
	static int dw = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/swea/input_6808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			clear();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int deal_sum = 0;
			for (int i = 1; i < 10; i++) {
				dealer[i] = Integer.parseInt(st.nextToken());
				deal_sum += dealer[i];
				cards[dealer[i]] = 1;
			}
			dealer[0] = deal_sum;
			int idx = 1;
			for (int i = 1; i < 10; i++) {
				while (cards[idx] != 0) {
					idx++;
				}
				player[i] = idx++;
			}
			player[0] = 171 - deal_sum;
			for (int i = 1; i < 10; i++)
				play(1, i, 0, 0);

			System.out.printf("#%d %d %d\n", test_case, dw, pw);
		}
	}

	static void clear() {
		for (int i = 0; i < 19; i++)
			cards[i] = 0;
		pw = 0;
		dw = 0;
	}

	// counting remaining scores leads to lower performance
	static void play(int round, int idx, int score, int winner) {
		if (check[idx - 1] == 1) return;
		if(winner != 0 && round != 9) {
			int game_left = factorials[8-round];
			if(winner > 0) pw += game_left;
			else dw += game_left;
			return;
		}
		check[idx - 1] = 1;
		int diff = player[idx] - dealer[round];
		int point = player[idx] + dealer[round];
		score = score + ((diff > 0) ? point : -point);
		player[0] -= player[idx];
		dealer[0] -= dealer[round];
		if(score > dealer[0]) winner = 1;
		else if(player[0] + score < 0) winner = -1;
		else winner = 0;
		
		if (round == 9) {
			if (score > 0)
				pw++;
			else if (score < 0)
				dw++;
			
			check[idx - 1] = 0;
			player[0] += player[idx];
			dealer[0] += dealer[round];
			return;
		}

		for (int i = 1; i < 10; i++) {
			play(round + 1, i, score, 0);
		}
		check[idx - 1] = 0;
		player[0] += player[idx];
		dealer[0] += dealer[round];
	}

}
