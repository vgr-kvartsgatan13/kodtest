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
    void testProcessRuleFive() {
        Article article = new Article("5", "Five", new BigDecimal(10));
        List<ArticleRecord> result = articleRules.processRules(article, 10);
        
        assertEquals(2, result.size());
        assertEquals("6", result.get(1).id());
        assertEquals(BigDecimal.ZERO, result.get(1).totalPrice());
    }

    @Test
    void testProcessRuleSix() {
        Article article = new Article("6", "Six", new BigDecimal(20));
        List<ArticleRecord> result = articleRules.processRules(article, 3);
        
        assertEquals(1, result.size());
        assertEquals(new BigDecimal(10).stripTrailingZeros(), result.get(0).singlePrice().stripTrailingZeros());
    }

    @Test
    void testProcessRuleSeven() {
        Article article = new Article("7", "Seven", new BigDecimal(50));
        List<ArticleRecord> result = articleRules.processRules(article, 1);
        
        assertEquals(2, result.size());
        assertEquals("5", result.get(1).id());
        assertEquals(BigDecimal.ZERO, result.get(1).totalPrice());
    }

    @Test
    void testProcessRuleNineLimit() {
        Article article = new Article("9", "Nine", new BigDecimal(30));
        List<ArticleRecord> result = articleRules.processRules(article, 4);
        
        assertEquals(1, result.size());
    }

    @Test
    void testProcessRuleNineExceedLimit() {
        Article article = new Article("9", "Nine", new BigDecimal(30));
        
        ArticleRuleException exception = assertThrows(ArticleRuleException.class, () -> {
            articleRules.processRules(article, 5);
        });
        
        assertEquals("It is not allowed to buy more than 4 pieces of article nr 9", exception.getMessage());
    }

    @Test
    void testProcessNoRule() {
        Article article = new Article("10", "Ten", new BigDecimal(15));
        List<ArticleRecord> result = articleRules.processRules(article, 2);
        
        assertEquals(1, result.size());
        assertEquals(new BigDecimal(30), result.get(0).totalPrice());
    }
}
