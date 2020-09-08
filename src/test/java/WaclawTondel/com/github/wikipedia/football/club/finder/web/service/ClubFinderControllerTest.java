package WaclawTondel.com.github.wikipedia.football.club.finder.web.service;

import WaclawTondel.com.github.wikipedia.football.club.finder.service.ClubFinderService;
import WaclawTondel.com.github.wikipedia.football.club.finder.service.article.ArticleNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClubFinderController.class)
class ClubFinderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClubFinderService clubFinderService;

    @Test
    void getClubWikiUrl() throws Exception {

        given(clubFinderService.getClubArticleUrl(any(String.class))).willReturn("OK");

        mvc.perform(get("/club/article?clubName=Liverpool")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    void getClubWikiUrlException() throws Exception {
        given(clubFinderService.getClubArticleUrl(any(String.class))).willThrow(new ArticleNotFoundException("error"));

        mvc.perform(get("/club/article?clubName=Liverpool")
                .contentType(MediaType.ALL))
                .andExpect(status().is(404));
    }
}