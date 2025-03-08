package se.kvartsgatan.vgr.domain.article;

import java.math.BigDecimal;

public record ArticleRecord(String id, String name, Integer count, BigDecimal singlePrice, BigDecimal totalPrice) {

}
