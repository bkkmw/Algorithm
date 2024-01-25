package boj;

import java.io.*;
import java.util.*;

public class MainB_21939 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_21939.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		TreeSet<int[]> set = new TreeSet<int[]>((o1,o2) -> {
				return o2[1]!=o1[1] ? o2[1]-o1[1] : o2[0]-o1[0];
			}
		);
		HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] obj = new int[] {
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
					};
			
			set.add(obj);
			map.put(obj[0], obj);
		}
		
		int M = Integer.parseInt(br.readLine());
		String[] cmds = new String[M];
		for(int i=0; i<M; i++) {
			cmds[i] = br.readLine();
		}
		
		solve(set, map, cmds, sb);
		System.out.println(sb.toString());
	}
	
	public static void solve(TreeSet<int[]> set, HashMap<Integer, int[]> map, String[] cmds, StringBuilder sb) {
		int M = cmds.length;
		StringTokenizer st;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(cmds[i]);
			char cmd = st.nextToken().charAt(0);
			
			if(cmd == 'a') {
				int[] obj = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				set.add(obj);
				map.put(obj[0], obj);
				
			} else if(cmd == 'r') {
				int mode = (int)st.nextToken().charAt(0);
				
				if(mode == 49) {
					sb.append(set.first()[0]).append("\n");
				}
				else {
					sb.append(set.last()[0]).append("\n");
				}
			} else {
				int[] obj = map.get(Integer.parseInt(st.nextToken()));
				set.remove(obj);
				map.remove(obj[0]);
			}
		}
	}
}
