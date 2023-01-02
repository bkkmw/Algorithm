package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_07682 {
	
	static int[][][] train_pos = new int[][][] {
		{{0,0}, {1,0}, {2,0}},
		{{0,1}, {1,1}, {2,1}},
		{{0,2}, {1,2}, {2,2}},
		{{0,0}, {0,1}, {0,2}},
		{{1,0}, {1,1}, {1,2}},
		{{2,0}, {2,1}, {2,2}},
		{{0,0}, {1,1}, {2,2}},
		{{0,2}, {1,1}, {2,0}}
	};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_07682.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		
		while(!line.equals("end")) {
			String out = (solve(line) == 1)? "valid" : "invalid";
			sb.append(out).append("\n");
			line = br.readLine();
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static int solve(String line) {
		int ret = 1;
		char map[][] = new char[3][3];
		int p1 = 0, p2 = 0, empty = 0;
		
		for(int i=0; i<9; i++) {
			char temp = line.charAt(i);
			map[i/3][i%3] = temp;
			if(temp == 'X') p1++;
			else if(temp == 'O') p2++;
			else empty++;
		}
		
		if((p1 > p2+1) || (p1 < p2)) return 0;
		
		int train_cnt = check_trains(map, (p1==p2));
		
		if(train_cnt < 0) return 0;
		else if(train_cnt > 0) return 1;
		else if(empty > 0) return 0;	
		
		return ret;
	}
	
	static int check_trains(char[][] map, boolean p2_turn) {
		// no trains : 0, invalid : -1, valid : 1
		int p1 = 0, p2 = 0;
		int check[] = new int[8];
		
		for(int tp=0; tp<8; tp++) {
			int[][] pos = train_pos[tp];
			int cnt = 0;
			char base = map[pos[0][0]][pos[0][1]];
			if(base =='.') continue;
			
			for(int p=1; p<3; p++) {
				if(base != map[pos[p][0]][pos[p][1]]) break;
				cnt ++;
			}
			if(cnt == 2) {
				if(base == 'X') {
					if(p2_turn) return -1;
					p1++;
					check[tp] = 1;
				}
				else {
					if(!p2_turn) return -1;
					p2++;
					check[tp] = 2;
				}
			}
		}
		
		if(p1+p2 == 0) return 0;
		if(p1+p2 == 1) return 1;
		if(p1 > 0 && p2 > 0) return -1;
		if(multiple_train_check(check, (p1!=0)? 1 : 2)) return 1;

		return -1;
	}
	
	static boolean multiple_train_check(int[] check, int player) {
		boolean base_found = false;
		int[] base_pos = new int[3];
		for(int tp=0; tp<8; tp++) {
			if(check[tp] == 0) continue;
			int[][] pos = train_pos[tp];
			if(!base_found) {
				for(int i=0; i<3; i++) 
					base_pos[i] = 3*pos[i][0] + pos[i][1];
				base_found = true;
			}
			else {
				boolean valid = false;
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						int pos_to_int = 3*pos[i][0] + pos[i][1];
						if(pos_to_int == base_pos[j]) valid = true;
					}
				}
				if(!valid) return false;
			}
		}
		return true;
	}	
}
