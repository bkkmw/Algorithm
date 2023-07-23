package swea;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution_1873
{
	static char map[][] = new char[20][20];
	static char cmd[] = new char[100];
	static int ypos, xpos;
	static int[][] dir_yx = {
			{-1, 0, '^'}, {0, 1, '>'}, {1, 0, 'v'}, {0, -1, '<'}
	};
	static int H,W;
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_1873.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T;
		T=Integer.parseInt(in.readLine());
		int N;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			for(int i=0; i<H; i++) {
				String line = in.readLine();
				for(int j=0; j<W; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == '^' || map[i][j] == '>' || map[i][j] == 'v' || map[i][j] == '<') {
						ypos = i;	xpos = j;
					}
				}
			}
			N = Integer.parseInt(in.readLine());
			String line = in.readLine();
			for(int i=0; i<N; i++) {
				cmd[i] = line.charAt(i);
			}
			
			run(N);
			
			sb.append(String.format("#%d ", test_case));
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
			
		}
		System.out.println(sb.toString());
	}

	static void run(int N) {
		for(int c=0; c<N; c++) {
			if(cmd[c] == 'S') {
				shoot();
			}
			else {
				move(cmd[c]);
			}
		}
	}
	
	static void shoot() {
		int dir;
		if(map[ypos][xpos] == '^') dir = 0;
		else if(map[ypos][xpos] == '>') dir = 1;
		else if(map[ypos][xpos] == 'v') dir = 2;
		else dir = 3;
		int y = ypos;
		int x = xpos;
		
		while(true) {
			int ny = y + dir_yx[dir][0];
			int nx = x + dir_yx[dir][1];
			
			if(ny<0 || ny>H-1 || nx<0 || nx>W-1) break;
			
			if(map[ny][nx] == '*') {
				map[ny][nx] = '.';
				break;
			}
			else if(map[ny][nx] == '#') break;
			else {
				y = ny;		x = nx;
			}
		}
		
	}
	
	static void move(char dirC) {
		int dir;
		if(dirC == 'U') dir = 0;
		else if(dirC == 'R') dir = 1;
		else if(dirC == 'D') dir = 2;
		else dir = 3;
		
		int ny = ypos + dir_yx[dir][0];
		int nx = xpos + dir_yx[dir][1];
		if(check(ny, nx)) {
			map[ypos][xpos] = '.';
			ypos = ny;
			xpos = nx;
		}
		map[ypos][xpos] = (char)dir_yx[dir][2];
		
	}
	
	static boolean check(int y, int x) {
		if(y<0 || y>(H-1) || x<0 || x>(W-1)) return false;
		if(map[y][x] == '.') return true;
		else return false;
	}
	
}