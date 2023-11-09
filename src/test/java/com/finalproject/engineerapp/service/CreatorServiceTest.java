package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.repositories.CreatorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CreatorServiceTest {

    @InjectMocks
    private CreatorServiceImpl creatorService;

    @Mock
    private CreatorRepository creatorRepository;
    private final Creator creator1 = new Creator(1L, "Tom", "John", "tom@npprojects.lt");
    private final Creator creator2 = new Creator(2L, "Jim", "Doe", "jim@npprojects.lt");
    private final Creator creator3 = new Creator(3L, "Fin", "Stone", "fin@npprojects.lt");
    private final List<Creator> creators = new ArrayList<>();

    @Test
    void getCreatorsTest() {
        creators.add(creator1);
        creators.add(creator2);
        when(creatorRepository.findAll()).thenReturn(creators);
        assertEquals(2, creatorService.getCreators().size());
    }

    @Test
    void updateCreatorTest() {
        creatorService.updateCreator(creator1);
        verify(creatorRepository,times(1)).save(creator1);
    }

    @Test
    void addCreatorTest() {
        creatorService.addCreator(creator3);
        verify(creatorRepository, times(1)).save(creator3);
    }

    @Test
    void deleteCreatorTest() {
        creatorService.deleteCreator(creator3);
        verify(creatorRepository, times(1)).delete(creator3);
    }

    @Test
    void findCreatorByIdTest() {
        Long id = 1L;
        when(creatorRepository.findById(id)).thenReturn(Optional.of(creator1));
        assertEquals("Tom", creatorService.findCreatorById(id).get().getFirstName());
    }

}