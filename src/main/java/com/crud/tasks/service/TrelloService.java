package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCard;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {
    private static final String SUBJECT = "Task New Trello card";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    // moduł 23.4
    public CreatedTrelloCard createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(              //  <-- import static java.util.Optional.ofNullable
                card -> {                           //  <-- funkcja .ifPresent('lambda'), sprawdza czy karta istnieje
                    emailService.send(
                            new Mail(
                                    adminConfig.getAdminMail(),
                                    SUBJECT,
                                    "New card: " + card.getName() + "has been created on your Trello board",
                                    "")
                    );
                }
        );
        return newCard;
    }
}
