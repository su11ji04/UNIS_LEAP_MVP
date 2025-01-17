package UNIS.leap_mvp.service;

import UNIS.leap_mvp.domain.Store;
import UNIS.leap_mvp.repository.StoreRepository;

import java.util.List;

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
                    throw new IllegalStateException("이미 존재하는 가게입니다: " + existingStore.getType());
                });
    }

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store findByType(String type) {
        return storeRepository.findByType(type)
                .orElseThrow(() -> new IllegalStateException("해당 타입의 가게가 존재하지 않습니다: " + type));
    }

}
