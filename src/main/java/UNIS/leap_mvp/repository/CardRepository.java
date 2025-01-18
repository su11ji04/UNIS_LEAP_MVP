package UNIS.leap_mvp.repository;

import UNIS.leap_mvp.domain.Card;
import UNIS.leap_mvp.domain.Store;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Card save(Card card);
    List<Card> findAll();
    Optional<Card> findByName(String name);
    String bestCard(Store store);
}
