package WaclawTondel.com.github.wikipedia.football.club.finder.domain.article;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleBuilder {
    private String title;
    private String snippet;

    public Article build() {
        return new Article(title, snippet);
    }

    public ArticleBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public ArticleBuilder withSnippet(String snippet){
        this.snippet = snippet;
        return this;
    }

    public ArticleBuilder basicArticle(){
        this.title = "Liverpool";
        this.snippet = "football club";
        return this;
    }

}