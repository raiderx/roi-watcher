package org.karpukhin.roiwatcher.web

import groovy.transform.CompileStatic
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.thymeleaf.TemplateEngine
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ITemplateResolver

/**
 * @author Pavel Karpukhin
 * @since 17.12.16.
 */
@CompileStatic
@ComponentScan('org.karpukhin.roiwatcher.web.controller.html')
@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private static final String UTF8 = 'UTF-8';

    private ApplicationContext applicationContext;

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext
    }

    @Bean
    ViewResolver viewResolver() {
        def resolver = new ThymeleafViewResolver()
        resolver.templateEngine = templateEngine()
        resolver.characterEncoding = UTF8
        resolver
    }

    @Bean
    TemplateEngine templateEngine() {
        def engine = new SpringTemplateEngine()
        engine.enableSpringELCompiler = true
        engine.templateResolver = templateResolver()
        engine
    }

    private ITemplateResolver templateResolver() {
        def resolver = new SpringResourceTemplateResolver()
        resolver.applicationContext = applicationContext
        resolver.prefix = '/WEB-INF/templates/'
        resolver.suffix = '.html'
        resolver.templateMode = TemplateMode.LEGACYHTML5
        resolver.cacheable = false
        resolver
    }
}
