package UNIS.leap_mvp.service;

import UNIS.leap_mvp.domain.Store;
import UNIS.leap_mvp.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class StoreServiceTest {

    private StoreRepository storeRepository;
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        storeRepository = Mockito.mock(StoreRepository.class);
        storeService = new StoreService(storeRepository);
    }

    @Test
    void saveStore() {
        Store store1 = new Store();
        store1.setType("마트");
        Store store2 = new Store();
        store2.setType("스벅");
        Store store3 = new Store();
        store3.setType("영화");
        Store store4 = new Store();
        store4.setType("주유");

        String savedStoreType1 = storeService.save(store1);
        String savedStoreType2 = storeService.save(store2);
        String savedStoreType3 = storeService.save(store3);
        String savedStoreType4 = storeService.save(store4);

        System.out.println("Saved Store 1: " + savedStoreType1);
        System.out.println("Saved Store 2: " + savedStoreType2);
        System.out.println("Saved Store 3: " + savedStoreType3);
        System.out.println("Saved Store 4: " + savedStoreType4);
    }


    @Test
    void findStoreByType() {
        Store store1 = new Store();
        store1.setType("마트");
        storeService.save(store1);
        Store store2 = new Store();
        store2.setType("스벅");
        storeService.save(store2);
        Store store3 = new Store();
        store3.setType("영화");
        storeService.save(store3);
        Store store4 = new Store();
        store4.setType("주유");
        storeService.save(store4);

        Store foundStore = storeService.findByType("마트");

        System.out.println(foundStore);
    }

    @Test
    void findStoreByTypeNotFound() {
        // given
        String storeType = "비디오방";

        // when
        Mockito.when(storeRepository.findByType(storeType)).thenReturn(Optional.empty());

        // then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> storeService.findByType(storeType));
        assertEquals("해당 타입의 가게가 존재하지 않습니다: " + storeType, exception.getMessage());
    }
}
