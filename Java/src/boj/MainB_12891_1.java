package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainB_12891_1{
	static int[] count = new int[4];	// A C G T
	static int[] min = new int[4];
	static HashMap<Character, Integer> map = new HashMap<Character, Integer>(); 
	
	public static void main(String[] args) throws Exception{
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
		int ans = 0;
		
		System.setIn(new FileInputStream("input/boj/input_12891.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		
		line = br.readLine();
		st = new StringTokenizer(line," ");
		for(int i=0; i<4; i++) {
			min[i] = Integer.parseInt(st.nextToken());
		}
		
		init_cnt(P, str);
		
		for(int i=0; i<S-P; i++) {
			ans += calc();
			count[map.get(str.charAt(i))]--;
			count[map.get(str.charAt(i+P))]++;
		}
		ans += calc();
		System.out.println(ans);
	}
	
	static void init_cnt(int P, String str) {
		for(int i=0; i<P; i++) {
			count[map.get(str.charAt(i))] ++;
		}
	}
	
	static int calc() {
		for(int i=0; i<4; i++) {
			if(count[i] < min[i]) return 0;
		}
		return 1;
	}

}
