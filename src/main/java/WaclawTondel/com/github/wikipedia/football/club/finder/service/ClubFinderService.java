package WaclawTondel.com.github.wikipedia.football.club.finder.service;

import WaclawTondel.com.github.wikipedia.football.club.finder.domain.article.Article;
import WaclawTondel.com.github.wikipedia.football.club.finder.service.article.ArticleNotFoundException;
import WaclawTondel.com.github.wikipedia.football.club.finder.service.article.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ClubFinderService {
    private final static String FOOTBALL_CLUB = "football club";
    private ArticleService articleService;

    @Autowired
    public ClubFinderService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public String getClubArticleUrl(String clubName) throws JsonProcessingException, ArticleNotFoundException {
        List<Article> articles = articleService.getWikiArticles(clubName);

        Comparator<Article> byLength = (article1, article2) -> article1.getTitle().length() > article2.getTitle().length() ? -1 : 1;

        for (Article article : articles) {
            if (article.getTitle().equals(clubName)) {
                return articleService.getArticleUrl(article.getTitle());
            }
        }

        Article clubArticle = articles.stream()
                .filter(article -> article.getSnippet().contains(FOOTBALL_CLUB))
                .filter(article -> article.getTitle().contains(clubName))
                .max(byLength)
                .orElseThrow(() -> new ArticleNotFoundException(clubName));

        return articleService.getArticleUrl(clubArticle.getTitle());
    }
}