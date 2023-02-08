package ir.aniranmp.web.controller;


import ir.aniranmp.encryption.rsa.RsaCrackerThread;
import ir.aniranmp.encryption.rsa.RsaDecryptor;
import ir.aniranmp.encryption.rsa.RsaEncryptor;
import ir.aniranmp.encryption.rsa.RsaProperties;
import ir.aniranmp.web.model.transport.RequestObject;
import ir.aniranmp.web.model.transport.ResponseObject;
import ir.aniranmp.web.util.BeautyLog;
import ir.aniranmp.web.util.Route;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = {"http://192.168.1.5:4200", "http://127.0.0.1:4200", "*", "http://localhost:4200"})
public class RsaController {

    private RsaProperties rsaProperties = new RsaProperties();
    private String realData = "";
    private String encryptedDataBase64 = "";
    public static boolean cracked = false;

    @PostMapping(value = Route.API_CRYPTO_ENCRYPT)
    public ResponseObject encrypt(@RequestBody RequestObject requestObject) {
        String data = requestObject.getStrVal1();
        this.realData = data;
        int digits = requestObject.getIntVal1();
        BeautyLog.println(data);
//        if (digits != 2 && digits != 4 && digits != 6 && digits != 8 ) {
//            return new ResponseObject(false);
//        }

        RsaEncryptor rsaEncryptor = new RsaEncryptor();
        this.rsaProperties = RsaProperties.setRsaProperties(digits);
        String base64EncryptedData = rsaEncryptor.encrypt(this.rsaProperties, data);

        return new ResponseObject(true, base64EncryptedData);

    }

    @PostMapping(value = Route.API_CRYPTO_DECRYPT)
    public ResponseObject decrypt(@RequestBody RequestObject requestObject) {
        String data = requestObject.getStrVal1();
        this.encryptedDataBase64 = data;
        RsaDecryptor rsaDecryptor = new RsaDecryptor();
        String decryptedData = rsaDecryptor.decrypt(this.rsaProperties, data);
        return new ResponseObject(true, decryptedData);

    }

    @GetMapping(value = Route.API_CRYPTO_BRUTEFORCE)
    public void startBruteForceAttack() {
        BeautyLog.println("**************************************************************************************");
        BeautyLog.println("Real Data: " + this.realData);
        BeautyLog.println("Real d: " + rsaProperties.getD());
        BeautyLog.println("**************************************************************************************");
        byte[] encryptedDataByte = Base64.decodeBase64(encryptedDataBase64);

        int hundred = 100;
        int thousand = 1000;
        int hundredThousand = 100000;
        for (int i = 1, lowerbound = 1; i <= 1; i++) {
            int upperbound = i * hundredThousand;
            RsaCrackerThread rsaCrackerThread = new RsaCrackerThread(lowerbound, upperbound, realData, rsaProperties.getN(), encryptedDataByte);
            rsaCrackerThread.start();
            lowerbound = upperbound;
            if (cracked) break;
        }
        //Long Max 9,223,372,036,854,775,807


    }
}
