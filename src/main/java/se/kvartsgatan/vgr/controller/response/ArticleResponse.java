package se.kvartsgatan.vgr.controller.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
	
	private String id;
	private String name;
	private Integer count;
	private BigDecimal singlePrice;
	private BigDecimal totalPrice;
	
}
