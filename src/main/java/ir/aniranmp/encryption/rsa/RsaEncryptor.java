package ir.aniranmp.encryption.rsa;

import ir.aniranmp.web.model.transport.RequestObject;
import ir.aniranmp.web.model.transport.ResponseObject;
import ir.aniranmp.web.util.BeautyLog;
import ir.aniranmp.web.util.Route;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

import static org.junit.Assert.assertEquals;


public class RsaEncryptor {

    private RsaProperties rsaProperties = new RsaProperties();
    private String encryptedData = "";
    private byte[] encryptedDataBytes;

    public String encrypt(RsaProperties rsaProperties, String data) {

        byte[] dataBytes = data.getBytes();

//        BeautyLog.println(bytesToString(dataBytes));
//        BeautyLog.println(rsaProperties.getE());
//        BeautyLog.println(rsaProperties.getN());

        byte[] encryptedDataBytes = (new BigInteger(dataBytes)).modPow(rsaProperties.getE(), rsaProperties.getN()).toByteArray();

//        BeautyLog.println(bytesToString(encryptedDataBytes));

        String base64EncryptedData = Base64.encodeBase64String(encryptedDataBytes);

        return base64EncryptedData;
    }

    public static String bytesToString(byte[] encrypted) {
        String test = "";
        for (byte b : encrypted) {
            test += Byte.toString(b);
        }
        return test;
    }
//    @Test
//    public void rsaLib() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//        generator.initialize(2048);
//        KeyPair pair = generator.generateKeyPair();
//        PrivateKey privateKey = pair.getPrivate();
//        PublicKey publicKey = pair.getPublic();
//        String secretMessage = "Baeldung secret message";
//        Cipher encryptCipher = Cipher.getInstance("RSA");
//        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
//        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
////        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
//        String encodedMessage = Base64.encodeBase64String(encryptedMessageBytes);
//        Cipher decryptCipher = Cipher.getInstance("RSA");
//        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
//        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
//        BeautyLog.println(decryptedMessage);
//        assertEquals(secretMessage, decryptedMessage);
//    }

}
