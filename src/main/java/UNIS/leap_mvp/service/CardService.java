package UNIS.leap_mvp.service;

import UNIS.leap_mvp.domain.Card;
import UNIS.leap_mvp.domain.Store;
import UNIS.leap_mvp.repository.CardRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Long save(Card card) {
        validateDuplicateCard(card);
        cardRepository.save(card);
        return card.getId();
    }

    private void validateDuplicateCard(Card card) {
        boolean isDuplicate = cardRepository.findByName(card.getName())
                .isPresent();

        if (isDuplicate) {
            throw new IllegalStateException("이미 존재하는 카드입니다: " + card.getName());
        }
    }

    public List<Card> findAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> findCardByName(String name) { return cardRepository.findByName(name);}
}
/*
ID, NAME, RESULT, BENE(마트, 스벅, 영화, 주유)
1, HD, 50,  0 0 0 10
2, KB, 20, 0 20 35 0
3, DL, 10, 10 0 0 0

INSERT INTO CARD (NAME, RESULT) VALUES ('HD', 50);
INSERT INTO CARD (NAME, RESULT) VALUES ('KB', 20);
INSERT INTO CARD (NAME, RESULT) VALUES ('DL', 10);


INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (1, 0);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (1, 0);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (1, 0);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (1, 10);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (2, 0);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (2, 20);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (2, 35);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (2, 0);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (3, 10);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (3, 0);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (3, 0);
INSERT INTO CARD_BENEFITS (CARD_ID, BENEFIT) VALUES (3, 0);
 */

