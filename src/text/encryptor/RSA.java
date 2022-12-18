/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.encryptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import static text.encryptor.utilities.Utilities.gcd;
import static text.encryptor.utilities.Utilities.getAlphabet;
import static text.encryptor.utilities.Utilities.getIndex;

/**
 *
 * @author maher
 */
public class RSA {
    
    public static String encrypt(String plainText,int p ,int q){
        int z = (p - 1) * (q - 1);
        int n = p * q;
        int e ; 
        for (e = 2; e < z; e++) {
            if (gcd(e, z) == 1) {
                break;
            }
        }
        String c="";
        for(int j = 0 ; j<plainText.length();j++){
            BigInteger N = BigInteger.valueOf(n);
            BigInteger C = BigDecimal.valueOf(getIndex(plainText.charAt(j))).toBigInteger();
            c += getAlphabet((C.pow(e).mod(N)).intValue());
        }
      return c;
    }
    
    public static String decrypt(String cipherText,int p ,int q){
        int   d = 0, e, i;
        int z = (p - 1) * (q - 1);
        int n = p * q;
        for (e = 2; e < z; e++) {
            if (gcd(e, z) == 1) {
                break;
            }
        }
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        String plainText = "";
        BigInteger N = BigInteger.valueOf(n);
        for(int j = 0 ; j<cipherText.length();j++){
               BigInteger C = BigDecimal.valueOf(getIndex(cipherText.charAt(j))).toBigInteger();

               BigInteger msgback = (C.pow(d)).mod(N).mod(BigInteger.valueOf(26));
               plainText += getAlphabet(msgback.intValue());
        }
        return plainText;
        
    }
}