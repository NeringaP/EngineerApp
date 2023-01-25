package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.Project;
import com.finalproject.engineerapp.repositories.ProjectRepository;
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
class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;
    private final Project project1 = new Project(1L, "Onara");
    private final Project project2 = new Project(2L, "Finesse");
    private final Project project3 = new Project(3L, "Brandberg");
    private final List<Project> projects = new ArrayList<>();

    @Test
    void getProjectsTest() {
        projects.add(project1);
        projects.add(project2);
        when(projectRepository.findAll()).thenReturn(projects);
        assertEquals(2, projectService.getProjects().size());
    }

    @Test
    void updateProjectTest() {
        projectService.updateProject(project1);
        verify(projectRepository,times(1)).save(project1);
    }

    @Test
    void addProjectTest() {
        projectService.addProject(project3);
        verify(projectRepository, times(1)).save(project3);
    }

    @Test
    void deleteProjectTest() {
        projectService.deleteProject(project3);
        verify(projectRepository, times(1)).delete(project3);
    }

    @Test
    void findProjectByIdTest() {
        Long id = 1L;
        when(projectRepository.findById(id)).thenReturn(Optional.of(project1));
        assertEquals("Onara", projectService.findProjectById(id).get().getName());
    }
}