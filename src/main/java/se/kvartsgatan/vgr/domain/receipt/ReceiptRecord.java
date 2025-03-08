package se.kvartsgatan.vgr.domain.receipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import se.kvartsgatan.vgr.domain.article.ArticleRecord;

public record ReceiptRecord (String id, LocalDateTime time, BigDecimal totalAmount, List<ArticleRecord> articles) {}
