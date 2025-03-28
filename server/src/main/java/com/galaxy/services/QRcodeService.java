package com.galaxy.services;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class QRcodeService {
    private static final Logger logger = LoggerFactory.getLogger(QRcodeService.class);

    @Autowired
    private MailService mailService;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    private static final String QR_CODE_FORMAT = "png";
    private static final String QR_CODE_DIR = "../../../../resources/static/uploads/QRcode";
    private static final String QR_CODE_FILENAME = "qrcode.png";

    /**
     * Tạo QR code và gửi qua email
     * 
     * @param email      Địa chỉ email người nhận
     * @param qrCodeData Dữ liệu để tạo QR code
     */
    public void generateAndSendQR(String email, String qrCodeData) {
        try {
            String qrCodePath = generateQRCode(qrCodeData);
            mailService.sendMail(email, qrCodePath);
            logger.info("QR code generated and sent successfully to {}", email);
        } catch (Exception e) {
            logger.error("Error generating or sending QR code to {}", email, e);
            throw new RuntimeException("Failed to generate or send QR code", e);
        }
    }

    /**
     * Tạo QR code và lưu vào file
     * 
     * @param data Dữ liệu để tạo QR code
     * @return Đường dẫn đến file QR code
     */
    private String generateQRCode(String data) throws IOException, WriterException {
        // Tạo thư mục QRcode nếu chưa tồn tại
        Path qrCodeDir = Paths.get(QR_CODE_DIR);
        if (!Files.exists(qrCodeDir)) {
            Files.createDirectories(qrCodeDir);
        }

        // Tạo QR code
        BitMatrix bitMatrix = new QRCodeWriter().encode(
                data,
                BarcodeFormat.QR_CODE,
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT);

        BufferedImage qrImage = createQRImage(bitMatrix);

        // Lưu QR code vào file
        String qrCodePath = Paths.get(QR_CODE_DIR, QR_CODE_FILENAME).toString();
        File qrCodeFile = new File(qrCodePath);
        ImageIO.write(qrImage, QR_CODE_FORMAT, qrCodeFile);

        return qrCodePath;
    }

    private BufferedImage createQRImage(BitMatrix bitMatrix) {
        BufferedImage qrImage = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = qrImage.createGraphics();

        // Vẽ nền trắng
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Vẽ QR code
        graphics.setColor(java.awt.Color.BLACK);
        for (int x = 0; x < DEFAULT_WIDTH; x++) {
            for (int y = 0; y < DEFAULT_HEIGHT; y++) {
                if (bitMatrix.get(x, y)) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }

        graphics.dispose();
        return qrImage;
    }
}
