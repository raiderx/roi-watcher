package org.karpukhin.roiwatcher

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.not
import static org.hamcrest.Matchers.nullValue
import static org.junit.Assert.assertThat
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
class RoiApiImplTest {

    private RoiStreamProvider streamProvider
    private RoiApi api

    @Before
    void setUp() {
        streamProvider = mock(RoiStreamProvider.class)
        api = new RoiApiImpl(streamProvider)
    }

    @Test
    void testGetLastItems() {

        when(streamProvider.lastItemsStream).thenReturn(RoiStreamProvider.class.getResourceAsStream('/last.html'))

        def result = api.getLastItems()

        assertThat(result, is(not(nullValue())))
        assertThat(result, hasSize(20))

        def item = result.get(0)

        assertThat(item, is(not(nullValue())))
        assertThat(item.url, is('https://www.roi.ru/31766/'))
        assertThat(item.voices, is(1))
    }

    @Test
    void testGetLastItemsForPage() {

        when(streamProvider.getLastItemsForPageStream(2)).thenReturn(RoiStreamProvider.class.getResourceAsStream('/last_for_page_2.html'))

        def result = api.getLastItemsForPage(2)

        assertThat(result, is(not(nullValue())))
        assertThat(result, hasSize(20))

        def item = result.get(0)

        assertThat(item, is(not(nullValue())))
        assertThat(item.url, is('https://www.roi.ru/31934/'))
        assertThat(item.voices, is(1757))
    }
}
