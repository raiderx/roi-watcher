package org.karpukhin.roiwatcher.roi

import groovy.transform.CompileStatic

/**
 * @author Pavel Karpukhin
 * @since 09.12.16
 */
@CompileStatic
class Petition {

    int id

    String url

    String title

    String description

    String decision

    int votes

    int negativeVotes

    @Override
    String toString() {
        return "Petition{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", decision='" + decision + '\'' +
                ", votes=" + votes +
                ", negativeVotes=" + negativeVotes +
                '}'
    }
}
