import java.math.BigInteger;
public class CalculatingPQ {
	public static final BigInteger N = new BigInteger("93055764871951658717994982842224750919360332058622770448972390633167503942434834859115156134525283249730479574216493913637687180382413182937841976189443277033521320176436962665774500984274136318811630599180437977412143825581067321490484349172656915529028222515140394108141826132902723568035733476746443326033");
	public static final BigInteger ENCRYPT =  new BigInteger("79869123554483073832586094128467857694075923245143702904778365327145719759357130886144572628206161024986203252699840432986714275558989723007543119466327221663418691490871994939182586500224411679779832814533617524791925478679789154209625291619740628996000288407731936015156716705148973053889017027553180087819");
	public static final BigInteger DECRYPT = new BigInteger("4980057216466768969503217587905609457069569964056765779727791");
	public static final BigInteger K = new BigInteger("4113806113050768557762893638791766636654925649664997859936575");
	
	
	public static void main(String[] args) {

		phi(ENCRYPT, DECRYPT, K, N);
	      	// breakingRsaEncryption(P,Q,ENCRYPT);
		
	}
	
	public static void solvingForPQ(BigInteger phi, BigInteger n) {
		BigInteger b = n.subtract(phi).add(BigInteger.ONE);
		BigInteger c = n;
		
		
		BigInteger p = b.add(sqrt(b.pow(2).subtract(BigInteger.valueOf(4L).multiply(c)))).divide(BigInteger.TWO);
		
		
		BigInteger q = b.subtract(sqrt(b.pow(2).subtract(c.multiply(BigInteger.valueOf(4L))))).divide(BigInteger.TWO);
		if(checkPQ(p,q,N)) {
			if(p.compareTo(q) == -1) {
				System.out.println("Smallest Prime: " + p);
			}
			System.out.println("Smallest Prime: " + q);
		}
		
		
	}
	
	public static void phi(BigInteger e, BigInteger d, BigInteger k, BigInteger n) {
		solvingForPQ(e.multiply(d).subtract(BigInteger.ONE).divide(k), n);
	}
	
	public static BigInteger sqrt(BigInteger x) {
	    BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
	    BigInteger div2 = div;
	    // Loop until we hit the same value twice in a row, or wind
	    // up alternating.
	    for(;;) {
	        BigInteger y = div.add(x.divide(div)).shiftRight(1);
	        if (y.equals(div) || y.equals(div2))
	            return y;
	        div2 = div;
	        div = y;
	    }
	}
	
	public static boolean checkPQ(BigInteger p, BigInteger q, BigInteger n) {
		if(p.multiply(q).compareTo(n) == 0) { 
			return true;
		}
		return false;
	}
}

