package UNIS.leap_mvp.service;

import UNIS.leap_mvp.domain.Card;
import UNIS.leap_mvp.domain.Store;
import UNIS.leap_mvp.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CardServiceTest {
    private CardRepository cardRepository;
    private CardService cardService;

    @BeforeEach
    void setUp() {
        cardRepository = Mockito.mock(CardRepository.class);
        cardService = new CardService(cardRepository);
    }

    @Test
    void saveCard() {
        Card card1 = new Card("HD", 50, List.of(0, 0, 0, 10));
        Card card2 = new Card("KB", 20, List.of(0, 35, 20, 0));
        Card card3 = new Card("DL", 10, List.of(10, 0, 0, 0));

        Long savedCardId1 = cardService.save(card1);
        Long savedCardId2 = cardService.save(card2);
        Long savedCardId3 = cardService.save(card3);

        System.out.println("Saved Card ID 1: " + savedCardId1);
        System.out.println("Saved Card ID 2: " + savedCardId2);
        System.out.println("Saved Card ID 3: " + savedCardId3);
    }

    @Test
    void findBestCard() {
        // given
        Store store = new Store();
        store.setType("마트"); // 매장 타입 설정

        Card card1 = new Card("HD", 50, List.of(10, 20, 15, 10));
        Card card2 = new Card("KB", 40, List.of(30, 40, 25, 35));
        List<Card> cards = List.of(card1, card2);

        // when
        Mockito.when(cardRepository.findAll()).thenReturn(cards);

        String bestCardName = cardService.BestCard(store);

        // then
        assertEquals("KB", bestCardName);
    }

    @Test
    void bestCardWithUnsupportedStoreType() {
        // given
        Store store = new Store();
        store.setType("미용"); // 지원하지 않는 타입

        Card card1 = new Card("HD", 50, List.of(10, 20, 15, 10));
        List<Card> cards = List.of(card1);

        // when
        Mockito.when(cardRepository.findAll()).thenReturn(cards);

        String bestCardName = cardService.BestCard(store);

        // then
        assertEquals("ERROR: Unsupported Store Type", bestCardName);
    }
}
