package org.karpukhin.roiwatcher

import groovy.transform.CompileStatic

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
interface RoiStreamProvider {

    InputStream getLastPetitionPreviewsStream()

    InputStream getLastPetitionPreviewsForPageStream(int page)

    InputStream getPetitionStream(int id)
}