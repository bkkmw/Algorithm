package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_12891_2{
	static int count[][];	// A C G T
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_12891.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		count = new int[S+1][4];
		
		line = br.readLine();
		read_data(line, S);
		
		line = br.readLine();
		st = new StringTokenizer(line, " ");
		
		int[] min = new int[4];
		for(int i=0; i<4; i++) {
			min[i] = Integer.parseInt(st.nextToken());
		}
		
		sb.append(solve(S,P,min));
		
		System.out.println(sb.toString());
	}
	
	static void read_data(String line, int S) {
		char temp;
		for(int i=0; i<S; i++) {
			for(int j=0; j<4; j++) {
				count[i+1][j] = count[i][j];
			}
			temp = line.charAt(i);
			if(temp == 'A') count[i+1][0] ++;
			else if(temp == 'C') count[i+1][1] ++;
			else if(temp == 'G') count[i+1][2] ++;
			else count[i+1][3] ++;			
		}
	}
	
	static int solve(int S, int P, int[] min) {
		
		int ret = 0;
		for(int i=1; i<=S-P+1; i++) {
			if(count[i+P-1][0] - count[i-1][0] < min[0]) continue;
			if(count[i+P-1][1] - count[i-1][1] < min[1]) continue;
			if(count[i+P-1][2] - count[i-1][2] < min[2]) continue;
			if(count[i+P-1][3] - count[i-1][3] < min[3]) continue;
			ret ++;
		}
		return ret;
	}
}
