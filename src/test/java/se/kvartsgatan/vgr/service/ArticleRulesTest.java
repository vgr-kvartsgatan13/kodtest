package se.kvartsgatan.vgr.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kvartsgatan.vgr.domain.article.Article;
import se.kvartsgatan.vgr.values.ArticleRecord;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

class ArticleRulesTest {
	private ArticleRules articleRules;

	@BeforeEach
	void setUp() {
		articleRules = new ArticleRules();
	}

	@Test
	void testRuleFive_10fivesShouldGive1Six() {
		Article article = new Article("5", "Five", new BigDecimal(10));
		List<ArticleRecord> result = articleRules.processRules(article, 10);

		assertEquals(2, result.size());
		ArticleRecord article5 = result.stream().filter(art -> art.id().equals("5")).findFirst().get();
		assertEquals(10, article5.count());
		assertEquals(BigDecimal.valueOf(100), article5.totalPrice());

		ArticleRecord article6 = result.stream().filter(art -> art.id().equals("6")).findFirst().get();
		assertEquals("6", article6.id());
		assertEquals(1, article6.count());
		assertEquals(BigDecimal.ZERO, article6.totalPrice());
	}

	@Test
	void testRuleFive_15fivesShouldGive1Six() {
		Article article = new Article("5", "Five", new BigDecimal(10));
		List<ArticleRecord> result = articleRules.processRules(article, 15);

		assertEquals(2, result.size());
		ArticleRecord article5 = result.stream().filter(art -> art.id().equals("5")).findFirst().get();
		assertEquals(15, article5.count());
		assertEquals(BigDecimal.valueOf(150), article5.totalPrice());

		ArticleRecord article6 = result.stream().filter(art -> art.id().equals("6")).findFirst().get();
		assertEquals("6", article6.id());
		assertEquals(1, article6.count());
		assertEquals(BigDecimal.ZERO, article6.totalPrice());
	}

	@Test
	void testRuleFive_20fivesShouldGive2Six() {
		Article article = new Article("5", "Five", new BigDecimal(10));
		List<ArticleRecord> result = articleRules.processRules(article, 20);

		assertEquals(2, result.size());
		ArticleRecord article5 = result.stream().filter(art -> art.id().equals("5")).findFirst().get();
		assertEquals(20, article5.count());
		assertEquals(BigDecimal.valueOf(200), article5.totalPrice());

		ArticleRecord article6 = result.stream().filter(art -> art.id().equals("6")).findFirst().get();
		assertEquals("6", article6.id());
		assertEquals(2, article6.count());
		assertEquals(BigDecimal.ZERO, article6.totalPrice());
	}

	@Test
	void testRuleFive_9fivesShouldNotGiveAnyExtra() {
		Article article = new Article("5", "Five", new BigDecimal(10));
		List<ArticleRecord> result = articleRules.processRules(article, 9);

		assertEquals(1, result.size());
		assertEquals("5", result.get(0).id());
		assertEquals(BigDecimal.valueOf(90), result.get(0).totalPrice());
	}

	@Test
	void testRuleSix_shouldGet50pDiscount_whenBuy2to4() {
		Article article = new Article("6", "Six", new BigDecimal(22));
		List<ArticleRecord> result = articleRules.processRules(article, 2);

		assertEquals(1, result.size());
		assertEquals(new BigDecimal(11).stripTrailingZeros(), result.get(0).singlePrice().stripTrailingZeros());
		assertEquals(BigDecimal.valueOf(22).stripTrailingZeros(), result.get(0).totalPrice().stripTrailingZeros());

		result = articleRules.processRules(article, 4);

		assertEquals(1, result.size());
		assertEquals(new BigDecimal(11).stripTrailingZeros(), result.get(0).singlePrice().stripTrailingZeros());
		assertEquals(BigDecimal.valueOf(44).stripTrailingZeros(), result.get(0).totalPrice().stripTrailingZeros());
	}
	
	@Test
	void testRuleSix_shouldGet75pDiscount_whenBuyMoreThan4() {
		Article article = new Article("6", "Six", new BigDecimal(22));
		List<ArticleRecord> result = articleRules.processRules(article, 5);

		assertEquals(1, result.size());
		assertEquals(new BigDecimal(5.5).stripTrailingZeros(), result.get(0).singlePrice().stripTrailingZeros());
		assertEquals(BigDecimal.valueOf(27.5).stripTrailingZeros(), result.get(0).totalPrice().stripTrailingZeros());

	}
	
	@Test
	void testRuleSix_shouldGetNoDiscount_whenBuyLessThan2() {
		Article article = new Article("6", "Six", new BigDecimal(22));
		List<ArticleRecord> result = articleRules.processRules(article, 1);

		assertEquals(1, result.size());
		assertEquals(new BigDecimal(22).stripTrailingZeros(), result.get(0).singlePrice().stripTrailingZeros());
		assertEquals(BigDecimal.valueOf(22).stripTrailingZeros(), result.get(0).totalPrice().stripTrailingZeros());

	}

	@Test
	void testRuleSeven_shouldGet3FivesForFreeFor1Seven() {
		Article article = new Article("7", "Seven", new BigDecimal(70));
		List<ArticleRecord> result = articleRules.processRules(article, 1);

		assertEquals(2, result.size());
		ArticleRecord article7 = result.stream().filter(art -> art.id().equals("7")).findFirst().get();
		assertEquals(1, article7.count());
		assertEquals(new BigDecimal(70), article7.totalPrice());
		
		ArticleRecord article5 = result.stream().filter(art -> art.id().equals("5")).findFirst().get();
		assertEquals("5", article5.id());
		assertEquals(3, article5.count());
		assertEquals(BigDecimal.ZERO, article5.totalPrice());
	}
	
	@Test
	void testRuleSeven_shouldGet6FivesForFreeFor2Seven() {
		Article article = new Article("7", "Seven", new BigDecimal(70));
		List<ArticleRecord> result = articleRules.processRules(article, 2);

		assertEquals(2, result.size());
		ArticleRecord article7 = result.stream().filter(art -> art.id().equals("7")).findFirst().get();
		assertEquals(2, article7.count());
		assertEquals(new BigDecimal(140), article7.totalPrice());
		
		ArticleRecord article5 = result.stream().filter(art -> art.id().equals("5")).findFirst().get();
		assertEquals("5", article5.id());
		assertEquals(6, article5.count());
		assertEquals(BigDecimal.ZERO, article5.totalPrice());
	}

	@Test
	void testRuleNine_shoudBePossibleToBuy4Nines() {
		Article article = new Article("9", "Nine", new BigDecimal(80));
		List<ArticleRecord> result = articleRules.processRules(article, 4);

		assertEquals(1, result.size());
		assertEquals(4, result.get(0).count());
		assertEquals(new BigDecimal(320), result.get(0).totalPrice());
		assertEquals(new BigDecimal(80), result.get(0).singlePrice());
	}

	@Test
	void testRuleNine_shouldNotBePossibleToBuyMoreThan4Nines() {
		Article article = new Article("9", "Nine", new BigDecimal(80));

		ArticleRuleException exception = assertThrows(ArticleRuleException.class, () -> {
			articleRules.processRules(article, 5);
		});

		assertEquals("It is not allowed to buy more than 4 pieces of article nr 9", exception.getMessage());
	}

	@Test
	void testNoRule_noExtraArticlesAndNoDiscounts() {
		Article article = new Article("10", "Ten", new BigDecimal(89));
		List<ArticleRecord> result = articleRules.processRules(article, 2);

		assertEquals(1, result.size());
		assertEquals(new BigDecimal(89), result.get(0).singlePrice());
		assertEquals(new BigDecimal(178), result.get(0).totalPrice());
	}
	
}
