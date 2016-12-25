package org.karpukhin.roiwatcher.task

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.karpukhin.roiwatcher.model.PetitionPreview
import org.karpukhin.roiwatcher.repository.PetitionPreviewRepository
import org.karpukhin.roiwatcher.roi.RoiApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.Instant

import static org.springframework.util.Assert.notNull

/**
 *
 * @author Pavel Karpukhin
 * @since 17.12.16.
 */
@CompileStatic
@Component
@Slf4j
class UpdatePreviewsTask implements Runnable {

    private RoiApi roiApi;

    private PetitionPreviewRepository petitionPreviewRepository;

    @Autowired
    UpdatePreviewsTask(RoiApi roiApi, PetitionPreviewRepository petitionPreviewRepository) {
        notNull(roiApi, 'Parameter \'roiApi\' can not be null')
        notNull(petitionPreviewRepository, 'Parameter \'petitionPreviewRepository\' can not be null')
        this.roiApi = roiApi
        this.petitionPreviewRepository = petitionPreviewRepository
    }

    @Override
    void run() {
        log.info('{} started', UpdatePreviewsTask.class.simpleName)
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
                preview.added = Instant.now()
                petitionPreviewRepository.save(preview)
            }
        }
        newPreviews
    }
}
