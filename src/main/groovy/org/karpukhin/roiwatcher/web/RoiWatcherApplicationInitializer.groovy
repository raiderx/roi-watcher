package org.karpukhin.roiwatcher.web

import groovy.transform.CompileStatic
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

/**
 * @author Pavel Karpukhin
 * @since 17.12.16.
 */
@CompileStatic
class RoiWatcherApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        ['*.html']
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        null
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        null
    }
}
