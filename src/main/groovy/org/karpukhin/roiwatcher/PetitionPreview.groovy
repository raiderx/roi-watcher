package org.karpukhin.roiwatcher

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
class PetitionPreview {

    String url

    String title

    int voices

    String jurisdiction

    boolean locked

    @Override
    String toString() {
        return "PetitionPreview{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", voices=" + voices +
                ", jurisdiction='" + jurisdiction + '\'' +
                ", locked=" + locked +
                '}'
    }
}
