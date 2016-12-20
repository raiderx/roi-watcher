package org.karpukhin.roiwatcher.task

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.karpukhin.roiwatcher.model.PetitionPreview
import org.karpukhin.roiwatcher.repository.PetitionPreviewRepository
import org.karpukhin.roiwatcher.roi.RoiApi

import static org.hamcrest.Matchers.any
import static org.hamcrest.Matchers.greaterThan
import static org.hamcrest.Matchers.is
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.ArgumentMatchers.argThat
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.never
import static org.mockito.Mockito.only
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.hamcrest.MockitoHamcrest.argThat
import static org.mockito.hamcrest.MockitoHamcrest.intThat

/**
 *
 * @author Pavel Karpukhin
 * @since 20.12.16.
 */
@CompileStatic
class UpdatePreviewListTaskTest {

    private RoiApi api
    private PetitionPreviewRepository repository
    private Runnable task;

    @Before
    void setUp() {
        api = mock(RoiApi.class)
        repository = mock(PetitionPreviewRepository.class)
        task = new UpdatePreviewListTask(api, repository)
    }

    @Test
    void testWhenNoPreviews() {
        when(api.getLastPetitionPreviewsForPage(intThat(is(1)))).thenReturn(Collections.EMPTY_LIST)
        task.run()
        verify(repository, never()).findByUrl(argThat(any(String.class)))
        verify(repository, never()).save(argThat(any(PetitionPreview.class)))
    }

    @Test
    void testWhen() {
        def preview = new PetitionPreview(url: 'some-url')
        def previews = Arrays.asList(preview)
        when(api.getLastPetitionPreviewsForPage(intThat(is(1)))).thenReturn(previews)
        when(api.getLastPetitionPreviewsForPage(intThat(greaterThan(1)))).thenReturn(Collections.EMPTY_LIST)
        when(repository.findByUrl(argThat(is(preview.url)))).thenReturn(null)
        task.run()
        verify(repository).findByUrl(argThat(is(preview.url)))
        //verify(repository, only()).save(argThat(is(preview)))
    }
}
