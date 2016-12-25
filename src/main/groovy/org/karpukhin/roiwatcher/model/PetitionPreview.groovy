package org.karpukhin.roiwatcher.model

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.Instant

/**
 * @author Pavel Karpukhin
 * @since 09.12.16
 */
@CompileStatic
@Entity
@Table(name = 'PETITION_PREVIEW')
class PetitionPreview {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    Integer id
    @Column(name = 'URL', nullable = false, length = 255)
    String url
    @Column(name = 'TITLE', nullable = false, length = 255)
    String title
    @Column(name = 'IS_LOCKED', nullable = false)
    boolean locked
    @Column(name = 'VOICES', nullable = false)
    int voices
    @Column(name = 'JURISDICTION', nullable = false, length = 50)
    String jurisdiction
    @Column(name = 'ADDED', nullable = false)
    Instant added

    @Override
    String toString() {
        return "PetitionPreview{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", locked=" + locked +
                ", voices=" + voices +
                ", jurisdiction='" + jurisdiction + '\'' +
                '}'
    }
}
