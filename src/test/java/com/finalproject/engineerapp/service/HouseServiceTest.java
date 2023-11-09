package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.House;
import com.finalproject.engineerapp.repositories.HouseRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class HouseServiceTest {

    @InjectMocks
    private HouseServiceImpl houseService;

    @Mock
    private HouseRepository houseRepository;
    private final House house1 = new House(1L, "Nora");
    private final House house2 = new House(2L, "Tina");
    private final House house3 = new House(3L, "Fiona");
    private final List<House> houses = new ArrayList<>();


    @Test
    void getHousesTest() {
        houses.add(house1);
        houses.add(house2);
        when(houseRepository.findAll()).thenReturn(houses);
        assertEquals(2, houseService.getHouses().size());
    }

    @Test
    void updateHouseTest() {
        houseService.updateHouse(house1);
        verify(houseRepository,times(1)).save(house1);
    }

    @Test
    void addHouseTest() {
        houseService.addHouse(house3);
        verify(houseRepository, times(1)).save(house3);
    }

    @Test
    void deleteHouseTest() {
        houseRepository.deleteHouse(house3);
        verify(houseRepository, times(1)).delete(house3);
    }

    @Test
    void findHouseByIdTest() {
        Long id = 1L;
        when(houseRepository.findById(id)).thenReturn(Optional.of(house1));
        assertEquals("Nora", houseService.findHouseById(id).get().getName());
    }
}