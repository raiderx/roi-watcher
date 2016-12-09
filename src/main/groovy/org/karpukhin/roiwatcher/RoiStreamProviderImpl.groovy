package org.karpukhin.roiwatcher

import groovy.transform.CompileStatic
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

import java.text.MessageFormat

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
class RoiStreamProviderImpl implements RoiStreamProvider {

    private static final String LAST_ITEMS_URL = 'https://www.roi.ru/poll/last/?archive=1'
    private static final String LAST_ITEMS_FOR_PAGE_URL = 'https://www.roi.ru/poll/last/?page={0}&archive=1'
    private static final String PETITION_URL = 'https://www.roi.ru/{0}/'

    @Override
    InputStream getLastItemsStream() {
        def request = new HttpGet(LAST_ITEMS_URL)
        def client = HttpClients.createDefault()
        def response = client.execute(request)
        response.getEntity().content
    }

    @Override
    InputStream getLastItemsForPageStream(int page) {
        if (page < 1) {
            throw new IllegalArgumentException('Parameter \'page\' can not be less than 1')
        }
        if (page == 1) {
            return getLastItemsStream()
        }
        def request = new HttpGet(MessageFormat.format(LAST_ITEMS_FOR_PAGE_URL, page))
        HttpClients.createDefault().execute(request).entity.content
    }

    @Override
    InputStream getPetitionStream(int id) {
        def request = new HttpGet(MessageFormat.format(PETITION_URL, id))
        HttpClients.createDefault().execute(request).entity.content
    }
}
