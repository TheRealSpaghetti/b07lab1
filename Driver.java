import java.io.IOException;

public class Driver {
	
	public static void main(String [] args) throws IOException {
		
		Polynomial p = new Polynomial();
		
		//revert this change again
		//System.out.println(p.evaluate(3));
		
		double[] c11 = {6,-2,5};
		int [] c12 = {0,1,3};
		
		Polynomial p1 = new Polynomial(c11,c12);
		
		double[] c21 = {1,2,3,4};
		int [] c22 = {1,3,5,6};
		
		Polynomial p2 = new Polynomial(c21,c22);
		
		Polynomial s = p1.add(p2);
		
		Polynomial WingWong = p1.multiply(p2);
		
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		System.out.println("WingWong(0.1) = " + WingWong.evaluate(0.1));
		
		p2.saveToFile("bobbert");
		
		p1.multiply(p2);
		
		if(s.hasRoot(1))
		System.out.println("1 is a root of s");
		
		else
			System.out.println("1 is not a root of s");
		}
}