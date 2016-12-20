package org.karpukhin.roiwatcher.web

import groovy.transform.CompileStatic
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

/**
 * @author Pavel Karpukhin
 * @since 17.12.16.
 */
@CompileStatic
class HtmlServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        null
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        [WebConfig.class] as Class[]
    }

    @Override
    protected String[] getServletMappings() {
        ['*.html'] as String[]
    }

    @Override
    protected String getServletName() {
        'html'
    }
}
