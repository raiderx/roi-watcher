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

    private final RoiStreamProvider provider

    RoiApiImpl(RoiStreamProvider provider) {
        this.provider = provider
    }

    List<Item> getLastItems() {
        def document = Jsoup.parse(provider.lastItemStream, 'UTF-8', ROI_BASE_URL)

        def els = document.select('div.item')

        return els.stream().map({ Element e -> getItem(e) }).collect(Collectors.toList())
    }

    List<Item> getLastItemsForPage(int page) {
        return null
    }

    static Item getItem(Element element) {
        def link = element.select('div.link a').get(0)
        def jurisdiction = element.select('div.jurisdiction').get(0)
        new Item(url: link.attr('href'), title: link.text(), jurisdiction: jurisdiction.text())
    }
}
