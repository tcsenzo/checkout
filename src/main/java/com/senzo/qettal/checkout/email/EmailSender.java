package com.senzo.qettal.checkout.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.senzo.qettal.checkout.history.PurchaseToHistoryConverter;
import com.senzo.qettal.checkout.history.PurchaseToHistoryDTO;
import com.senzo.qettal.checkout.purchase.Purchase;
import com.senzo.qettal.checkout.users.User;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private VelocityEngine engine;
	@Autowired
	private PurchaseToHistoryConverter converter;

	public void send(Purchase purchase) {
		PurchaseToHistoryDTO purchaseDTO = converter.convert(purchase);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				User owner = purchase.getOwner();
				MimeMessageHelper msg = new MimeMessageHelper(mimeMessage);
				msg.setFrom("qettal.senzo@gmail.com");
				msg.setTo(owner.getEmail());
				msg.setSubject("Qettal - Os seus ingressos já estão disponíveis!");
				Map<String, Object> model = new HashMap<>();
				model.put("purchase", purchaseDTO);
				model.put("user", owner);
				String body = VelocityEngineUtils.mergeTemplateIntoString(engine, "purchase.vm", "utf-8", model);
				msg.setText(body, true);
			}
		};

		sender.send(preparator);
	}
}
