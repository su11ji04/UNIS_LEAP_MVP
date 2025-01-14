package UNIS.leap_mvp.service;


import UNIS.leap_mvp.repository.CardRepository;
import jakarta.transaction.Transactional;

@Transactional
public class BestBenefitRecommend {
    private final CardRepository cardRepository;

    public BestBenefitRecommend(CardRepository cardRepository) {this.cardRepository = cardRepository;}
}
/*
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
 */
