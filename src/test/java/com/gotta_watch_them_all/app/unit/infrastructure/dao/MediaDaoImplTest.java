package com.gotta_watch_them_all.app.unit.infrastructure.dao;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dao.MediaDaoImpl;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.mapper.MediaMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MediaDaoImplTest {

    @Mock
    private MediaRepository mockMediaRepository;

    private MediaDaoImpl sut;

    @BeforeEach
    void setUp() {
        sut = new MediaDaoImpl(mockMediaRepository);
    }

    @Nested
    class FindAll {
        @Test
        void should_call_findAll_of_mediaRepository() {
            sut.findAll();
            verify(mockMediaRepository, times(1)).findAll();
        }

        @Test
        void should_return_media_list() {
            MediaEntity filmMedia = MediaEntity.builder()
                    .id(1L)
                    .name("film")
                    .build();
            MediaEntity seriesMedia = MediaEntity.builder()
                    .id(2L)
                    .name("series")
                    .build();
            List<MediaEntity> mediaList = Arrays.asList(filmMedia, seriesMedia);
            List<Media> expectedList = mediaList.stream()
                    .map(MediaMapper::entityToDomain)
                    .collect(Collectors.toList());
            when(mockMediaRepository.findAll()).thenReturn(mediaList);

            var result = sut.findAll();
            assertThat(expectedList.size()).isEqualTo(expectedList.size());
            assertThat(result).isEqualTo(expectedList);
        }
    }

    @Nested
    class FindById {
        @Test
        void should_call_findById_of_mediaRepository() {
            var mediaId = 1L;
            sut.findById(mediaId);
            verify(mockMediaRepository, times(1)).findById(mediaId);
        }

        @Test
        void when_media_found_by_mediaRepository_should_return_media() {
            var mediaId = 1L;
            var filmMediaEntity = MediaEntity.builder().id(mediaId).name("film").build();
            when(mockMediaRepository.findById(mediaId)).thenReturn(Optional.of(filmMediaEntity));
            var expected = MediaMapper.entityToDomain(filmMediaEntity);

            var result = sut.findById(mediaId);

            assertThat(result).isEqualTo(expected);
        }

        @Test
        void when_media_not_found_return_null() {
            var mediaId = 1L;
            when(mockMediaRepository.findById(mediaId)).thenReturn(Optional.empty());

            assertThat(sut.findById(mediaId)).isNull();
        }
    }
}