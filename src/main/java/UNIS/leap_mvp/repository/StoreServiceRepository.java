package UNIS.leap_mvp.repository;

import UNIS.leap_mvp.domain.Store;

import java.util.*;

public class StoreServiceRepository implements StoreRepository {
    private static Map<String, Store> storeList = new HashMap<>();

    public void storeListClear() {
        storeList.clear();
    }

    @Override
    public Store save(Store store) {
        storeList.put(store.getType(), store);
        return store;
    }

    @Override
    public List<Store> findAll() {
        return new ArrayList<>(storeList.values());
    }

    @Override
    public Optional<Store> findByType(String type) {
        return Optional.ofNullable(storeList.get(type));
    }
}
