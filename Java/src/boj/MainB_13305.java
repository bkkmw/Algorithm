package boj;

import java.io.*;
import java.util.*;

public class MainB_13305 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_13305.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] cities = new int[N][2];
		
		StringTokenizer dist = new StringTokenizer(br.readLine());
		StringTokenizer price = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N-1; i++) {
			cities[i][0] = Integer.parseInt(dist.nextToken());
			cities[i][1] = Integer.parseInt(price.nextToken());
		}
		cities[N-1][1] = Integer.parseInt(price.nextToken());
		
		long ans = solve(cities);
		
		System.out.println(ans);
	}
	
	public static long solve(int[][] cities) {
		long ret = 0;
		int N = cities.length;
		
		for(int i=0; i<N-1; i++) {
			int dist = cities[i][0], cnt = 1;
			
			while(cities[i][1] < cities[i+cnt][1]) {
				dist += cities[i+cnt][0];
				cnt++;
				if(i+cnt > N-1) 
					break;
			}
			
			ret += (long)dist*cities[i][1];
			i += cnt-1;
		}
		
		return ret;
	}
}
