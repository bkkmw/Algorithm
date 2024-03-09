package boj;

import java.io.*;
import java.util.*;

public class MainB_21275 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_21275.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String strA = st.nextToken();
		String strB = st.nextToken();
		
		String ans = solve(strA, strB);
		System.out.println(ans);
	}
	
	public static String solve(String strA, String strB) {
		// X A B
		List<long[]> ans = new LinkedList<long[]>();
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		
		for(int i=2; i<=36; i++) {
			long num = convert_to_decimal(strA, i);
			if(num == -1) continue;
			
			map.put(num, i);
		}
		
		for(int i=2; i<=36; i++) {
			long num = convert_to_decimal(strB, i);
			if(num == -1) continue;
			
			Integer A = map.get(num);
			if(A != null && A != i) {
				ans.add(new long[] {num, (long)A, (long)i});
			}
		}
		
		if(ans.size() == 1) {
			long[] xab = ans.get(0);
			return String.format("%d %d %d", xab[0], xab[1], xab[2]);
		}
		
		return ans.size() == 0 ? "Impossible" : "Multiple";
		
	}
	
	public static long convert_to_decimal(String str, int base) {
		long ret = 0;
		int len = str.length();
		
		for(int i=0; i<len; i++) {
			long num = char_to_num(str.charAt(i));
			if(num >= base || ret > Long.MAX_VALUE / base) return -1;
			ret *= base;
			ret += num;
		}
		
		return ret;
	}
	
	public static int char_to_num(char c) {
		if((int)c < 60) return c-48;
		else return c-97+10;
	}
}
