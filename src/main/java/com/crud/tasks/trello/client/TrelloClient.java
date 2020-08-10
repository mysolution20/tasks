package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class TrelloClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String username;

    public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(getUri(), TrelloBoardDto[].class);

/**  'Original Kodilla has kept for educational purposes'

    if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
 */


/**   'Version with isPresent() method that need avoid as possible, kept for educational purposes'

        final boolean present = Optional.ofNullable(boardsResponse).isPresent();
        return  (present)?Arrays.asList(boardsResponse):new ArrayList<>();
 */


        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)        // --> .map(a -> Arrays.asList(a))  :lambda is much readable
                .orElse(Collections.emptyList());

    }

    private URI getUri() {
        final URI uri = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + username)
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
        return uri;
    }
}
