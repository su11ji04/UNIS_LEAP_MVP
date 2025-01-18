package UNIS.leap_mvp.repository;

import UNIS.leap_mvp.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreServiceRepositoryTest {
    StoreServiceRepository repository = new StoreServiceRepository();

    @AfterEach
    public void afterEach(){ repository.storeListClear(); }

    @Test
    public void save() {
        Store store = new Store("주유");
        repository.save(store);

        Optional<Store> optionalSavedStore = repository.findByType(store.getType());

        assertTrue(optionalSavedStore.isPresent());
        assertEquals(store, optionalSavedStore.get());
    }

    @Test
    public void findAll(){
        Store store1 = new Store("주유");
        repository.save(store1);
        Store store2 = new Store("마트");
        repository.save(store2);

        List<Store> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findByType() {
        Store store1 = new Store("주유");
        repository.save(store1);
        Store store2 = new Store("마트");
        repository.save(store2);

        Optional<Store> result = repository.findByType("마트");

        assertTrue(result.isPresent());
        assertEquals(store2, result.get());
    }

}
