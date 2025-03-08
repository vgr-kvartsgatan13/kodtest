package se.kvartsgatan.vgr.domain.article;

import org.springframework.stereotype.Repository;

import se.kvartsgatan.vgr.data.article.ArticleDataRepository;

@Repository
public class ArticleRepository {
	
	private ArticleDataRepository repositoryImpl;
	
	public ArticleRepository(ArticleDataRepository repositoryImpl) {
		this.repositoryImpl = repositoryImpl;
	}

	public Article getArticle(String id) {
		return ArticleMapper.fromEntity(repositoryImpl.getArticle(id));
	}
}
