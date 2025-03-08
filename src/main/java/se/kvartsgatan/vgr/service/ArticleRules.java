package se.kvartsgatan.vgr.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import se.kvartsgatan.vgr.domain.article.Article;
import se.kvartsgatan.vgr.values.ArticleRecord;

@Component
public class ArticleRules {
  
//    Regler:
//
//    	* 3 st artikel 5 ingår gratis varje gång man köper en artikel 7
//
//    	* köper man 10 st artikel 5 så får man en artikel 6 gratis
//
//    	* man får bara köpa 4 st artikel 9
//
//    	* artikel 6 har 50% rabatt om man köper 2 st - 4 st
//
//    	* artikel 6 har 75% rabatt om man köper 5 st -
//
//    	* artiklar som inte faller in under ovan regler räknas som artikelpris * antal
//
//    	* alla artiklar skall redovisas på kvittot
//	

	public List<ArticleRecord> processRules(Article article, int count) {
		return switch (article.getId()) {
		case "5" -> processRuleFive(article, count);
		case "6" -> processRuleSix(article, count);
		case "7" -> processRuleSeven(article, count);
		case "9" -> processRuleNine(article, count);
		default -> processNoRule(article, count);
		};
	}

	private List<ArticleRecord> processRuleFive(Article article, int count) {
		List<ArticleRecord> records = new ArrayList<ArticleRecord>();
		records.add(new ArticleRecord(article.getId(), article.getName(), count, article.getPrice(),
				article.getPrice().multiply(new BigDecimal(count))));
		int nbrOfFreeSixes = count / 10;
		if (nbrOfFreeSixes > 0) {
			records.add(new ArticleRecord("6", "Sexan", nbrOfFreeSixes, BigDecimal.ZERO, BigDecimal.ZERO));
		}
		return records;
	}

	private List<ArticleRecord> processRuleSix(Article article, int count) {
		BigDecimal discount = count < 2 ? new BigDecimal(1) : count < 5 ? new BigDecimal(0.5) : new BigDecimal(0.25);
		BigDecimal priceEach = discount.multiply(article.getPrice());
		return List.of(
				new ArticleRecord("6", article.getName(), count, priceEach, priceEach.multiply(new BigDecimal(count))));
	}

	private List<ArticleRecord> processRuleSeven(Article article, int count) {
		List<ArticleRecord> records = new ArrayList<ArticleRecord>();
		records.add(new ArticleRecord(article.getId(), article.getName(), count, article.getPrice(),
				article.getPrice().multiply(new BigDecimal(count))));
		int numberOfFives = count * 3;
		records.add(new ArticleRecord("5", "Femman", numberOfFives, BigDecimal.ZERO, BigDecimal.ZERO));
		return records;
	}

	private List<ArticleRecord> processRuleNine(Article article, int count) {
		if (count > 4) {
			throw new ArticleRuleException("It is not allowed to buy more than 4 pieces of article nr 9");
		}
		return List.of(new ArticleRecord(article.getId(), article.getName(), count, article.getPrice(),
				article.getPrice().multiply(new BigDecimal(count))));
	}

	private List<ArticleRecord> processNoRule(Article article, int count) {
		return List.of(new ArticleRecord(article.getId(), article.getName(), count, article.getPrice(),
				article.getPrice().multiply(new BigDecimal(count))));
	}

}
