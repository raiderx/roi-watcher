package org.karpukhin.roiwatcher.web.controller.rest

import groovy.transform.CompileStatic
import org.karpukhin.roiwatcher.model.PetitionPreview
import org.karpukhin.roiwatcher.repository.PetitionPreviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author Pavel Karpukhin
 * @since 20.12.16
 */
@CompileStatic
@RestController
class PreviewsController {

    @Autowired
    private PetitionPreviewRepository previewRepository

    @RequestMapping(value = '/previews', method = RequestMethod.GET)
    Page<PetitionPreview> previews(Pageable pageable) {
        previewRepository.findAll(pageable)
    }
}
