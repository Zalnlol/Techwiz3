/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

/**
 *
 * @author jthie
 */
public interface QRCodeService {

    byte[] generateQRCode(String qrContent, int width, int height);

}
