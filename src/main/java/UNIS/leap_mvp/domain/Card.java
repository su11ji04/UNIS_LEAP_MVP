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
