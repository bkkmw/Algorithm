package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution_1873_es {
     
    static int h, w;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("input/swea/input_1873.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int TC = Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=TC;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
             
            char[][] arr = new char[h][w];
            int row=0,col=0,d=0;
            for(int i=0;i<h;i++) {
                String s = br.readLine();
                for(int j=0;j<w;j++) {
                    arr[i][j] = s.charAt(j);
                    if (s.charAt(j) == '^' || s.charAt(j) == 'v'|| s.charAt(j) == '<'|| s.charAt(j) == '>') {
                        row = i;
                        col = j;
                        if(s.charAt(j) == '^') d = 0;
                        if(s.charAt(j) == 'v') d = 1;
                        if(s.charAt(j) == '<') d = 2;
                        if(s.charAt(j) == '>') d = 3;
                    }
                }
            }
             
            int n = Integer.parseInt(br.readLine());
            String userInput = br.readLine();
             
            //           u d l r 
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            for(int i=0;i<n;i++) {
                char now = userInput.charAt(i);
                if(now == 'S') {
                    int nx = row + dx[d];
                    int ny = col + dy[d];
                    while(move_possible(nx, ny)) {
                        if (arr[nx][ny] == '*') {
                            arr[nx][ny] = '.';
                            break;
                        } else if(arr[nx][ny] == '#') {
                            break;
                        }
                        nx += dx[d];
                        ny += dy[d];
                    }
                     
                } else if(now == 'U') {
                    d = 0;
                    int nx = row + dx[d];
                    int ny = col + dy[d];
                    if(move_possible(nx, ny) && arr[nx][ny] == '.') {
                        arr[row][col] = '.';
                        row = nx;
                        col = ny;
                        arr[row][col] = '^';
                    }
                     
                } else if(now == 'D') {
                    d = 1;
                    int nx = row + dx[d];
                    int ny = col + dy[d];
                    if(move_possible(nx, ny) && arr[nx][ny] == '.') {
                        arr[row][col] = '.';
                        row = nx;
                        col = ny;
                        arr[row][col] = 'v';
                    }
                     
                } else if(now == 'L') {
                    d = 2;
                    int nx = row + dx[d];
                    int ny = col + dy[d];
                    if(move_possible(nx, ny) && arr[nx][ny] == '.') {
                        arr[row][col] = '.';
                        row = nx;
                        col = ny;
                        arr[row][col] = '<';
                    }
                     
                } else if(now == 'R') {
                    d = 3;
                    int nx = row + dx[d];
                    int ny = col + dy[d];
                    if(move_possible(nx, ny) && arr[nx][ny] == '.') {
                        arr[row][col] = '.';
                        row = nx;
                        col = ny;
                        arr[row][col] = '>';
                    }
                }
            }
             
            sb.append("#"+tc+" ");
            for(int i=0;i<h;i++) {
                for(int j=0;j<w;j++) {
                    sb.append(arr[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
     
    public static boolean move_possible(int x,int y) {
        if(x<0 || y<0 || x>=h || y>=w) {
            return false;
        }
        return true;
    }
 
}