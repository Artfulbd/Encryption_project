package myone;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BIgMod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String s = "23421349236598346896138957618972356973459751265";
		BigInteger a= new BigInteger(s);
		System.out.println(" A big integer: "+a);
		BigInteger b= new BigInteger("234213434534");
		BigDecimal c= new  BigDecimal("23421343453.4");
		System.out.println(" Addition      : "+a.add(b));  /// a + b
		System.out.println(" Subtraction   : "+a.subtract(b));   // a * b
		System.out.println(" Multiplrcation: "+a.multiply(b));
		System.out.println(" Divition      : "+a.divide(b));     //   a / b
		System.out.println(" Remainder     : "+a.remainder(b));   // a % b
		System.out.println(" Remainder     : "+c.add(new BigDecimal(b)));
		System.out.println(" Remainder     : "+a.add(c.toBigInteger()));*/
		System.out.println("KEY:"+defeHelman(8,15,23,7));
		System.out.println("Elgamal M:"+elgamal(15,23,18,7));
		System.out.println("aPowMode: "+aPowMode(222,5,247));
		
	}
	static BigInteger aPowMode(int aa,int p, int m) {
		BigInteger a = new BigInteger(Integer.toString(aa));
		a = a.pow(p);
		a = a.mod(new BigInteger(Integer.toString(m)));
		return a;
	}
	
	static BigInteger defeHelman(int a, int b, long pp, long gg) {
		int ab = a*b;
		BigInteger g = new BigInteger(Long.toString(gg));
		BigInteger p = new BigInteger(Long.toString(pp));
		BigInteger key = g.pow(ab).mod(p);
		return key;
	}
	
	static BigInteger elgamal(int b,int p, long rr, long tt) {
		BigInteger r = new BigInteger(Long.toString(rr));
		BigInteger t = new BigInteger(Long.toString(tt));
		b = (-b) + (p-1);
		r = r.pow(b);
		t = t.multiply(r);
		t = t.mod(new BigInteger(Long.toString(p)));
		return t;
	}

}
