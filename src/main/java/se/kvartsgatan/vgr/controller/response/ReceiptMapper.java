package se.kvartsgatan.vgr.controller.response;

import se.kvartsgatan.vgr.values.ArticleRecord;
import se.kvartsgatan.vgr.values.ReceiptRecord;

public class ReceiptMapper {
	
	public static ReceiptResonse fromRecord(ReceiptRecord record) {
		ReceiptResonse response = new ReceiptResonse(
				record.id(),
				record.time(),
				record.totalAmount(),
				record.articles().stream().map(article -> toArticleResponse(article)).toList());
		return response;
	}

	public static ArticleResponse toArticleResponse(ArticleRecord record) {
		return new ArticleResponse(record.id(),record.name(),record.count(),record.singlePrice(),record.totalPrice());
	}
}
