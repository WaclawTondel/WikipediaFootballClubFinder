package WaclawTondel.com.github.wikipedia.football.club.finder.domain.article;

import java.io.Serializable;

public class Article implements Serializable {

    private String title;
    private String snippet;

    private Article() {
    }

    Article(String title, String snippet) {
        this.title = title;
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }
}
