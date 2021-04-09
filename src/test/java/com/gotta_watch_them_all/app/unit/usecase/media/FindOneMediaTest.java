package com.gotta_watch_them_all.app.unit.usecase.media;

import com.gotta_watch_them_all.app.core.dao.MediaDao;
import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.exception.NotFoundException;
import com.gotta_watch_them_all.app.usecase.media.FindOneMedia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindOneMediaTest {
    @Mock
    private MediaDao mockMediaDao;

    FindOneMedia sut;

    @BeforeEach
    public void setup() {
        sut = new FindOneMedia(mockMediaDao);
    }

    @Test
    public void when_mediaDao_not_found_media_should_throw() {
        var mediaId = 1L;
        when(mockMediaDao.findById(mediaId)).thenReturn(null);

        assertThatThrownBy(() -> sut.execute(mediaId))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Media with id '" + mediaId + "' not found");
    }

    @Test
    public void when_mediaDao_found_media_should_return_concerned_one() throws NotFoundException {
        var mediaId = 1L;
        var expectedMedia = Media.builder()
                .id(mediaId)
                .name("The media")
                .build();
        when(mockMediaDao.findById(mediaId)).thenReturn(expectedMedia);

        var result = sut.execute(mediaId);

        assertThat(result).isEqualTo(expectedMedia);
    }
}