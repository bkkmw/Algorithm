package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01260 {
	static int[] stack;
	static int top = -1;
	static int[] queue;
	static int front, rear;
	
	public static void main(String[] args) throws Exception{
		int N, M, V;
		int[][] mat;
		
		System.setIn(new FileInputStream("input/boj/input_01260.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
		stack = new int[M];
		queue = new int[N];
		front = 0;
		rear = 0;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			mat[src-1][dst-1] = 1;
			mat[dst-1][src-1] = 1;
		}
		
		dfs(mat, sb, V-1);
		bfs(mat, sb, V-1);
		System.out.println(sb);
	}
	
	static void dfs(int[][] mat, StringBuilder sb, int V) {
		int N = mat.length;
		int[] check = new int[N];

		stack[++top] = V;
		int vertex = V;
		while(top != -1) {
			vertex = stack[top--];
			int j;
			if(check[vertex] == 0) {
				sb.append(vertex+1).append(" ");				
				check[vertex] = 1;
			}
			for(j=N-1; j>=0; j--) {
				if(mat[vertex][j] == 1 && check[j] == 0) {
					stack[++top] = j;
				}
			}
		}
		sb.append("\n");		
	}
	
	static void bfs(int[][] mat, StringBuilder sb, int V) {
		int N = mat.length;
		int[] check = new int[N];
		
		queue[rear++] = V;
		check[V] = 1;
		while(front != rear) {
			int vertex = queue[front++];
			sb.append(vertex+1).append(" ");
			for(int j=0; j<N; j++) {
				if(mat[vertex][j] == 1 && check[j] == 0) {
					queue[rear++] = j;
					check[j] = 1;
				}
			}
			
		}
	}
	
}
