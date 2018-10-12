package com.porlabs.templates.config;


import com.porlabs.templates.logger.EventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
    private static EventLogger<WebConfiguration> LOGGER = new EventLogger(WebConfiguration.class);

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".html");
//        viewResolver.setOrder(2);
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/doc", "/swagger-ui.html");
        registry.addViewController("/home/**").setViewName("forward:/");
        registry.addViewController("/login").setViewName("forward:/");
        registry.addViewController("/loginPsap").setViewName("forward:/");
        registry.addRedirectViewController("/info", "/actuator/info");
        registry.addRedirectViewController("/health", "/actuator/health");
        registry.addRedirectViewController("/metrics", "/actuator/metrics");
        registry.addRedirectViewController("/trace", "/actuator/trace");
//        registry.setOrder(3);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        // to serve static .html pages...
        registry.addResourceHandler("/static/**").addResourceLocations("/resources/static/");

        // Swagger resources
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON)
                .favorPathExtension(true);
    }


    @Bean
    public ViewResolver excelViewResolver() {
        return null; //new ExcelViewResolver();
    }

    /*
     * Configure ContentNegotiatingViewResolver
     */
//    @Bean
//    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//        resolver.setContentNegotiationManager(manager);
//        List<ViewResolver> resolvers = new ArrayList<>();
//        resolvers.add(excelViewResolver());
//        resolver.setViewResolvers(resolvers);
//        resolver.setOrder(1);
//        return resolver;
//    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @PostConstruct
    private void postConstruct(){
        LOGGER.info("Application web Configuration complete");
    }
}
