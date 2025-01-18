package UNIS.leap_mvp.service;

import UNIS.leap_mvp.domain.Store;
import UNIS.leap_mvp.repository.StoreRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public String save(Store store) {
        validateDuplicateStore(store); // 중복 검사
        storeRepository.save(store);
        return store.getType();
    }

    private void validateDuplicateStore(Store store) {
        storeRepository.findByType(store.getType())
                .ifPresent(existingStore -> {
                    throw new IllegalStateException("이미 존재하는 타입의 가게 입니다");
                });
    }

    public List<Store> findAll() { return storeRepository.findAll(); }

    public Optional<Store> findByType(String type) { return storeRepository.findByType(type); }

}
/*
CREATE TABLE STORE (
    TYPE VARCHAR(255) NOT NULL
);
INSERT INTO STORE (TYPE) VALUES ('주유');
INSERT INTO STORE (TYPE) VALUES ('스벅');
INSERT INTO STORE (TYPE) VALUES ('마트');
INSERT INTO STORE (TYPE) VALUES ('영화');
* */