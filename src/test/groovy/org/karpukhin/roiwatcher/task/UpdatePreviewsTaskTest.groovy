package org.karpukhin.roiwatcher.task

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.karpukhin.roiwatcher.model.PetitionPreview
import org.karpukhin.roiwatcher.repository.PetitionPreviewRepository
import org.karpukhin.roiwatcher.roi.RoiApi

import static org.hamcrest.Matchers.greaterThan
import static org.hamcrest.Matchers.is
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.verifyNoMoreInteractions
import static org.mockito.Mockito.verifyZeroInteractions
import static org.mockito.Mockito.when
import static org.mockito.hamcrest.MockitoHamcrest.argThat
import static org.mockito.hamcrest.MockitoHamcrest.intThat

/**
 *
 * @author Pavel Karpukhin
 * @since 20.12.16.
 */
@CompileStatic
class UpdatePreviewsTaskTest {

    private RoiApi api
    private PetitionPreviewRepository repository
    private Runnable task;

    @Before
    void setUp() {
        api = mock(RoiApi.class)
        repository = mock(PetitionPreviewRepository.class)
        task = new UpdatePreviewsTask(api, repository)
    }

    @Test
    void testWhenNoPreviews() {
        when(api.getLastPetitionPreviewsForPage(intThat(is(1)))).thenReturn(Collections.EMPTY_LIST)
        task.run()
        verify(api, times(1)).getLastPetitionPreviewsForPage(1)
        verifyNoMoreInteractions(api)
        verifyZeroInteractions(repository)
    }

    @Test
    void testWhenOnePreview() {
        def preview = new PetitionPreview(url: 'some-url')
        def previews = Arrays.asList(preview)
        when(api.getLastPetitionPreviewsForPage(intThat(is(1)))).thenReturn(previews)
        when(api.getLastPetitionPreviewsForPage(intThat(greaterThan(1)))).thenReturn(Collections.EMPTY_LIST)
        when(repository.findByUrl(argThat(is(preview.url)))).thenReturn(null)
        task.run()
        verify(api, times(1)).getLastPetitionPreviewsForPage(1)
        verify(api, times(1)).getLastPetitionPreviewsForPage(2)
        verifyNoMoreInteractions(api)
        verify(repository, times(1)).findByUrl(preview.url)
        verify(repository, times(1)).save(preview)
        verifyNoMoreInteractions(repository)
    }
}
