package se.kvartsgatan.vgr.domain.receipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.Data;
import se.kvartsgatan.vgr.domain.article.Article;

@Data
public class Receipt {
	
	private String id;
	private LocalDateTime time;
	private BigDecimal totalAmount;
	private Map<String, List<Article>> articles;

}
