package UNIS.leap_mvp.service;

import UNIS.leap_mvp.domain.Card;
import UNIS.leap_mvp.repository.CardRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CardRecommendService {
    private final CardRepository cardRepository;

    public CardRecommendService(CardRepository cardRepository) {this.cardRepository = cardRepository;}


    public Long registerCard(Card card) {
        validateDuplicateCard(member); //중복 회원 검증
        cardRepository.save(card);
        return card.getId();
    }

    private void validateDuplicateCard(Card card) {
        cardRepository.findByName(card.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 카드입니다");
                });
    }

    public Card bestCardSelction(){
        CardRepository.
    }
}

//카드 3개 등록 //장소 4곳 선정 //TEST CASE에 등록 + 골라주는 SERVICE 개발