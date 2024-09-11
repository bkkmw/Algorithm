package boj;

import java.io.*;
import java.util.*;

public class MainB_02109 {
	
	public static int INF_PRICE = 10001;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02109.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] req = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			req[i][0] = Integer.parseInt(st.nextToken());
			req[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(req);
		
		System.out.println(ans);
	}
	
	public static int solve(int[][] req) {
		int ret = 0, N = req.length, cur = 1;
		boolean[] checked = new boolean[N];
		
		Arrays.sort(req, (o1,o2) -> {
			return o1[1] == o2[1] ? o2[0]-o1[0] : o1[1] - o2[1];
		});
		
		for(int i=0; i<N; i++) {
			if(req[i][1] >= cur) {
				cur++;
				ret += req[i][0];
				checked[i] = true;
				
			} else {
				int[] min = find_min(req, checked);
				if(min[0] < req[i][0]) {
					ret += (req[i][0] - min[0]);
					checked[i] = true;
					checked[min[1]] = false;
				}
			}
			
		}
		
		return ret;
	}
	
	public static int[] find_min(int[][] req, boolean[] checked) {
		int N = req.length, ret = INF_PRICE, idx = -1;
		
		for(int i=0; i<N; i++) {
			if(!checked[i]) continue;
			if(ret > req[i][0]) {
				ret = req[i][0];
				idx = i;
			}
		}
		
		return new int[] {ret, idx};
	}

}
