package org.karpukhin.roiwatcher.roi

import groovy.transform.CompileStatic
import org.karpukhin.roiwatcher.model.PetitionPreview

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
interface RoiApi {

    List<PetitionPreview> getLastPetitionPreviews()

    List<PetitionPreview> getLastPetitionPreviewsForPage(int page)

    Petition getPetition(int id)
}