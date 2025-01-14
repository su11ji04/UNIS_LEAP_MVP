package UNIS.leap_mvp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ArrayList<String> benefits = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList getBenefits() { return benefits; }

    public void setBenefits(ArrayList<String> benefits) {
        this.benefits = benefits;
    }

    public void setBenefits() {this.benefits=benefits;}


}