package org.karpukhin.roiwatcher

import groovy.transform.CompileStatic

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
class RoiStreamProviderTestImpl implements RoiStreamProvider {

    InputStream getLastItemStream() {
        return RoiStreamProvider.class.getResourceAsStream('/last.html')
    }
}
