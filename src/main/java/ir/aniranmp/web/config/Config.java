package ir.aniranmp.web.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.codecentric.boot.admin.client.registration.Application;
import ir.aniranmp.web.Main;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Collections;

@Configuration
public class Config {




    @Bean
    public ErrorViewResolver customErrorViewResolver() {
        final ModelAndView redirectToIndexHtml = new ModelAndView("forward:/index.html", Collections.emptyMap(), HttpStatus.OK);
        return (request, status, model) -> status == HttpStatus.NOT_FOUND ? redirectToIndexHtml : null;
    }




}
