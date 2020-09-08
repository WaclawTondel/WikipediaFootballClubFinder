package WaclawTondel.com.github.wikipedia.football.club.finder.service.article;

import WaclawTondel.com.github.wikipedia.football.club.finder.domain.article.Article;
import WaclawTondel.com.github.wikipedia.football.club.finder.web.service.article.ArticleWebService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleWebService articleWebService;

    @InjectMocks
    ArticleService testService;

    @Test
    void getWikiArticles() throws JsonProcessingException {
        String clubName = "Barcelona";
        String wikiResponse = "{\"batchcomplete\":\"\",\"continue\":{\"sroffset\":2,\"continue\":\"-||\"},\"query\":{\"searchinfo\":{\"totalhits\":65443},\"search\":[{\"ns\":0,\"title\":\"Barcelona\",\"pageid\":4443,\"size\":152559,\"wordcount\":13868,\"snippet\":\"<span class=\\\"searchmatch\\\">Barcelona</span> (/\\u02ccb\\u0251\\u02d0rs\\u0259\\u02c8lo\\u028an\\u0259/ BAR-s\\u0259-LOH-n\\u0259, Catalan:\\u00a0[b\\u0259\\u027es\\u0259\\u02c8lon\\u0259], Spanish:\\u00a0[ba\\u027e\\u03b8e\\u02c8lona] or Spanish:\\u00a0[ba\\u027ese\\u02c8lona]) is a city on the coast of northeastern\",\"timestamp\":\"2020-09-08T00:22:57Z\"},{\"ns\":0,\"title\":\"FC Barcelona\",\"pageid\":68187,\"size\":155123,\"wordcount\":13626,\"snippet\":\"Futbol Club <span class=\\\"searchmatch\\\">Barcelona</span> (Catalan pronunciation:\\u00a0[fub\\u02c8b\\u0254l \\u02c8klub b\\u0259\\u027es\\u0259\\u02c8lon\\u0259] (listen)), commonly referred to as <span class=\\\"searchmatch\\\">Barcelona</span> and colloquially known as Bar\\u00e7a\",\"timestamp\":\"2020-09-08T02:06:56Z\"}]}}";
        given(articleWebService.getArticles(clubName))
                .willReturn(wikiResponse);

        List<Article> result = testService.getWikiArticles(clubName);

        then(articleWebService).should()
                .getArticles(clubName);
    assertEquals(result.size(),2);
    }

    @Test
    void getArticleUrl() {
        String clubName = "Lech Poznań";
        String wikiResponse = "{\"batchcomplete\":\"\",\"continue\":{\"gsroffset\":1,\"continue\":\"gsroffset||\"},\"query\":{\"pages\":{\"677622\":{\"pageid\":677622,\"ns\":0,\"title\":\"Lech Pozna\\u0144\",\"index\":1,\"contentmodel\":\"wikitext\",\"pagelanguage\":\"en\",\"pagelanguagehtmlcode\":\"en\",\"pagelanguagedir\":\"ltr\",\"touched\":\"2020-09-07T00:26:31Z\",\"lastrevid\":977010150,\"length\":64988,\"fullurl\":\"https://en.wikipedia.org/wiki/Lech_Pozna%C5%84\",\"editurl\":\"https://en.wikipedia.org/w/index.php?title=Lech_Pozna%C5%84&action=edit\",\"canonicalurl\":\"https://en.wikipedia.org/wiki/Lech_Pozna%C5%84\"}}}}";
        String expectedResult = "https://en.wikipedia.org/wiki/Lech_Pozna%C5%84";
        given(articleWebService.getArticleUrl(clubName))
                .willReturn(wikiResponse);

        String result = testService.getArticleUrl(clubName);

        then(articleWebService).should()
                .getArticleUrl(clubName);
        assertEquals(expectedResult, result);
    }
}