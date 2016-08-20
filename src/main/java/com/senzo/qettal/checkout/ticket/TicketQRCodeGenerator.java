package com.senzo.qettal.checkout.ticket;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.codec.binary.Hex.encodeHexString;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.senzo.qettal.checkout.purchase.PurchaseItem;

@Component
public class TicketQRCodeGenerator {

	private static final String SALT = "amskf(*901%6";

	@Value("${url.ticket}")
	private String ticketUrlBase;
	
	public TicketQRCode generate(PurchaseItem item){
		int size = 250;
		try {
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hintMap.put(EncodeHintType.MARGIN, 1);
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			String hash = hash(item);
			BitMatrix byteMatrix = qrCodeWriter.encode(ticketUrlBase+"/"+hash, BarcodeFormat.QR_CODE, size, size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "png", os);
			return new TicketQRCode(os.toByteArray(), item, hash);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String hash(PurchaseItem item) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String key = item.getPurchaseUniqueId() + item.getId() + currentTimeMillis() + SALT;
		MessageDigest md = DigestUtils.getSha256Digest();
		md.update(key.getBytes("UTF-8"));
		String hashedTicketName = encodeHexString(md.digest());
		return hashedTicketName;
	}
	

}
