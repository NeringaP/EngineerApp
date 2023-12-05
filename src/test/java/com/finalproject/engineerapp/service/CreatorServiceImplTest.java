package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Creator;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repository.CreatorRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreatorServiceImplTest {

    @Mock
    private CreatorRepository creatorRepository;

    @InjectMocks
    private CreatorServiceImpl creatorService;

    @Test
    void testFindAll() {
        // Mocking data
        List<Creator> creators = new ArrayList<>();
        when(creatorRepository.findAll()).thenReturn(creators);

        // Calling the method
        List<Creator> result = creatorService.findAll();

        // Verifying the result
        assertEquals(creators, result);
        verify(creatorRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Mocking data
        Creator creator = new Creator();
        when(creatorRepository.save(creator)).thenReturn(creator);

        // Calling the method
        Creator result = creatorService.save(creator);

        // Verifying the result
        assertEquals(creator, result);
        verify(creatorRepository, times(1)).save(creator);
    }

    @Test
    void testDelete() {
        // Mocking data
        Long creatorId = 1L;
        Creator creator = new Creator();
        creator.setId(creatorId);
        Project project = new Project();
        project.setCreator(creator);
        Set<Project> projects = new HashSet<>();
        projects.add(project);
        creator.setProjects(projects);

        when(creatorRepository.findById(creatorId)).thenReturn(Optional.of(creator));

        // Calling the method
        creatorService.delete(creatorId);

        // Verifying the result
        assertNull(project.getCreator());
        verify(creatorRepository,times(1)).delete(creator);
    }

    @Test
    void testFindById() {
        // Mocking data
        Long creatorId = 1L;
        Creator creator = new Creator();
        creator.setId(creatorId);

        when(creatorRepository.findById(creatorId)).thenReturn(Optional.of(creator));

        // Calling the method
        Creator result = creatorService.findById(creatorId);

        // Verifying the result
        assertEquals(creator, result);
        verify(creatorRepository,times(1)).findById(creatorId);
    }

    @Test
    void testFindByIdWithInvalidId() {
        // Mocking data
        Long invalidId = 100L;
        when(creatorRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Calling the method and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> creatorService.findById(invalidId));
    }
}