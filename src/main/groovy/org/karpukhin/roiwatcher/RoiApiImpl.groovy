package org.karpukhin.roiwatcher

import groovy.transform.CompileStatic
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

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

    List<Item> getLastItems() {
        def document = Jsoup.parse(provider.lastItemsStream, UTF8, ROI_BASE_URL)
        def els = document.select('div.item')
        return els.stream().map({ Element e -> getItem(e) }).collect(Collectors.toList())
    }

    List<Item> getLastItemsForPage(int page) {
        def document = Jsoup.parse(provider.getLastItemsForPageStream(page), UTF8, ROI_BASE_URL)
        def els = document.select('div.item')
        return els.stream().map({ Element e -> getItem(e) }).collect(Collectors.toList())
    }

    static Item getItem(Element element) {
        def link = element.select('div.link a').get(0)
        def jurisdiction = element.select('div.jurisdiction').get(0)
        def voices = element.select('div.hour b').get(0)
        new Item(url: ROI_BASE_URL + link.attr('href'), voices: Integer.parseInt(voices.text()),
                title: link.text(), jurisdiction: jurisdiction.text())
    }
}
