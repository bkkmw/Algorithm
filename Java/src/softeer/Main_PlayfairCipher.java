package softeer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_PlayfairCipher {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/softeer/input_PlayfairCipher.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String text = br.readLine();
		String key = br.readLine();
		
		String res = solve(text, key);
		System.out.println(res);
		br.close();
	}
	
	static String solve(String text, String key) {
		String ret = "";
		char table[][] = new char[5][5];
		int check[] = new int[26];
		Arrays.fill(check, -1);
		check[9] = 404;
		
		convert_key(key, table, check);
		char divided[] = divide_text(text);
		ret = encrypt(divided, table, check);
		
		return ret;
	}
	
	static void convert_key(String key, char[][] table, int[] check) {
		int slen = key.length();
		int tidx = 0;
		
		for(int i=0; i<slen; i++) {
			char temp = key.charAt(i);
			if(check[temp-65] > -1) continue;
			else {
				table[tidx/5][tidx%5] = temp;
				check[temp-65] = tidx;
				tidx++;
			}			
		}
		
		int cidx = 0;
		while(tidx < 25) {
			for(int i=cidx; i<26; i++) {
				if(check[i] == -1) {
					cidx = i;
					check[i] = tidx;
					table[tidx/5][tidx%5] = (char)(i + 65);
					break;
				}
			}
			tidx ++;
		}
		
	}

	static char[] divide_text(String text) {
		int slen = text.length();
		char ret[] = new char[2 * slen];
		int ridx = 0;
		
		for(int i=0; i<slen; i++) {
			char cur = text.charAt(i);
			ret[ridx++] = cur;
			if(i == slen - 1) {
				ret[ridx++] = 'X';
				break;
			}
			
			char next = text.charAt(i+1);			
			if(cur == next) {
				ret[ridx++] = (cur == 'X')? 'Q' : 'X';
			}
			else {
				ret[ridx++] = next;
				i += 1;
			}
			
		}
		
		return Arrays.copyOf(ret, ridx);
	}
	
	static String encrypt(char[] msg, char[][] table, int[] check) {
		int slen = msg.length;
		char ret[] = new char[slen];
		
		for(int i=0; i<slen; i+=2) {
			char even = msg[i];
			char odd = msg[i+1];
			
			int even_row = check[even-65] / 5;
			int even_col = check[even-65] % 5;
			
			int odd_row = check[odd-65] / 5;
			int odd_col = check[odd-65] % 5;
			
			if(even_row == odd_row) {
				even = table[even_row][(even_col + 1) % 5];
				odd = table[odd_row][(odd_col + 1) % 5];
			}
			else if (even_col == odd_col) {
				even = table[(even_row + 1) % 5][even_col];
				odd = table[(odd_row + 1) % 5][odd_col];
			}
			else {
				even = table[even_row][odd_col];
				odd = table[odd_row][even_col];
			}
			ret[i] = even;
			ret[i+1] = odd;
		}
		
		return new String(ret);
	}
}