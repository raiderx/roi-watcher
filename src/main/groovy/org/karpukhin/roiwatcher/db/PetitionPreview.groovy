package org.karpukhin.roiwatcher.db

import groovy.transform.CompileStatic

/**
 * @author Pavel Karpukhin
 * @since 09.12.16
 */
@CompileStatic
class PetitionPreview {

    int id

    String url

    String title

    boolean locked
}
