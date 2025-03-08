package se.kvartsgatan.vgr.data.article;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ArticleDataRepository {
	
	private Map<String, ArticleEntity> articles;
	
	{
		articles = new HashMap<String, ArticleEntity>();
		articles.put("1", new ArticleEntity("1", "Ettan", new BigDecimal(10)));
		articles.put("2", new ArticleEntity("2", "Tvåan", new BigDecimal(20)));
		articles.put("3", new ArticleEntity("3", "Trean", new BigDecimal(30)));
		articles.put("4", new ArticleEntity("4", "Fyran", new BigDecimal(44)));
		articles.put("5", new ArticleEntity("5", "Femman", new BigDecimal(16)));
		articles.put("6", new ArticleEntity("6", "Sexan", new BigDecimal(22)));
		articles.put("7", new ArticleEntity("7", "Sjuan", new BigDecimal(70)));
		articles.put("8", new ArticleEntity("8", "Åttan", new BigDecimal(89)));
		articles.put("9", new ArticleEntity("9", "Nian", new BigDecimal(80)));
	}
	
	public ArticleEntity getArticle(String id) {
		return articles.get(id);
	}

}
