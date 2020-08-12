package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;                  //  <-- Tomkat
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;  //  <-- Tomkat

@SpringBootApplication
public class TasksApplication /*extends SpringBootServletInitializer*/ {            /**  <-- Tomkat */
	public static void main(String[] args) {
	    SpringApplication.run(TasksApplication.class, args);
	}

/*    @Override                                                                      //  <-- Tomkat
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TasksApplication.class);
    }*/
}
