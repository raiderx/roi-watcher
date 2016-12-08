package org.karpukhin.roiwatcher

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.not
import static org.hamcrest.Matchers.nullValue
import static org.junit.Assert.assertThat

/**
 * @author Pavel Karpukhin
 * @since 08.12.16
 */
@CompileStatic
class RoiApiImplTest {

    private static final RoiStreamProvider streamProvider = new RoiStreamProviderTestImpl();
    private RoiApi api;

    @Before
    void setUp() {
        api = new RoiApiImpl(streamProvider);
    }

    @Test
    void testGetLastItems() {
        List<Item> result = api.getLastItems();

        assertThat(result, is(not(nullValue())));
        assertThat(result, hasSize(20));
    }

}
