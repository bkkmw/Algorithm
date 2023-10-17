package boj;

import java.util.*;
import java.io.*;

// WA
public class MainB_01695_Out2In {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01695.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(seq);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[] seq) {
		int ret = 0;
		int N = seq.length;
		int lidx = 0, ridx = N-1;
		
		while(lidx < ridx) {
			if(seq[lidx] == seq[ridx]) {
				lidx ++;
				ridx --;
				continue;
			}
			
			int next = find_num_pos(seq, lidx, ridx);
			if(next == 0) {
				ret += 1;
//				ridx--;
				lidx++;
			}
			else if(next < 0) {
				ret -= next;
				ridx += (next - 1);
				lidx ++;
			}
			else {
				ret += next;
				ridx --;
				lidx += next+1;
			}
		}
		
		return ret;
	}
	
	public static int find_num_pos(int[] seq, int lidx, int ridx) {
		int cnt = 1;
		while(cnt < (ridx-lidx)) {
			if(seq[lidx] == seq[ridx-cnt]) return -cnt;
			if(seq[ridx] == seq[lidx+cnt]) return cnt;
			cnt++;
		}
		return 0;
	}
}
