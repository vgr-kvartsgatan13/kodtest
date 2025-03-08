package se.kvartsgatan.vgr.domain.article;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Article {
	
	private String id;
	private String name;
	private BigDecimal price;

}
