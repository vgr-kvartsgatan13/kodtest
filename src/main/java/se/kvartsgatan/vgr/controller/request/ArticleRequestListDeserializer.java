package se.kvartsgatan.vgr.controller.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ArticleRequestListDeserializer extends JsonDeserializer<List<ArticleRequest>> {
	@Override
	public List<ArticleRequest> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		ObjectCodec codec = p.getCodec();
		JsonNode node = codec.readTree(p);
		List<ArticleRequest> articleRequests = new ArrayList<>();
		for (JsonNode articleNode : node) {
			String articleId = articleNode.get("articleId").asText();
			int count = articleNode.get("count").asInt();
			articleRequests.add(new ArticleRequest(articleId, count));
		}
		return articleRequests;
	}
}
