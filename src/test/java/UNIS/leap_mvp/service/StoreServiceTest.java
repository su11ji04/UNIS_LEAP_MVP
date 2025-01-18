package UNIS.leap_mvp.service;

import UNIS.leap_mvp.domain.Store;
import UNIS.leap_mvp.repository.StoreServiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StoreServiceTest {
    private StoreService storeService;
    private StoreServiceRepository storeRepository;

    @BeforeEach
    public void beforeEach() {
        storeRepository = new StoreServiceRepository();
        storeService = new StoreService(storeRepository);
    }

    @AfterEach
    public void afterEach() {
        storeRepository.storeListClear();
    }

    @Test
    void saveNewStore() {
        Store store = new Store("마트");

        String savedStoreType = storeService.save(store);
        Optional<Store> findStoreOptional = storeService.findByType("마트");

        assertTrue(findStoreOptional.isPresent());
        assertEquals("마트", savedStoreType);
        assertEquals(store, findStoreOptional.get());
    }

    @Test
    void validateDuplicateStore() {
        Store store1 = new Store("주유");
        Store store2 = new Store("마트");
        Store store3 = new Store("주유");

        storeService.save(store1);
        storeService.save(store2);
        //storeService.save(store3); => 예외 상황
    }

    @Test
    void findAll() {}

    @Test
    void findByType() {}
}
