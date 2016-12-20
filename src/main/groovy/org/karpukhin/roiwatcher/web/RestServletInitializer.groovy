package org.karpukhin.roiwatcher.web

import groovy.transform.CompileStatic
import org.karpukhin.roiwatcher.repository.RepositoryConfig
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

/**
 * @author Pavel Karpukhin
 * @since 20.12.16
 */
@CompileStatic
class RestServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        [RepositoryConfig.class] as Class[]
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        [RestWebConfig.class] as Class[]
    }

    @Override
    protected String[] getServletMappings() {
        ['/api/*'] as String[]
    }

    @Override
    protected String getServletName() {
        'rest'
    }
}
