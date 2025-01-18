package UNIS.leap_mvp.repository;

import UNIS.leap_mvp.domain.Card;
import UNIS.leap_mvp.domain.Store;

import java.util.*;

public class CardServiceRepository implements CardRepository {
    private static Map<String, Card> cardList = new HashMap<>();
    private static long sequence = 0L;

    public void cardListClear() {
        cardList.clear();
    }

    @Override
    public Card save(Card card) {
        card.setId(++sequence);
        cardList.put(card.getName(), card);
        return card;
    }

    @Override
    public List<Card> findAll() {
        return new ArrayList<>(cardList.values());
    }

    @Override
    public Optional<Card> findByName(String name) {
        return Optional.ofNullable(cardList.get(name));
    }

    @Override
    public String bestCard(Store store) {
        if (store == null || store.getType() == null) {
            return "ERROR: Invalid Store or Store Type";
        }

        List<Card> cards = findAll();
        if (cards.isEmpty()) {
            return "ERROR: No Cards Available";
        }

        Card bestCard = cards.stream()
                .filter(card -> store.getType().equals(card.getName()))
                .findFirst()
                .orElse(null);

        if (bestCard == null) {
            return "ERROR: No Matching Card Found";
        }

        return bestCard.getName();
    }
}
