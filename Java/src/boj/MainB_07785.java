package boj;

import java.io.*;
import java.util.*;

public class MainB_07785 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_07785.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			if("leave".equals(temp[1])) {
				map.remove(temp[0]);
			}
			else {
				map.put(temp[0], 1);
			}
		}
		
		String[] current = get_name(map);
		Arrays.sort(current, Collections.reverseOrder());
		
		print_ans(sb, current);
		System.out.println(sb);
	}
	
	public static String[] get_name(Map<String, Integer> map) {
		LinkedList<String> current_empl = new LinkedList<String>();
		int idx = 0;
		
		Set<String> names = map.keySet();
		
		names.forEach(name -> {
			if(map.get(name) != null) current_empl.add(name);
		});
		
		return current_empl.toArray(new String[idx]);
	}
	
	public static void print_ans(StringBuilder sb, String[] arr) {
		int len = arr.length;
		for(int i=0; i<len; i++) {
			sb.append(arr[i]).append("\n");
		}
	}
}
