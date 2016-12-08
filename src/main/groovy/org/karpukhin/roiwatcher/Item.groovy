package org.karpukhin.roiwatcher

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
class Item {

    String url

    int voices

    String title

    String jurisdiction

    @Override
    String toString() {
        return "Item{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", jurisdiction='" + jurisdiction + '\'' +
                '}'
    }
}
