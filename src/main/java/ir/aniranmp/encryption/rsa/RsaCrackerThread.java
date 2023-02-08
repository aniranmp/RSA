package ir.aniranmp.encryption.rsa;

import ir.aniranmp.web.controller.RsaController;
import ir.aniranmp.web.util.BeautyLog;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RsaCrackerThread extends Thread {

    private int upperbound = 0;
    private int lowerbound = 0;
    private String realData = "";
    private BigInteger N;
    private byte[] encryptedData;

    public RsaCrackerThread(int lowerbound, int upperbound, String realData, BigInteger N, byte[] encryptedData) {
        this.lowerbound = lowerbound;
        this.upperbound = upperbound;
        this.realData = realData;
        this.N = N;
        this.encryptedData = encryptedData;
    }

    public void run() {

        try {
            //            Displaying the thread that is running
            //            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
            long beforeJob = System.currentTimeMillis();
            while (true) {
                if (lowerbound >= upperbound) {
                    break;
                }
                byte[] decodedDataByte = (new BigInteger(this.encryptedData)).modPow(BigInteger.valueOf(lowerbound),
                        this.N).toByteArray();
//                System.out.println("Decrypting Bytes: " + RsaEncryptor.bytesToString(decodedDataByte));
//                System.out.println("Decrypted String: " + new String(decodedDataByte));
                if ((new String(decodedDataByte)).equals(realData)) {

//                    BeautyLog.println("Itadakimasu");
                    BeautyLog.println("d is equal to :" + Long.toString(lowerbound));
                    RsaController.cracked = true;
//                    return;
                }
                lowerbound++;
            }

            long afterJob = System.currentTimeMillis();
            long jobDelay = afterJob - beforeJob;

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

}
