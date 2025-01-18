package UNIS.leap_mvp.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int result;

    @ElementCollection
    private List<Integer> benefits;

    public Card() {}

    public Card(String name, int result, List<Integer> benefits) {
        this.name = name;
        this.result = result;
        this.benefits = benefits;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }

    public List<Integer> getBenefits() {
        return benefits;
    }
    public void setBenefits(List<Integer> benefits) {
        this.benefits = benefits;
    }
}
/*
* -- 메인 테이블
CREATE TABLE CARD (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 카드 고유 ID
    NAME VARCHAR(255) NOT NULL,             -- 카드 이름
    RESULT INT                              -- 결과 값
);

-- 컬렉션 테이블
CREATE TABLE CARD_BENEFITS (
    CARD_ID BIGINT NOT NULL,                -- CARD 테이블과의 외래키 관계
    BENEFIT INT NOT NULL,                   -- 혜택 값
    PRIMARY KEY (CARD_ID, BENEFIT),         -- 복합 기본키로 유니크 보장
    CONSTRAINT FK_CARD_BENEFITS FOREIGN KEY (CARD_ID) REFERENCES CARD(ID) ON DELETE CASCADE
);

* */