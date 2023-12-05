package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repository.ProjectRepository;
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
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void testFindAll() {
        // Mocking data
        List<Project> projects = new ArrayList<>();
        when(projectRepository.findAll()).thenReturn(projects);

        // Calling the method
        List<Project> result = projectService.findAll();

        // Verifying the result
        assertEquals(projects, result);
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Mocking data
        Project project = new Project();
        when(projectRepository.save(project)).thenReturn(project);

        // Calling the method
        Project result = projectService.save(project);

        // Verifying the result
        assertEquals(project, result);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testDelete() {
        // Mocking data
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // Calling the method
        projectService.delete(projectId);

        // Verifying the result
        verify(projectRepository,times(1)).delete(project);
    }

    @Test
    void testFindById() {
        // Mocking data
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // Calling the method
        Project result = projectService.findById(projectId);

        // Verifying the result
        assertEquals(project, result);
        verify(projectRepository,times(1)).findById(projectId);
    }

    @Test
    void testFindByIdWithInvalidId() {
        // Mocking data
        Long invalidId = 100L;
        when(projectRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Calling the method and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> projectService.findById(invalidId));
    }
}