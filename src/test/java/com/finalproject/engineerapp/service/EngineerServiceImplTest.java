package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Engineer;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repository.EngineerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EngineerServiceImplTest {

    @Mock
    private EngineerRepository engineerRepository;

    @InjectMocks
    private EngineerServiceImpl engineerService;

    @Test
    void testFindAll() {
        // Mocking data
        List<Engineer> engineers = new ArrayList<>();
        when(engineerRepository.findAll()).thenReturn(engineers);

        // Calling the method
        List<Engineer> result = engineerService.findAll();

        // Verifying the result
        assertEquals(engineers, result);
        verify(engineerRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Mocking data
        Engineer engineer = new Engineer();
        when(engineerRepository.save(engineer)).thenReturn(engineer);

        // Calling the method
        Engineer result = engineerService.save(engineer);

        // Verifying the result
        assertEquals(engineer, result);
        verify(engineerRepository, times(1)).save(engineer);
    }

    @Test
    void testDelete() {
        // Mocking data
        Long engineerId = 1L;
        Engineer engineer = new Engineer();
        engineer.setId(engineerId);
        Project project = new Project();
        project.setEngineer(engineer);
        Set<Project> projects = new HashSet<>();
        projects.add(project);
        engineer.setProjects(projects);

        when(engineerRepository.findById(engineerId)).thenReturn(Optional.of(engineer));

        // Calling the method
        engineerService.delete(engineerId);

        // Verifying the result
        assertNull(project.getEngineer());
        verify(engineerRepository,times(1)).delete(engineer);
    }

    @Test
    void testFindById() {
        // Mocking data
        Long engineerId = 1L;
        Engineer engineer = new Engineer();
        engineer.setId(engineerId);

        when(engineerRepository.findById(engineerId)).thenReturn(Optional.of(engineer));

        // Calling the method
        Engineer result = engineerService.findById(engineerId);

        // Verifying the result
        assertEquals(engineer, result);
        verify(engineerRepository,times(1)).findById(engineerId);
    }

    @Test
    void testFindByIdWithInvalidId() {
        // Mocking data
        Long invalidId = 100L;
        when(engineerRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Calling the method and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> engineerService.findById(invalidId));
    }
}