package org.karpukhin.roiwatcher.web.controller.html

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * @author Pavel Karpukhin
 * @since 20.12.16
 */
@CompileStatic
@Controller
class IndexController {

    @RequestMapping(value = '/index', method = RequestMethod.GET)
    String index(Model model) {
        model.addAttribute('recipient', 'World');
        'index';
    }
}
