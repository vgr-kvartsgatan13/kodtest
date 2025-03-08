package se.kvartsgatan.vgr.data.article;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleEntity {
	
	private String id;
	private String name;
	private BigDecimal price;

}
