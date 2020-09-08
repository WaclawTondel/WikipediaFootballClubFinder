package WaclawTondel.com.github.wikipedia.football.club.finder.web.service;


import WaclawTondel.com.github.wikipedia.football.club.finder.service.ClubFinderService;
import WaclawTondel.com.github.wikipedia.football.club.finder.service.article.ArticleNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubFinderController {

    private ClubFinderService clubFinderService;

    @Autowired
    public ClubFinderController(ClubFinderService clubFinderService) {
        this.clubFinderService = clubFinderService;
    }

    @GetMapping(value = "/club/article")
    public String getClubWikiUrl(@RequestParam("clubName") String clubName) throws JsonProcessingException, ArticleNotFoundException {

        return clubFinderService.getClubArticleUrl(clubName);
    }
}
