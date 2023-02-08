package ir.aniranmp.encryption.rsa;

import ir.aniranmp.web.model.transport.RequestObject;
import ir.aniranmp.web.model.transport.ResponseObject;
import ir.aniranmp.web.util.BeautyLog;
import ir.aniranmp.web.util.Route;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

public class RsaDecryptor {

    public String decrypt(RsaProperties rsaProperties, String data) {

        byte[] encryptedDataByte = Base64.decodeBase64(data);
        byte[] decodedDataByte = (new BigInteger(encryptedDataByte)).modPow(rsaProperties.getD(),
                rsaProperties.getN()).toByteArray();
        System.out.println("Decrypting Bytes: " + RsaEncryptor.bytesToString(decodedDataByte));
        System.out.println("Decrypted String: " + new String(decodedDataByte));
        return new String(decodedDataByte) +"";

    }

}
