package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_11286_Arr {
	static int last = 0;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_11286.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] heap = new int[N];
		for(int i=0; i<N; i++) {
			int temp = Integer.parseInt(br.readLine());
			if(temp == 0) {
				int prt = delete(heap);
//				System.out.println(prt);
				sb.append(prt).append("\n");
			}
			else {
				insert(heap, temp);
			}
		}
		System.out.println(sb);
		br.close();
	}
	
	static void insert(int[] heap, int key) {
		heap[++last] = key;
		int idx = last;
		while(idx > 1) {
			int parent = idx/2;
			int child = idx;
			if(Math.abs(heap[parent]) > Math.abs(heap[child])) {
				swap(heap, child, parent);
			}			
			else if(Math.abs(heap[parent]) == Math.abs(heap[child])) {
				if(heap[parent] > heap[child]) {
					swap(heap, child, parent);
				}					
			}
			idx = idx>>1;
		}
		
	}
	
	static int delete(int[] heap) {
		if(last == 0) return 0;
		int ret = heap[1];
		heap[1] = heap[last];
		heap[last--] = 0;
		int idx = 1;
		while(idx*2 <= last) {
			int parent = idx;
			int child = idx*2;
			if(child > last) break;
			if(child+1 <= last) {
				child = (heap[child] < heap[child+1]) ? child : child+1;
			}
			if(Math.abs(heap[parent]) > Math.abs(heap[child])) {
				swap(heap, child, parent);
				}							else if(Math.abs(heap[parent]) == Math.abs(heap[child])) {
				if(heap[parent] > heap[child]) {
					swap(heap, child, parent);
				}
			}
			idx = idx<<1;
		}
		return ret;
	}
	
	static void swap(int[] heap, int src, int dst) {
		int temp = heap[src];
		heap[src] = heap[dst];
		heap[dst] = temp;
	}
}
