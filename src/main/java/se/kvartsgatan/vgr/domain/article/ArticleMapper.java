package se.kvartsgatan.vgr.domain.article;

import se.kvartsgatan.vgr.data.article.ArticleEntity;

public class ArticleMapper {
	
	public static Article fromEntity(ArticleEntity entity) {
		return Article.builder()
				.id(entity.getId())
				.name(entity.getName())
				.price(entity.getPrice())
				.build();
	}

}
