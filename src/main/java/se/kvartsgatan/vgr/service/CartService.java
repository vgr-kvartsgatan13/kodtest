package se.kvartsgatan.vgr.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import se.kvartsgatan.vgr.controller.request.ArticleRequest;
import se.kvartsgatan.vgr.controller.request.CartRequest;
import se.kvartsgatan.vgr.domain.article.ArticleRecord;
import se.kvartsgatan.vgr.domain.article.ArticleRepository;
import se.kvartsgatan.vgr.domain.article.ArticleRules;
import se.kvartsgatan.vgr.domain.receipt.ReceiptRecord;
import se.kvartsgatan.vgr.domain.receipt.ReceiptRepository;

@Component
@AllArgsConstructor 
public class CartService {
	
	private final ArticleRules articleRules;
	private final ArticleRepository articleRepository;
	private final ReceiptRepository receiptRepository;
	
	
	public ReceiptRecord placeOrder(CartRequest cart) {
		LocalDateTime time = LocalDateTime.now();
		String receiptId = UUID.randomUUID().toString();
		List<ArticleRecord> articles = extractArticles(cart.getArticles());
		BigDecimal totalamount = calculateTotalAmount(articles);
		ReceiptRecord receiptRecord = new ReceiptRecord(receiptId, time, totalamount, articles);
		receiptRepository.saveReceipt(receiptRecord);
		return receiptRecord;
	}


	private BigDecimal calculateTotalAmount(List<ArticleRecord> articles) {
		return articles.stream().map(ArticleRecord::totalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}


	private List<ArticleRecord> extractArticles(List<ArticleRequest> articles) {
		List<ArticleRecord> allArticleRecords = new ArrayList<ArticleRecord>();
		for (ArticleRequest article : articles) {
			allArticleRecords.addAll(articleRules.processRules(articleRepository.getArticle(article.getArticleId()), article.getCount()));
		}
		return allArticleRecords;
	}

}
