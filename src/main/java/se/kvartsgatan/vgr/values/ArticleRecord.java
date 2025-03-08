package se.kvartsgatan.vgr.values;

import java.math.BigDecimal;

public record ArticleRecord(String id, String name, Integer count, BigDecimal singlePrice, BigDecimal totalPrice) {

}
