package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private long getHowManyTasks() {
        return taskRepository.count();                           //  <-- ex.modul 23.4
    }

    private String getOneOrMoreTasksString() {
        return (getHowManyTasks() > 1) ? " tasks" : " task";     //  <-- ex.modul 23.4
    }

    //    @Scheduled(fixedDelay = 10000)                         //  <-- step 10 seconds
    @Scheduled(cron = "0 0 10 * * *")                            //  <-- every day at 10 a.m.
    public void sendInformationEmail() {
//        long size = taskRepository.count();                    // Kodilla kod, wykomentowany ex.modul 23.4
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got: " + /* size */ getHowManyTasks() + getOneOrMoreTasksString(),
                        ""
                )
        );
    }
}
