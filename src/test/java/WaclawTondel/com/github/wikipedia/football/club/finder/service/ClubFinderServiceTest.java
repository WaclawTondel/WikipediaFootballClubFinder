package WaclawTondel.com.github.wikipedia.football.club.finder.service;

import WaclawTondel.com.github.wikipedia.football.club.finder.domain.article.Article;
import WaclawTondel.com.github.wikipedia.football.club.finder.domain.article.ArticleBuilder;
import WaclawTondel.com.github.wikipedia.football.club.finder.service.article.ArticleNotFoundException;
import WaclawTondel.com.github.wikipedia.football.club.finder.service.article.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ClubFinderServiceTest {

    @Mock
    ArticleService articleService;

    @InjectMocks
    ClubFinderService testService;

    @Test
    void getClubArticleUrl() throws JsonProcessingException, ArticleNotFoundException {
        Article article = new ArticleBuilder().basicArticle().build();
        String responseUrl = "url";
        given(articleService.getWikiArticles(article.getTitle()))
                .willReturn(Collections.singletonList(article));
        given(articleService.getArticleUrl(article.getTitle()))
                .willReturn(responseUrl);

        String url = testService.getClubArticleUrl("Liverpool");

        then(articleService).should()
                .getArticleUrl("Liverpool");
        assertEquals(url, responseUrl);
    }
}