package boj;

import java.util.*;
import java.io.*;

public class MainB_07869 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_07869.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// x y z
		double[][] circle = new double[2][3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<3; j++) {
				circle[i][j] = Double.parseDouble(st.nextToken());
			}
		}
		
		double ans = solve(circle);
		System.out.println(String.format("%.3f", ans));
		br.close();
	}
	
	public static double solve(double[][] circle) {
		double ret = 0;
		double dist = get_dist(circle[0][0], circle[0][1], circle[1][0], circle[1][1]);
		
		if(dist >= (circle[0][2] + circle[1][2])) {
			ret = 0;
		}
		else if(dist+Math.min(circle[0][2], circle[1][2]) <= Math.max(circle[0][2], circle[1][2])) {
			double radius = Math.min(circle[0][2], circle[1][2]);
			ret = Math.PI * radius * radius;
		}
		else {
			double cos1 = (circle[0][2]*circle[0][2] + dist*dist - circle[1][2]*circle[1][2]) / (2*dist*circle[0][2]);
			double cos2 = (circle[1][2]*circle[1][2] + dist*dist - circle[0][2]*circle[0][2]) / (2*dist*circle[1][2]);
			
			ret += circle[0][2]*circle[0][2]*Math.acos(cos1);
			ret -= circle[0][2]*circle[0][2]*Math.sin(2*Math.acos(cos1))/2;
			ret += circle[1][2]*circle[1][2]*Math.acos(cos2);
			ret -= circle[1][2]*circle[1][2]*Math.sin(2*Math.acos(cos2))/2;
		}
		
		return ret;
	}
	
	public static double get_dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
}
