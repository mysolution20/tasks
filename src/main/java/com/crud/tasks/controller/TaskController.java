package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")   //http://localhost:8080/v1/task/getTasks
    public List<TaskDto> getTasks() {

/*      TaskDto taskDto1 = new TaskDto(1L, "Title No.1", "test_content No.1");
        List<TaskDto> listTaskToDo = new ArrayList<>();
        listTaskToDo.add(taskDto1);
        return new ArrayList<>(listTaskToDo);*/

        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")   // http://localhost:8080/v1/task/getTask
    public TaskDto getTask(Long taskId) {
        //  return new TaskDto(1L, "test title", "test_content");
        return taskMapper.mapToTaskDto(service.getById(taskId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask") // http://localhost:8080/v1/task/deleteTask
    public void deleteTask(Long taskId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")  // http://localhost:8080/v1/task/updateTask
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edit test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask") // http://localhost:8080/v1/task/createTask
    public void createTask(TaskDto taskDto) {
    }
}
