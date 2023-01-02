package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_17215 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int frames[] = new int[11];
		int pins[] = to_frame(br.readLine(), frames);
		
		int ans = calc_score(frames, pins);
		System.out.println(ans);
		br.close();
	}
	
	static int calc_score(int[] frames, int[] pins) {
		int ret = 0;
		
		for(int i=0; i<9; i++) {
			int frame_score = (frames[i] > 0)? 10 : (pins[2*i] + pins[2*i +1]);
			int bonus_score = 0;
			
			int next_idx = 2*(i+1);
			for(int j=0; j<frames[i]; j++) {
				if(pins[next_idx] == -1) next_idx++;
				bonus_score += pins[next_idx++];
			}
			
			ret += frame_score;
			ret += bonus_score;
		}
		
		for(int i=18; i<21; i++) ret += (pins[i] >= 0)? pins[i] : 0;
		return ret;
	}
	
	static int[] to_frame(String line, int[] frames) {
		int[] ret = new int[22];
		int ridx = 0;

		for(int i=0; i<line.length(); i++) {
			char temp = line.charAt(i);
			if(temp == 'S') {
				ret[ridx++] = 10;
				if(ridx/2 < 9)	ret[ridx++] = -1;
				frames[(ridx-1)/2] = 2;
			}
			else if(temp == 'P') {
				ret[ridx++] = (10 - ret[ridx-2]);
				frames[(ridx-1)/2] = 1;
			}
			else if(temp == '-') {
				ret[ridx++] = 0;
			}
			else {
				ret[ridx++] = temp - 48;
			}
		}
		
		for(int i=ridx; i<21; i++) {
			ret[i] = -1;
		}
		
		return ret;
	}
}
