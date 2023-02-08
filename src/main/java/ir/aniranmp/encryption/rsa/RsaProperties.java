package ir.aniranmp.encryption.rsa;

import ir.aniranmp.web.util.BeautyLog;
import lombok.Data;

import java.math.BigInteger;
import java.util.Random;

@Data
public class RsaProperties {

    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;

    public RsaProperties() {
    }

    public static RsaProperties setRsaProperties(int digits) {
        RsaProperties rsaProperties = new RsaProperties();
//        int e = 17;
        int e = 1000000033;
        int p = 0, q = 0;
        if (digits == 2) {
            p = 37;
            q = 41;
        } else if (digits == 4) {
            p = 1451;
            q = 1487;
        } else if (digits == 6) {
            p = 104287;
            q = 105023;
        } else if (digits == 8) {
            p = 22183151;
            q = 22183193;
        } else {
            Random r = new Random();

//            BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
//            BigInteger e2 = BigInteger.probablePrime(digits / 2, r);
//        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
//            e.add(BigInteger.ONE);
//        }
//        d = e.modInverse(phi);
            rsaProperties.setP(BigInteger.probablePrime(digits, r));
            rsaProperties.setQ(BigInteger.probablePrime(digits, r));
            rsaProperties.setN(rsaProperties.getP().multiply(rsaProperties.getQ()));
            rsaProperties.setE(BigInteger.probablePrime(digits / 2, r));
            rsaProperties.setD(rsaProperties.getE().modInverse(rsaProperties.getP().subtract(BigInteger.ONE).multiply(rsaProperties.getQ().subtract(BigInteger.ONE))));
            BeautyLog.println(rsaProperties.getN());
            return rsaProperties;
        }
        rsaProperties.setE(BigInteger.valueOf(e));
        rsaProperties.setP(BigInteger.valueOf(p));
        rsaProperties.setQ(BigInteger.valueOf(q));
        rsaProperties.setD(rsaProperties.getE().modInverse(rsaProperties.getP().subtract(BigInteger.ONE).multiply(rsaProperties.getQ().subtract(BigInteger.ONE))));
        rsaProperties.setN(rsaProperties.getP().multiply(rsaProperties.getQ()));
        BeautyLog.println(rsaProperties.getN());
        return rsaProperties;
    }

}
