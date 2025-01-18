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

    // 카드 저장
    public Long save(Card card) {
        validateDuplicateCard(card); // 중복 카드 검증
        cardRepository.save(card);
        return card.getId();
    }

    // 중복 카드 검증
    private void validateDuplicateCard(Card card) {
        boolean isDuplicate = cardRepository.findByName(card.getName())
                .isPresent();

        if (isDuplicate) {
            throw new IllegalStateException("이미 존재하는 카드입니다: " + card.getName());
        }
    }

    // 전체 카드 조회
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public String BestCard(Store store) {
        List<Card> cards = cardRepository.findAll();

        // store type에 따른 index 정의
        int benefitIndex = getBenefitIndexForStoreType(store.getType());

        if (benefitIndex == -1) {
            return "ERROR: Unsupported Store Type";
        }

        // 혜택 비교: 최고 혜택을 가진 카드를 찾음
        Card bestCard = cards.stream()
                .max((c1, c2) -> Integer.compare(c1.getBenefits().get(benefitIndex), c2.getBenefits().get(benefitIndex)))
                .orElse(null);

        if (bestCard != null) {
            return bestCard.getName();
        }
        return "ERROR: No Cards Available";
    }

    // store type에 따른 benefits 배열 인덱스 매핑
    private int getBenefitIndexForStoreType(String storeType) {
        switch (storeType) {
            case "마트":
                return 0;
            case "스벅":
                return 1;
            case "영화":
                return 2;
            case "주유":
                return 3;
            default:
                return -1; // 지원하지 않는 Store type
        }
    }
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

