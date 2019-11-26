import java.math.BigInteger;
// 800831348299529×1063023658301453 (2 distinct prime factors)
public class Encryption {
	//comment
	public static final BigInteger  P = new BigInteger("800831348299529");
	public static final BigInteger Q = new BigInteger("1063023658301453");
	public static final BigInteger ENCRYPT =  new BigInteger("290106185698556540920595819021");
	public static void main(String[] args) {

	      	breakingRsaEncryption(P,Q,ENCRYPT);
		
	} 
	
	public static void breakingRsaEncryption(BigInteger p, BigInteger q, BigInteger encrypt) {
		BigInteger one = new BigInteger("1");
		
		// phi = (p-1)(q-1)
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		System.out.println("phi: " + phi);	
		
		// ed = 1 mod phi
		BigInteger decrypt = encrypt.modInverse(phi);
		System.out.println("decrypt: " + decrypt);
		
		// N = p*q
		BigInteger N = p.multiply(q);
		System.out.println("N: " + N);
		
		// checking if decrypt is the right value
		System.out.println("isValid: " + checkValidityOfDecryptionValue(encrypt, decrypt, phi));
		
	}
	
	public static boolean checkValidityOfDecryptionValue(BigInteger encyrpt, BigInteger decrypt, BigInteger N) {
		if(encyrpt.multiply(decrypt).mod(N).equals(new BigInteger("1"))){
			return true;
		}
		return false;
	}
	
	/* 
	 * Attempt at creating a primeFactor algorithm. It takes an extremely long time to execute though
	 * 
	public static ArrayList<BigInteger> factorPrimeNumber(BigInteger n) {
		ArrayList<BigInteger> primeFactors = new ArrayList<BigInteger>();
		BigInteger three = new BigInteger("3");
		BigInteger two = new BigInteger("2");
        while (three.multiply(three).compareTo(n) <= 0)
        {
            if (n.mod(three).equals(BigInteger.ZERO))
            {
            	primeFactors.add(three);
                n = n.divide(three);
            }
            else
            {
            	three = three.add(two);
            }
        }
        primeFactors.add(n);
        return primeFactors;
	}
	*/
}

