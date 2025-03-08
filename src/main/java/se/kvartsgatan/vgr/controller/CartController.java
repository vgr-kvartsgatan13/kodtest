package se.kvartsgatan.vgr.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import se.kvartsgatan.vgr.controller.request.CartRequest;
import se.kvartsgatan.vgr.controller.response.ReceiptMapper;
import se.kvartsgatan.vgr.controller.response.ReceiptResonse;
import se.kvartsgatan.vgr.domain.receipt.ReceiptRecord;
import se.kvartsgatan.vgr.service.CartService;

@RequestMapping(value = "/api/cart")
@RestController
public class CartController {
	
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping(
			value = "/",  
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ReceiptResonse> addPurchase(@RequestBody @Valid CartRequest cart){
		ReceiptRecord receipt = cartService.placeOrder(cart);
		return ResponseEntity.ok(ReceiptMapper.fromRecord(receipt));
		
	}

}
