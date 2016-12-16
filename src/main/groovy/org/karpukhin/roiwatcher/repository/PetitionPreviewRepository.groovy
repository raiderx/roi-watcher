package org.karpukhin.roiwatcher.repository

import org.karpukhin.roiwatcher.model.PetitionPreview
import org.springframework.data.repository.CrudRepository

/**
 * @author Pavel Karpukhin
 * @since 13.12.16
 */
interface PetitionPreviewRepository extends CrudRepository<PetitionPreview, Integer> {

    PetitionPreview findByUrl(String url)
}