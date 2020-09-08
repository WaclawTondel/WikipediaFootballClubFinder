package WaclawTondel.com.github.wikipedia.football.club.finder.service.article;

import WaclawTondel.com.github.wikipedia.football.club.finder.domain.article.Article;
import WaclawTondel.com.github.wikipedia.football.club.finder.web.service.article.ArticleWebService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ArticleService {

    private ArticleWebService articleWebService;

    @Autowired
    public ArticleService(ArticleWebService articleWebService) {
        this.articleWebService = articleWebService;
    }

    public List<Article> getWikiArticles(String clubName) throws JsonProcessingException {
        String response = this.articleWebService.getArticles(clubName).split("\"search\":")[1];

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return Arrays.asList(mapper.readValue(response, Article[].class));
    }

    public String getArticleUrl(String articleTitle) {
        String response = articleWebService.getArticleUrl(articleTitle);

        JSONObject jsonResponse = new JSONObject("{" + response.split("\"ns\":0,")[1]);

        return jsonResponse.getString("fullurl");
    }
}
