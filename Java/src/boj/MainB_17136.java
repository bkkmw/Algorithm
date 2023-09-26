package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainB_17136 {
	
	public static final int N = 10;
	public static final int[] areas = new int[] {25, 16, 9, 4, 1};
	public static final int[] indexes = new int[] {6*6*6*6, 6*6*6, 6*6, 6, 1};
	public static final int iter = 6*6*6*6*6;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17136.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[N][N];
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cnt += map[i][j];
			}
		}
		
		int ans = solve(map, cnt);
		System.out.println(ans);
	}
	
	public static int solve(int[][] map, int cnt) {
		if(cnt == 0)
			return 0;
		else if(cnt == N*N)
			return 4;
		
		List<int[]> seqs = get_seqs(cnt);
		int size = seqs.size();
		
		int ret = -1;
		for(int i=0; i<size; i++) {
			int[] seq = seqs.get(i);
			if(validate(seq, 0, map))
				return seq[5];
		}
		
		return ret;
	}
	
	public static boolean validate(int[] sqs, int idx, int[][] map) {
		if(idx == sqs[5])
			return true;
		
		int size = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<sqs[i]; j++)
				size++;
			if(size > idx) {
				size = 5-i;
				break;
			}
		}
		
		for(int i=0; i<N-size+1; i++) {
			for(int j=0; j<N-size+1; j++) {
				if(check_square(map, i, j, size)) {
					modify_map(map, i, j, size, 0);
					boolean ret = validate(sqs, idx+1, map);
					modify_map(map, i, j, size, 1);				
					if(ret) return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean check_square(int[][] map, int i, int j, int size) {
		boolean ret = true;
		for(int y=i; y<i+size; y++)
			for(int x=j; x<j+size; x++) 
				if(map[y][x] == 0)
					return false;
		
		return ret;
	}
	
	public static void modify_map(int[][] map, int i, int j, int size, int val) {
		for(int y=i; y<i+size; y++)
			for(int x=j; x<j+size; x++)
				map[y][x] = val;
	}
	

	// simple implementation
	public static List<int[]> get_seqs(int cnt) {
		List<int[]> ret = new ArrayList<int[]>();
		int idx = 0;
		
		while(idx < iter) {
			int[] seq = generate_seq(idx);
			if(calc_cnt(seq, cnt) == 1)
				ret.add(seq);
			idx++;
		}	
		
		ret.sort((int[] o1, int[] o2) -> o1[5] - o2[5]);
		return ret;
	}
	
	public static int[] generate_seq(int idx) {
		int[] ret = new int[6];
		for(int i=0; i<5; i++) {
			ret[i] = idx / indexes[i];
			idx %= indexes[i];
			ret[5] += ret[i];
		}
		
		return ret;
	}
	
	public static int calc_cnt(int[] seq, int cnt) {
		int sum = 0;
		for(int i=0; i<5; i++) {
			sum += areas[i]*seq[i];
		}
		return (cnt == sum) ? 1 : 0;
	}
	
}
