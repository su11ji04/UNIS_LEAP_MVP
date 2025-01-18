package UNIS.leap_mvp.service;


import UNIS.leap_mvp.domain.Card;
import UNIS.leap_mvp.domain.Store;
import UNIS.leap_mvp.repository.CardRepository;
import UNIS.leap_mvp.repository.CardServiceRepository;
import UNIS.leap_mvp.repository.StoreRepository;
import UNIS.leap_mvp.repository.StoreServiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CardServiceTest {
    private CardService cardService;
    private CardServiceRepository cardRepository;

    @BeforeEach
    public void beforeEach() {
        cardRepository = new CardServiceRepository();
        cardService = new CardService(cardRepository);
    }

    @AfterEach
    public void afterEach() {
        cardRepository.cardListClear();
    }

    @Test
    void saveNewCard() {
        Card card = new Card("주유", 50, Arrays.asList(0, 0, 0, 10));

        Long savedCardId = cardService.save(card);
        Optional<Card> findCardOptional = cardService.findCardByName("주유");

        assertTrue(findCardOptional.isPresent());
        assertEquals(card, findCardOptional.get());
    }


    @Test
    void validateDuplicateCard() {
        Card card1 = new Card("주유", 50, Arrays.asList(0, 0, 0, 10));
        Card card2 = new Card("마트", 20, Arrays.asList(10, 0, 0, 0));
        Card card3 = new Card("주유", 50, Arrays.asList(0, 0, 0, 10));

        cardService.save(card1);
        cardService.save(card2);
        //cardService.save(card3);
    }

    @Test
    void findAllCards() {}

    @Test
    void findCardByName() {}

}
