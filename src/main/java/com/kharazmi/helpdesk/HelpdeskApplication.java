package com.kharazmi.helpdesk;

import com.cmeza.sdgenerator.annotation.SDGenerator;
import com.kharazmi.helpdesk.Repository.RoleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SDGenerator(
        entityPackage = "com.kharazmi.helpdesk.Model",
        repositoryPackage = "com.kharazmi.helpdesk.Repository",
        repositoryPostfix = "Repository",
        managerPostfix = "Manager",
        onlyAnnotations = false,
        debug = false,
        overwrite = false
)
@SpringBootApplication
public class HelpdeskApplication {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Autowired
    RoleModelRepository roleModelRepository;
    public static void main(String[] args) {

        SpringApplication.run(HelpdeskApplication.class, args



        );
    }

}

