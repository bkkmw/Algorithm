package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_11053 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_11053.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(seq);
		System.out.println(ans);
	}
	
	public static int solve(int[] seq) {
		int ret = 1;
		int N = seq.length;
		int[] mem = new int[N];
		
		mem[0] = 1;
		for(int i=1; i<N; i++) {
			int largest = 1;
			for(int j=i-1; j>-1; j--) {
				if(seq[j] < seq[i] && mem[j] >= largest)
					largest = mem[j]+1;
			}
			
			mem[i] = largest;
			if(mem[i] > ret)
				ret = mem[i];
		}
		
		
		return ret;
	}
	
	
}
