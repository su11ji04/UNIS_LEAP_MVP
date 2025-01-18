package UNIS.leap_mvp.repository;

import UNIS.leap_mvp.domain.*;
import jakarta.persistence.ElementCollection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardServiceRepositoryTest {
    CardServiceRepository repository = new CardServiceRepository();

    @AfterEach
    public void afterEach(){ repository.cardListClear(); }

    @Test
    public void save() {
        Card card = new Card("주유", 50, Arrays.asList(0, 0, 0, 10));
        repository.save(card);

        Optional<Card> optionalSavedCard = repository.findByName(card.getName());

        assertTrue(optionalSavedCard.isPresent());
        assertEquals(card, optionalSavedCard.get());
    }

    @Test
    public void findAll(){
        Card card1 = new Card("주유", 50, Arrays.asList(0, 0, 0, 10));
        repository.save(card1);
        Card card2 = new Card("마트", 10, Arrays.asList(10, 0, 0, 0));
        repository.save(card2);

        List<Card> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findByName() {
        Card card1 = new Card("주유", 50, Arrays.asList(0, 0, 0, 10));
        repository.save(card1);
        Card card2 = new Card("마트", 10, Arrays.asList(10, 0, 0, 0));
        repository.save(card2);

        Optional<Card> result = repository.findByName("마트");

        assertTrue(result.isPresent());
        assertEquals(card2, result.get());
    }


    @Test
    public void bestCard() {
        Card card1 = new Card("주유", 50, Arrays.asList(0, 0, 0, 10));
        repository.save(card1);
        Card card2 = new Card("마트", 10, Arrays.asList(10, 0, 0, 0));
        repository.save(card2);

        Store store = new Store("마트");
        if (store.getType() == null) {
            System.out.println("ERROR: Invalid Store Type");
        }

        List<Card> cards = repository.findAll();
        if (cards.isEmpty()) {
            System.out.println("ERROR: No Cards Available");
            return;
        }


        Card bestCard = cards.stream()
                .filter(card -> store.getType().equals(card.getName()))
                .findFirst()
                .orElse(null);


        if (bestCard == null) {
            System.out.println("ERROR: No Matching Card Found");
        } else {
            System.out.println(bestCard.getName());
        }
    }

}
