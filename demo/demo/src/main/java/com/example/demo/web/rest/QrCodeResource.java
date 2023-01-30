package com.example.demo.web.rest;


import com.example.demo.config.QrEan13generatorConfig;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/barcodes")
public class QrCodeResource {

    @GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
//        String fileName = "QRCodeEAN13_" + LocalDateTime.now() +".png";
//        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename(fileName).build();
//        headers.setContentDisposition(contentDisposition);
//        MediaType mediaType = MediaType.parseMediaType("application/png;charset=utf-8");
//        headers.setContentType(mediaType);
//        return ResponseEntity.ok().headers(headers).body(QrEan13generatorConfig.generateEAN13BarcodeImage(barcode));
        return ResponseEntity.ok().body(QrEan13generatorConfig.generateEAN13BarcodeImage1(barcode)); //1234567890111
    }


    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";


    @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
    public void download(
            @PathVariable("codeText") String codeText)
            throws Exception {
        int edgesOfTheSquare = 300;
        QrEan13generatorConfig.generateQRCodeImage(codeText, edgesOfTheSquare, edgesOfTheSquare, QR_CODE_IMAGE_PATH);
    }

    @GetMapping(value = "/genrateQRCode/{codeText}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("codeText") String codeText)
            throws Exception {

        int edgesOfTheSquare = 300;
        byte[] pngData = QrEan13generatorConfig.getQRCodeImage(codeText, edgesOfTheSquare, edgesOfTheSquare);

        HttpHeaders headers = new HttpHeaders();
        String fileName = "QRCode_" + LocalDateTime.now() +".png";
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename(fileName).build();
        headers.setContentDisposition(contentDisposition);
        MediaType mediaType = MediaType.parseMediaType("application/png;charset=utf-8");
        headers.setContentType(mediaType);

//        return ResponseEntity.status(HttpStatus.OK).body(pngData);
        return ResponseEntity.ok().headers(headers).body(pngData);
    }
}