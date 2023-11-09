package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.repositories.EngineerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EngineerServiceTest {

    @InjectMocks
    private EngineerServiceImpl engineerService;

    @Mock
    private EngineerRepository engineerRepository;
    private final Engineer engineer1 = new Engineer(1L, "Tom", "John", "tom@npprojects.lt");
    private final Engineer house2 = new Engineer(2L, "Jim", "Doe", "jim@npprojects.lt");
    private final Engineer engineer3 = new Engineer(3L, "Fin", "Stone", "fin@npprojects.lt");
    private final List<Engineer> engineers = new ArrayList<>();


    @Test
    public void getEngineersTest() {
        engineers.add(engineer1);
        engineers.add(house2);
        when(engineerRepository.findAll()).thenReturn(engineers);
        assertEquals(2, engineerService.getEngineers().size());
    }

    @Test
    void updateEngineerTest() {
        engineerService.updateEngineer(engineer1);
        verify(engineerRepository,times(1)).save(engineer1);
    }

    @Test
    void addEngineerTest() {
        engineerService.addEngineer(engineer3);
        verify(engineerRepository, times(1)).save(engineer3);
    }

    @Test
    void deleteEngineerTest() {
        engineerRepository.delete(engineer3);
        verify(engineerRepository, times(1)).delete(engineer3);
    }

    @Test
    void findEngineerByIdTest() {
        Long id = 1L;
        when(engineerRepository.findById(id)).thenReturn(Optional.of(engineer1));
        assertEquals("Tom", engineerService.findEngineerById(id).get().getFirstName());
    }
}