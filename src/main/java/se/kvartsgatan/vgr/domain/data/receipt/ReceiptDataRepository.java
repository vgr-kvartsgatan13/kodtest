package se.kvartsgatan.vgr.domain.data.receipt;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ReceiptDataRepository {
	
	private Map<String, ReceiptEntitiy> receipts;

}
