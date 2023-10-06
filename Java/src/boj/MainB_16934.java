package boj;

import java.util.*;
import java.io.*;

public class MainB_16934 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16934.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] nicknames = new String[N];
		
		for(int i=0; i<N; i++)
			nicknames[i] = br.readLine();
		
		solve(nicknames, sb);
		System.out.println(sb.toString());
		
		br.close();
	}
	
	public static void solve(String[] nicknames, StringBuilder sb) {
		int N = nicknames.length;
		Map<String, Integer> map = new HashMap<String, Integer>(N*10);
		
		for(int i=0; i<N; i++) {
			String nickname = nicknames[i], substr, ret = "";
			int len = nickname.length();
			
			for(int j=1; j<len; j++) {
				substr = nickname.substring(0, j);
				
				if(map.get(substr) == null) {
					map.put(substr, 0);
					ret = ret.equals("")? substr : ret;
				}
			}
			
			Integer cnt = map.get(nickname);
			cnt = cnt==null ? 0 : cnt;
			
			if(ret.equals("")) {
				ret = nickname + (cnt==0? "" : cnt+1);
			}
			map.put(nickname, cnt+1);
			sb.append(ret).append("\n");
		}
	}
}
