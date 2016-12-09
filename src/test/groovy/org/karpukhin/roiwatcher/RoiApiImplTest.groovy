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
    void testGetLastPetitionPreviews() {

        when(streamProvider.lastPetitionPreviewsStream).thenReturn(RoiStreamProvider.class.getResourceAsStream('/last.html'))

        def previews = api.getLastPetitionPreviews()

        assertThat(previews, is(not(nullValue())))
        assertThat(previews, hasSize(20))

        def preview = previews.get(0)

        assertThat(preview, is(not(nullValue())))
        assertThat(preview.url, is('https://www.roi.ru/31766/'))
        assertThat(preview.voices, is(1))
        assertThat(preview.locked, is(false))
    }

    @Test
    void testGetLastPetitionPreviewsForPage() {

        when(streamProvider.getLastPetitionPreviewsForPageStream(117)).thenReturn(RoiStreamProvider.class.getResourceAsStream('/last_for_page_117.html'))

        def previews = api.getLastPetitionPreviewsForPage(117)

        assertThat(previews, is(not(nullValue())))
        assertThat(previews, hasSize(20))

        def first = previews.get(0)

        assertThat(first, is(not(nullValue())))
        assertThat(first.url, is('https://www.roi.ru/23883/'))
        assertThat(first.voices, is(1968))
        assertThat(first.locked, is(false))

        def second = previews.get(6);
        assertThat(second, is(not(nullValue())))
        assertThat(second.url, is('https://www.roi.ru/23578/'))
        assertThat(second.voices, is(829))
        assertThat(second.locked, is(true))
    }
}
