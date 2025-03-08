package se.kvartsgatan.vgr.values;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ReceiptRecord (String id, LocalDateTime time, BigDecimal totalAmount, List<ArticleRecord> articles) {}
