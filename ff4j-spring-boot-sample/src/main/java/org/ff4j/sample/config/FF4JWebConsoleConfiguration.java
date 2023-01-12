package org.ff4j.sample.config;

import java.beans.ConstructorProperties;
import org.ff4j.FF4j;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FF4JWebConsoleConfiguration extends SpringBootServletInitializer implements
    WebMvcConfigurer {

  @Bean
  public FF4jDispatcherServlet getFF4jDispatcherServlet(FF4j ff4j) {
    var ff4jConsoleServlet = new FF4jDispatcherServlet();
    ff4jConsoleServlet.setFf4j(ff4j);
    return ff4jConsoleServlet;
  }

  @Bean
  public ServletRegistrationBean ff4jDispatcherServletRegistrationBean(
      FF4jDispatcherServlet ff4jDispatcherServlet) {
    return new ServletRegistrationBean(ff4jDispatcherServlet, "/ff4j-web-console/*");
  }
}
