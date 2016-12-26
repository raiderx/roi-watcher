package org.karpukhin.roiwatcher.roi

import groovy.transform.CompileStatic
import org.apache.http.client.methods.HttpGet
import org.apache.http.conn.HttpClientConnectionManager
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients

import java.text.MessageFormat

import static org.springframework.util.Assert.isTrue
import static org.springframework.util.Assert.notNull

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
class RoiStreamProviderImpl implements RoiStreamProvider {

    private static final String LAST_ITEMS_URL = 'https://www.roi.ru/poll/last/?archive=1'
    private static final String LAST_ITEMS_FOR_PAGE_URL = 'https://www.roi.ru/poll/last/?page={0}&archive=1'
    private static final String PETITION_URL = 'https://www.roi.ru/{0}/'
    private static final String ITEMS_URL = 'https://www.roi.ru/poll/last/?author={0}&archive=1'

    private final CloseableHttpClient httpClient;

    RoiStreamProviderImpl(HttpClientConnectionManager connectionManager) {
        notNull(connectionManager, 'Parameter \'connectionManager\' can not be null')
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).build()
    }

    @Override
    InputStream getLastPetitionPreviewsStream() {
        def request = new HttpGet(LAST_ITEMS_URL)
        httpClient.execute(request).entity.content
    }

    @Override
    InputStream getLastPetitionPreviewsForPageStream(int page) {
        isTrue(page > 0, 'Parameter \'page\' can not be less than 1')
        if (page == 1) {
            return getLastPetitionPreviewsStream()
        }
        def request = new HttpGet(MessageFormat.format(LAST_ITEMS_FOR_PAGE_URL, Integer.toString(page)))
        httpClient.execute(request).entity.content
    }

    @Override
    InputStream getPetitionStream(int id) {
        def request = new HttpGet(MessageFormat.format(PETITION_URL, Integer.toString(id)))
        httpClient.execute(request).entity.content
    }
}
