package se.kvartsgatan.vgr.domain.receipt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReceiptRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(ReceiptRepository.class);
	
	public void saveReceipt(ReceiptRecord receipt) {
		logger.info("Save receipt wiht id: " + receipt.id());
	}

}
