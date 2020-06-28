package com.example.emt2_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import emt_lab.shared-kernel.src.main.java.com.example.emt2_lab.SharedConfiguration;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class ProjectCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectCatalogApplication.class, args);
    }

}
