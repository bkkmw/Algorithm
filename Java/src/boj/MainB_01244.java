package boj;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainB_01244 {

	static int[] arr = new int[101];
	static int SW;
	
	static void flip(int gen, int num) {
		int cnt = 0;
		if(gen == 1) {
			for(int i=1; i*num <= SW; i++) {
				arr[i*num] = arr[i*num] ^ 1;
			}
		}
		else if(gen == 2) {
			cnt = cnt_sym(num);
			for(int i=1; i<=cnt; i++) {
				arr[num+i] = arr[num+i] ^1;
				arr[num-i] = arr[num-i] ^1;
			}
			arr[num] = arr[num]^1;
		}
	}
	
	static int cnt_sym(int num) {
		int ret = 0;
		while(true) {
			if(num-ret>1 && num+ret<SW) {
				if(arr[num-ret-1] == arr[num+ret+1]) {
					ret ++;
				}
				else break;
			}
			else break;
		}
		return ret;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream(new File("input/boj/input_01244.txt")));
		Scanner sc = new Scanner(System.in);
		
		SW = sc.nextInt();
		for(int i=1; i<=SW; i++) {
			arr[i] = sc.nextInt();
		}
		arr[0] = Integer.MAX_VALUE;
		
		int ST = sc.nextInt();
		for(int i=0; i<ST; i++) {
			int gender = sc.nextInt();
			int sw_num = sc.nextInt();
			flip(gender, sw_num);
		}
		
		for(int i=1; i<=SW; i++) {
			System.out.printf("%d ",arr[i]);
			if(i%20 == 0) System.out.println();
		}
		
		sc.close();
	}
}
