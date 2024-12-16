package boj;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class MainB_28132 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_28132.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] vectors = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			vectors[i][0] = x;
			vectors[i][1] = y;
		}
		
		long ans = solve(vectors);
		
		System.out.println(ans);
		br.close();
	}
	
	public static long solve(int[][] vectors) {
		int N = vectors.length;
		long ret = 0, zero_cnt = 0;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int[][] scaled_vectors = init_map(vectors, map);
		
		for(int i=0; i<N; i++) {
			// WA ( 1% ) - consider multiple zero vectors
			if(vectors[i][0] == 0 && vectors[i][1] == 0) {
				zero_cnt++;
				continue;
			}
			
			int[] cur = scaled_vectors[i];
			
			String[] orth_keys = new String[] {
					coord2str(-cur[1], cur[0]), coord2str(cur[1], -cur[0])
			};
			
			for(int j=0; j<2; j++) {
				if(map.get(orth_keys[j]) != null) {
					ret += map.get(orth_keys[j]);
				}
			}
		}
		
		return ret/2L + zero_cnt * (N-zero_cnt) + zero_cnt * (zero_cnt - 1L) / 2L;
	}
	
	public static int[][] init_map(int[][] vectors, Map<String, Integer> map) {
		int N = vectors.length;
		int[][] ret = new int[N][2];
		
		for(int i=0; i<N; i++) {
			ret[i] = to_coprime(vectors[i][0], vectors[i][1]);
			String key = coord2str(ret[i][0], ret[i][1]);
			
			if(map.get(key) == null) {
				map.put(key, 1);
			} else {
				map.put(key, map.get(key)+1);
			}
		}
		
		return ret;
	}
	
	public static String coord2str(int x, int y) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%010d", x)).append(String.format("%010d", y));
		
		return sb.toString();
	}
	
	public static int[] to_coprime(int x, int y) {
		if(x == 0 && y == 0) return new int[] {x, y};
		BigInteger a = BigInteger.valueOf(x);
		BigInteger b = BigInteger.valueOf(y);
		
		int gcd = a.gcd(b).intValue();
		
		return new int[] { x/gcd, y/gcd };
	}
}
