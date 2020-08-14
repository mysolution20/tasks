package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;


    /**
     * http://localhost:8080/v1/task/getTasks
     * TaskDto taskDto1 = new TaskDto(1L, "Title No.1", "test_content No.1");
     * List<TaskDto> listTaskToDo = new ArrayList<>();
     * listTaskToDo.add(taskDto1);
     * return new ArrayList<>(listTaskToDo);
     */

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {

        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    /**
     * 19.2 Zadanie: wyszukiwanie pojedynczego zadania
     *
     * http://localhost:8080/v1/task/getTask?taskId=1
     * return new TaskDto(1L, "test title", "test_content");     *
     *
     * @RequestMapping(method = RequestMethod.GET, value = "getTask")
     * public TaskDto getTask(Long taskId) {
     * return taskMapper.mapToTaskDto(service.getById(taskId));
     * }
     */


    /**
     * http://localhost:8080/v1/task/getTask?taskId=1
     */
    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    /**
     * 19.3  Zadanie: usuwanie zadania
     * <p>
     * http://localhost:8080/v1/task/deleteTask?taskId=1
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId) {
        service.deleteTaskById(taskId);
    }

    /**
     * http://localhost:8080/v1/task/updateTask
     * return new TaskDto(1L, "Edit test title", "Test content");
     */
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        final Task task = taskMapper.mapToTask(taskDto);// map DTO /data transfer object/ do encji dla zmian w DB
        final Task saveTask = service.saveTask(task);   // po zmanie ma encje aby zapisać do BD gdzie uzupełnia id
        return taskMapper.mapToTaskDto(saveTask);       // po zmianie id mapuje ponownie na/DTO/ aby nie zwracać encji
    }

    /**
     * http://localhost:8080/v1/task/createTask
     */
    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
