package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainB_11660 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_11660.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		StringBuilder sb = new StringBuilder();
		
		line = in.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			line = in.readLine();
			st = new StringTokenizer(line, " ");
			map[i][0] = 0;
			int temp = map[i][0];
			for(int j=1; j<N+1; j++) {
				temp += Integer.parseInt(st.nextToken());
				map[i][j] = temp + map[i-1][j];
			}
		}
		
		for(int i=0; i<M; i++) {
			line = in.readLine();
			st = new StringTokenizer(line, " ");
			int startx = Integer.parseInt(st.nextToken());
			int starty = Integer.parseInt(st.nextToken());
			int endx = Integer.parseInt(st.nextToken());
			int endy = Integer.parseInt(st.nextToken());
			int ans = map[endx][endy] - map[endx][starty-1] - map[startx-1][endy] + map[startx-1][starty-1];
			sb.append(ans);
			sb.append("\n");

		}
		
		System.out.println(sb.toString());
		in.close();
	}
}
