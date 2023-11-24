package boj;

import java.io.*;
import java.util.*;

public class MainB_19598 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_19598.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] start = new int[N];
		int[] end = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			start[i] = Integer.parseInt(st.nextToken());
			end[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(start, end);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[] st, int[] ed) {
		int ret = 0, N = st.length, occupied = 0;
		
		Arrays.sort(st);
		Arrays.sort(ed);
		
		int sidx=0, eidx=0;
		occupied ++;
		
		for(sidx=1; sidx<N; sidx++) {
			int time = st[sidx];
			while(ed[eidx] <= time) {
				occupied --;
				eidx ++;
			}
			
			occupied++;
			ret = (ret < occupied)? occupied : ret;
		}
		
		return ret;
	}
}
