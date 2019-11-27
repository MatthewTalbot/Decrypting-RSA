import java.math.BigInteger;
import java.util.Random;
public class CalculatingPQ {
	public static final BigInteger N = new BigInteger("93055764871951658717994982842224750919360332058622770448972390633167503942434834859115156134525283249730479574216493913637687180382413182937841976189443277033521320176436962665774500984274136318811630599180437977412143825581067321490484349172656915529028222515140394108141826132902723568035733476746443326033");
	public static final BigInteger ENCRYPT =  new BigInteger("79869123554483073832586094128467857694075923245143702904778365327145719759357130886144572628206161024986203252699840432986714275558989723007543119466327221663418691490871994939182586500224411679779832814533617524791925478679789154209625291619740628996000288407731936015156716705148973053889017027553180087819");
	public static final BigInteger DECRYPT = new BigInteger("288313403400973178744649655802323935202188876800754335378600080052609895017399");
	public static final BigInteger K = new BigInteger("247457412985991341305459991694859180505046214010847623471775543864184584047826");
	
	
	public static void main(String[] args) {
		calculatingPQWithoutK(ENCRYPT, DECRYPT, N);
		// phi(ENCRYPT, DECRYPT, k  ,N);
	      	// breakingRsaEncryption(P,Q,ENCRYPT);
		
	}
	
	public static void solvingForPQ(BigInteger phi, BigInteger n) {
		BigInteger b = n.subtract(phi).add(BigInteger.ONE);
		BigInteger c = n;
		
		
		BigInteger p = b.add(sqrt(b.pow(2).subtract(BigInteger.valueOf(4L).multiply(c)))).divide(BigInteger.valueOf(2L));
		
		
		BigInteger q = b.subtract(sqrt(b.pow(2).subtract(c.multiply(BigInteger.valueOf(4L))))).divide(BigInteger.valueOf(2L));
		
		if(checkPQ(p,q,N)) {
			System.out.println("Smallest Prime: " + smallestPrime(p, q));
		}
		
		
	}
	
	public static BigInteger smallestPrime(BigInteger p, BigInteger q) {
		if(p.compareTo(q) == -1) { return p;}
		return q;
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
	
	public static BigInteger calculatingPQWithoutK(BigInteger e, BigInteger d, BigInteger n) {
		BigInteger k = e.multiply(d).subtract(BigInteger.ONE);
		if(isEven(k)) {
		    BigInteger r = k;
		    BigInteger t = BigInteger.ZERO;

		    do {
		        r = r.divide(BigInteger.valueOf(2L));
		        t = t.add(BigInteger.ONE);
		    } while (isEven(r));

		    // Step 3
		    Random random = new Random();
		    boolean success = false;
		    BigInteger y = null;
		    
		    step3loop: for (int i = 1; i <= 100; i++) {

		        // 3a
		        BigInteger g = getRandomBi(n, random);

		        // 3b
		        y = g.modPow(r, n);

		        // 3c
		        if (y.equals(BigInteger.ONE) || y.equals(n.subtract(BigInteger.ONE))) {
		            // 3g
		            continue step3loop;
		        }

		        // 3d
		        for (BigInteger j = BigInteger.ONE; j.compareTo(t) <= 0; j = j.add(BigInteger.ONE)) {
		            // 3d1
		            BigInteger x = y.modPow(BigInteger.valueOf(2L), n);

		            // 3d2
		            if (x.equals(BigInteger.ONE)) {
		                success = true;
		                break step3loop;
		            }

		            // 3d3
		            if (x.equals(n.subtract(BigInteger.ONE))) {
		                // 3g
		                continue step3loop;
		            }

		            // 3d4
		            y = x;
		        }

		        // 3e
		        BigInteger x = y.modPow(BigInteger.valueOf(2L), n);
		        if (x.equals(BigInteger.ONE)) {

		            success = true;
		            break step3loop;

		        }

		        // 3g
		        // (loop again)
		    }

		    if (success) {
		        // Step 5
		        BigInteger p = y.subtract(BigInteger.ONE).gcd(n);
		        BigInteger q = n.divide(p);
		        System.out.println("Smallest Prime: " + smallestPrime(p, q));
		        return null;
		    }
		}

		// Step 4
		throw new RuntimeException("Prime factors not found");
		}
	
	private static BigInteger getRandomBi(BigInteger n, Random rnd) {
	    // From http://stackoverflow.com/a/2290089
	    BigInteger r;
	    do {
	        r = new BigInteger(n.bitLength(), rnd);
	    } while (r.compareTo(n) >= 0);
	    return r;
	}
	
	
	public static boolean checkPQ(BigInteger p, BigInteger q, BigInteger n) {
		if(p.multiply(q).compareTo(n) == 0) { 
			return true;
		}
		return false;
	}
	
	public static boolean isEven(BigInteger num) {
		return num.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO);
	}
}

