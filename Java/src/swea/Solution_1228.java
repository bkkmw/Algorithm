package swea;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_1228
{
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_1228.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		String line = null;
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			int[] arr = new int[10];
			int[] origin = new int[10];
			
			for(int i=0; i<10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int C = Integer.parseInt(br.readLine());
			line = br.readLine();
			st = new StringTokenizer(line, "I");
			for(int i=0; i<C; i++) {
				String cmd = st.nextToken();	
				StringTokenizer st_c = new StringTokenizer(cmd, " ");
				int src = Integer.parseInt(st_c.nextToken());
				int size = Integer.parseInt(st_c.nextToken());
				for(int j=0; j<10; j++) {
					origin[j] = arr[j];
				}
				for(int j=0; j<size; j++) {
					int temp = Integer.parseInt(st_c.nextToken());
					if(src + j <10) arr[src+j] = temp; 
				}
				for(int j=0; j+src+size<10; j++) {
					arr[j+src+size] = origin[j+src];
				}
								
			}
			sb.append("#").append(test_case).append(" ");
			print(sb, arr);

		}
		System.out.println(sb.toString());
		br.close();
	}
	static void print(StringBuilder sb, int[] arr) {
		for(int i=0; i<10; i++) {
			sb.append(arr[i]).append(" ");
		}
		sb.append("\n");
	}

	
}


