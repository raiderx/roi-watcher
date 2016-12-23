package org.karpukhin.roiwatcher.repository

import org.karpukhin.roiwatcher.model.PetitionPreview
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * @author Pavel Karpukhin
 * @since 13.12.16
 */
interface PetitionPreviewRepository extends PagingAndSortingRepository<PetitionPreview, Integer> {

    PetitionPreview findByUrl(String url)

    Page<PetitionPreview> findByTitleContainingIgnoreCase(String title, Pageable pageable)
}