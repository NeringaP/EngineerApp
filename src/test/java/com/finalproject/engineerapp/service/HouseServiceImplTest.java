package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.repository.HouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HouseServiceImplTest {

    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private HouseServiceImpl houseService;

    @Test
    void testFindAll() {
        // Mocking data
        List<House> houses = new ArrayList<>();
        when(houseRepository.findAll()).thenReturn(houses);

        // Calling the method
        List<House> result = houseService.findAll();

        // Verifying the result
        assertEquals(houses, result);
        verify(houseRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Mocking data
        House house = new House();
        when(houseRepository.save(house)).thenReturn(house);

        // Calling the method
        House result = houseService.save(house);

        // Verifying the result
        assertEquals(house, result);
        verify(houseRepository, times(1)).save(house);
    }

    @Test
    void testDelete() {
        // Mocking data
        Long houseId = 1L;
        House house = new House();
        house.setId(houseId);

        when(houseRepository.findById(houseId)).thenReturn(Optional.of(house));

        // Calling the method
        assertDoesNotThrow(() -> houseService.delete(houseId));

        // Verifying the result
        verify(houseRepository,times(1)).delete(house);
    }

    @Test
    void testFindById() {
        // Mocking data
        Long houseId = 1L;
        House house = new House();
        house.setId(houseId);

        when(houseRepository.findById(houseId)).thenReturn(Optional.of(house));

        // Calling the method
        assertDoesNotThrow(() -> {
            House result = houseService.findById(houseId);
            assertEquals(house, result);
        });

        // Verifying the result
        verify(houseRepository,times(1)).findById(houseId);
    }

    @Test
    void testFindByIdWithNonExistentId() {
        // Mocking data
        Long invalidId = 100L;
        when(houseRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Calling the method and expecting an exception
        assertThrows(NoSuchElementException.class, () -> houseService.findById(invalidId));
    }
}