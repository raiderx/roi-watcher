package org.karpukhin.roiwatcher.web

import groovy.transform.CompileStatic
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.json.MappingJackson2JsonView

/**
 * @author Pavel Karpukhin
 * @since 20.12.16
 */
@CompileStatic
@ComponentScan('org.karpukhin.roiwatcher.web.controller.rest')
@Configuration
@EnableWebMvc
class RestWebConfig extends WebMvcConfigurerAdapter {

    @Override
    void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType('json', MediaType.APPLICATION_JSON);
    }

    @Override
    void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.jsp()
    }
}
