package com.gotta_watch_them_all.app.e2e;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.mapper.MediaMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.MediaRepository;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@DirtiesContext
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MediaApiTest {
    @Autowired
    private MediaRepository mediaRepository;

    @LocalServerPort
    private int localPort;

    @BeforeEach
    void setup() {
        port = localPort;
        filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @DisplayName("METHOD GET /media")
    @Nested
    class FindAllMedias {
        @Test
        void should_get_all_medias() {
            MediaEntity filmMedia = MediaEntity.builder().name("film").build();
            MediaEntity seriesMedia = MediaEntity.builder().name("series").build();
            var mediaEntityList = Arrays.asList(filmMedia, seriesMedia);
            mediaRepository.saveAll(mediaEntityList);

            var expectedResponse = mediaEntityList.stream()
                    .map(MediaMapper::entityToDomain)
                    .collect(Collectors.toList());

            List<Media> response = given()
                    .when()
                    .get("/api/media")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath()
                    .getList(".", Media.class);

            assertThat(response).isNotNull();
            assertThat(response.size()).isEqualTo(expectedResponse.size());
            assertThat(response).isEqualTo(expectedResponse);
        }
    }

    @DisplayName("METHOD GET /media/{id}")
    @Nested
    class FindOneMedia {
        @ParameterizedTest
        @ValueSource(strings = {"notNumber", "1.0", "0", "-1"})
        void when_id_is_not_integer_more_than_zero_should_get_error_response(String notCorrectId) {
            given()
                    .when()
                    .get("/api/media/" + notCorrectId)
                    .then()
                    .statusCode(400);
        }

/*        @Test
        void when_id_correspond_to_one_media_should_return_concerned_media() {
            MediaEntity filmMedia = MediaEntity.builder().name("film").build();
            MediaEntity seriesMedia = MediaEntity.builder().name("series").build();
            MediaEntity mangaMedia = MediaEntity.builder().name("manga").build();
            var mediaEntityList = Arrays.asList(filmMedia, seriesMedia, mangaMedia);
            var savedMediaEntityList = mediaRepository.saveAll(mediaEntityList);

            var expectMedia = MediaMapper.entityToDomain(savedMediaEntityList.get(1));
            var response = given()
                    .when()
                    .get("/api/media/" + expectMedia.getId())
                    .then()
                    .statusCode(200)
                    .extract()
                    .as(Media.class);

            assertThat(response).isEqualTo(expectMedia);
        }*/
    }
}
