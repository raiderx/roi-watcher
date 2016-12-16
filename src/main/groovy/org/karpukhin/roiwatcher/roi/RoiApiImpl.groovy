package org.karpukhin.roiwatcher.roi

import groovy.transform.CompileStatic
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.karpukhin.roiwatcher.model.PetitionPreview

import java.util.stream.Collectors

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
class RoiApiImpl implements RoiApi {

    private static final String ROI_BASE_URL = 'https://www.roi.ru'
    private static final String UTF8 = 'UTF-8'

    private final RoiStreamProvider provider

    RoiApiImpl(RoiStreamProvider provider) {
        this.provider = provider
    }

    @Override
    List<PetitionPreview> getLastPetitionPreviews() {
        def stream = provider.lastPetitionPreviewsStream
        def document = Jsoup.parse(stream, UTF8, ROI_BASE_URL)
        stream.close()
        def els = document.select('div.item')
        return els.stream().map({ Element e -> getItem(e) }).collect(Collectors.toList())
    }

    @Override
    List<PetitionPreview> getLastPetitionPreviewsForPage(int page) {
        def stream = provider.getLastPetitionPreviewsForPageStream(page)
        def document = Jsoup.parse(stream, UTF8, ROI_BASE_URL)
        stream.close()
        def els = document.select('div.item')

        return els.stream().map({ Element e -> getItem(e) }).collect(Collectors.toList())
    }

    @Override
    Petition getPetition(int id) {
        def stream = provider.getPetitionStream(id)
        def document = Jsoup.parse(stream, UTF8, ROI_BASE_URL)
        stream.close()
        def titleEl = document.select('h1')
        def descriptionEl = document.select('div.petition-text-block')
        def decisionEl = document.select('div.decision-item div.paragraph-transform')
        def votesEl = document.select('#voting-status > div.status > div > div.number > div')
        def negativeVotesEl = document.select('div.voting-solution > div.negative > span > b')
        return new Petition(id: id, url: ROI_BASE_URL, title: titleEl.text(), description: descriptionEl.text(),
                decision: decisionEl.text(), votes: Integer.parseInt(votesEl.text()),
                negativeVotes: Integer.parseInt(negativeVotesEl.text()))
    }

    static PetitionPreview getItem(Element element) {
        def linkEl = element.select('div.link a')
        def jurisdictionEl = element.select('div.jurisdiction')
        def voicesEl = element.select('div.hour b')
        def lockedEl = element.select('li.lock')
        new PetitionPreview(url: ROI_BASE_URL + linkEl.attr('href'), voices: Integer.parseInt(voicesEl.text()),
                title: linkEl.text(), jurisdiction: jurisdictionEl.text(), locked: !lockedEl.empty)
    }
}
