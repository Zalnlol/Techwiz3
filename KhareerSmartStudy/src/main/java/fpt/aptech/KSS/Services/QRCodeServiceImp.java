/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class QRCodeServiceImp implements QRCodeService{

    @Override
    public byte[] generateQRCode(String qrContent, int width, int height) {
        try {
            QRCodeWriter qrCodeWrite = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWrite.encode(qrContent, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
            } 
        catch (WriterException | IOException ex) {
            Logger.getLogger(QRCodeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
