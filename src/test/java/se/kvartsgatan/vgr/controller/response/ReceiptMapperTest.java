package se.kvartsgatan.vgr.controller.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import se.kvartsgatan.vgr.values.ArticleRecord;
import se.kvartsgatan.vgr.values.ReceiptRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReceiptMapperTest {

    @Test
    public void testFromRecord() {
    	LocalDateTime timestamp = LocalDateTime.now();
        ArticleRecord article1 = new ArticleRecord("1", "Ettan", 2, new BigDecimal(10), new BigDecimal(20));
        ArticleRecord article2 = new ArticleRecord("2", "Tvåan", 1, new BigDecimal(20), new BigDecimal(20));
        ReceiptRecord receiptRecord = new ReceiptRecord("123", LocalDateTime.now(), new BigDecimal(40), List.of(article1, article2));

        ReceiptResonse response = ReceiptMapper.fromRecord(receiptRecord);

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals(receiptRecord.time(), response.getTime());
        assertEquals(receiptRecord.totalAmount(), response.getTotalAmount());
        assertEquals(2, response.getArticles().size());
        
        ArticleResponse firstArticle = response.getArticles().stream().filter(article -> article.getId().equals("1")).findFirst().get();
        assertEquals("1", firstArticle.getId());
        assertEquals("Ettan", firstArticle.getName());
        assertEquals(2, firstArticle.getCount());
        assertEquals(new BigDecimal(10), firstArticle.getSinglePrice());
        assertEquals(new BigDecimal(20), firstArticle.getTotalPrice());
        
        ArticleResponse secondArticle = response.getArticles().stream().filter(article -> article.getId().equals("2")).findFirst().get();
        assertEquals("2", secondArticle.getId());
        assertEquals("Tvåan", secondArticle.getName());
        assertEquals(1, secondArticle.getCount());
        assertEquals(new BigDecimal(20), secondArticle.getSinglePrice());
        assertEquals(new BigDecimal(20), secondArticle.getTotalPrice());
    }

    @Test
    public void testToArticleResponse() {
        ArticleRecord article = new ArticleRecord("3", "Trean", 3, new BigDecimal(30), new BigDecimal(90));

        ArticleResponse response = ReceiptMapper.toArticleResponse(article);

        assertNotNull(response);
        assertEquals("3", response.getId());
        assertEquals("Trean", response.getName());
        assertEquals(3, response.getCount());
        assertEquals(new BigDecimal(30), response.getSinglePrice());
        assertEquals(new BigDecimal(90), response.getTotalPrice());
    }
}
