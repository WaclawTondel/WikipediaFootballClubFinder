package WaclawTondel.com.github.wikipedia.football.club.finder.service.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends Exception {

    public ArticleNotFoundException(String errorMessage) {
        super("Football club: " + errorMessage + " not found");
    }
}
