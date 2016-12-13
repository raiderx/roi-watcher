package org.karpukhin.roiwatcher.db

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author Pavel Karpukhin
 * @since 09.12.16
 */
@CompileStatic
@Entity
@Table(name = 'PETITIOPN_PREVIEW')
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
}
