package se.kvartsgatan.vgr.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import se.kvartsgatan.vgr.controller.request.ArticleRequest;
import se.kvartsgatan.vgr.controller.request.CartRequest;
import se.kvartsgatan.vgr.domain.article.ArticleRepository;
import se.kvartsgatan.vgr.values.ArticleRecord;
import se.kvartsgatan.vgr.values.ReceiptRecord;

@Component
@AllArgsConstructor 
public class CartService {
	
	private final ArticleRules articleRules;
	private final ArticleRepository articleRepository;
	
	
	public ReceiptRecord placeOrder(CartRequest cart) {
		LocalDateTime time = LocalDateTime.now();
		String receiptId = UUID.randomUUID().toString();
		List<ArticleRecord> articles = extractArticles(cart.getArticles());
		BigDecimal totalamount = calculateTotalAmount(articles);
		return new ReceiptRecord(receiptId, time, totalamount, articles);
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
