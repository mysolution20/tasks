package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.TrelloBadges;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCard;
import jdk.nashorn.internal.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrelloConfig trelloConfig;

    public List<TrelloBoardDto> getTrelloBoards() {

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(getUri(), TrelloBoardDto[].class);

            return ofNullable(boardsResponse)
                    .map(Arrays::asList)                   /** --> .map(a -> Arrays.asList(a))  :lambda is much readable */
                    .orElse(Collections.emptyList());

//            return Arrays.asList(ofNullable(boardsResponse)
//                    .orElse(new TrelloBoardDto[0]));         // <--  Original from Kodilla

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }

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

    }

    private URI getUri() {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        return restTemplate.postForObject(getUriCard(trelloCardDto), null, CreatedTrelloCard.class);
    }

    private URI getUriCard(TrelloCardDto trelloCardDto) {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();
    }

}
