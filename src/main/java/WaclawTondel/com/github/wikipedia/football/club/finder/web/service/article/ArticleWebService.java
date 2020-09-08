package WaclawTondel.com.github.wikipedia.football.club.finder.web.service.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArticleWebService {

    private final RestTemplate restTemplate;

    @Autowired
    public ArticleWebService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public String getArticles(String clubName) {
        String url = "https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch={clubName}&srlimit=10";

        return this.restTemplate.getForObject(url, String.class, clubName);
    }

    public String getArticleUrl(String articleTitle) {
        String url = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&generator=search&inprop=url&gsrsearch={articleTitle}&gsrlimit=1";

        return this.restTemplate.getForObject(url, String.class, articleTitle);
    }
}
