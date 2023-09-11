package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_09702 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_09702.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<TC+1; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] seq = new int[N];
			
			for(int i=0; i<N; i++)
				seq[i] = Integer.parseInt(br.readLine());
			
			int ans = solve(seq);
			sb.append(String.format("Case #%d: %d\n", tc, ans));
		}
		System.out.println(sb.toString());
	}
	
	public static int solve(int[] seq) {
		int ret = 0;
		int N = seq.length;
		
		for(int i=0; i<N; i++) {
			int[] cnt = new int[N-i];
			cnt[i-i] = 1;
			ret += 1;
			for(int j=i+1; j<N; j++) {
				int largest = 1;
				int len = 1;
				
				for(int k=j-1; k>i-1; k--) {
					if(seq[j] > seq[k] && cnt[k-i] >= largest)
						largest = cnt[k-i] + 1;
					if(len < cnt[k-i])
						len = cnt[k-i];
				}
				
				cnt[j-i] = largest;
				ret += len > largest? len : largest;
			}
		}
			
		return ret;
	}
}
