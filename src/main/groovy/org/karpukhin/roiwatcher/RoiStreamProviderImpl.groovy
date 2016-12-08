package org.karpukhin.roiwatcher

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
class RoiStreamProviderImpl implements RoiStreamProvider {

    private static final String LAST_ITEMS_URL = 'https://www.roi.ru/poll/last/'
    private static final String LAST_ITEMS_FOR_PAGE_URL = 'https://www.roi.ru/poll/last/?page={0}'

    @Override
    InputStream getLastItemsStream() {
        null
    }

    @Override
    InputStream getLastItemsForPageStream(int page) {
        null
    }
}
