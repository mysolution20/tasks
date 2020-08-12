package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCard;
import com.crud.tasks.trello.client.TrelloClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }
/**    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println("Here we have trelloBoardDto: " + trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
            System.out.println();
            System.out.println("This board contains nested lists: < trelloList > ");
            System.out.println();
            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println( trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));

        });

        // 22.2 Zadanie: Ułożenie kodu - to review    //   StringUtils.isNotBlank  -->  import org.apache.commons.lang3.StringUtils;
        Objects.requireNonNull(trelloBoards).stream()
                .filter(e -> StringUtils.isNotBlank(e.getId()))              // -->   or general:  .filter(e-> e.getId() != null && !e.getId().isEmpty())
                .filter(e -> e.getName() != null && !e.getName().isEmpty())
                .filter(e -> e.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {
                    System.out.println();
                    System.out.println(" filtrowanie wyświetla tablice, które posiadają pola id i name ****");
                    System.out.println();
                    System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
                });
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}