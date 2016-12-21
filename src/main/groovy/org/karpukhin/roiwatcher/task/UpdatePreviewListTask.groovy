package org.karpukhin.roiwatcher.task

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.karpukhin.roiwatcher.model.PetitionPreview
import org.karpukhin.roiwatcher.repository.PetitionPreviewRepository
import org.karpukhin.roiwatcher.roi.RoiApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 *
 * @author Pavel Karpukhin
 * @since 17.12.16.
 */
@CompileStatic
@Component
@Slf4j
class UpdatePreviewListTask implements Runnable {

    private RoiApi roiApi;

    private PetitionPreviewRepository petitionPreviewRepository;

    @Autowired
    UpdatePreviewListTask(RoiApi roiApi, PetitionPreviewRepository petitionPreviewRepository) {
        this.roiApi = roiApi
        this.petitionPreviewRepository = petitionPreviewRepository
    }

    @Override
    void run() {
        def newPetitions = 1
        def page = 0
        while (newPetitions > 0) {
            def previews = roiApi.getLastPetitionPreviewsForPage(++page)
            log.info('Got previews: {}', previews.size())
            newPetitions = savePreviews(previews)
            log.info('Saved previews: {}', newPetitions)
        }
    }

    private int savePreviews(Iterable<PetitionPreview> previews) {
        def newPreviews = 0;
        for (preview in previews) {
            def other = petitionPreviewRepository.findByUrl(preview.url)
            if (other == null) {
                ++newPreviews
                petitionPreviewRepository.save(preview)
            }
        }
        newPreviews
    }
}
