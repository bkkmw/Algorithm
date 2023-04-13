package boj;

public class SqrtTest {

	public static void main(String[] args) {
		Math.sqrt(100);
//		System.out.println(Long.MAX_VALUE);
		long d = 1000000;
		
		long d_sq = d * d;
		
		double d_sqrt = Math.sqrt(d_sq);
		int d_sqrt_i = (int) d_sqrt;
		
		System.out.printf("Long : %d %f %d\n", d_sq, d_sqrt, d_sqrt_i);
		
		double d_sq_d = d * d;
		double d_sqrt_d = Math.sqrt(d_sq_d);
		int d_sqrt_d_i = (int) d_sqrt_d;
		
		System.out.printf("Double : %f %f %d\n", d_sq_d, d_sqrt_d, d_sqrt_d_i);
		
		int k = 1;
		int limit = (int)(d/k);
        float d_sqr = (float)d * (float)d;
        float k_sqr = (float)k * (float)k;
        System.out.println(d_sqr);
        System.out.println(Math.sqrt(d_sqr));
	}
}
