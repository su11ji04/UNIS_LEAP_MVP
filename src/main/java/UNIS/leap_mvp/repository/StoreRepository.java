package UNIS.leap_mvp.repository;

import UNIS.leap_mvp.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    String save(Store store);
    List<Store> findAll();
    Optional<Store> findByType(String type); // 타입으로 검색
}
