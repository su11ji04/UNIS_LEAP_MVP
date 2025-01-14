package UNIS.leap_mvp.domain;

import jakarta.persistence.Entity;

@Entity
public class Store {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


