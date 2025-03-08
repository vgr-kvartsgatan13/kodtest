package se.kvartsgatan.vgr.domain.article;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import se.kvartsgatan.vgr.data.article.ArticleEntity;

class ArticleMapperTest {

	@Test
	void testFromEntity() {
		ArticleEntity articleEntity = new ArticleEntity("id", "name", new BigDecimal(4));
		Article article = ArticleMapper.fromEntity(articleEntity);

		assertNotNull(article);
		assertEquals("id", article.getId());
		assertEquals("name", article.getName());
		assertEquals(BigDecimal.valueOf(4), article.getPrice());
	}

}
