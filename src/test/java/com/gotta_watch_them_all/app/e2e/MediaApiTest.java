package com.gotta_watch_them_all.app.e2e;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper.MediaMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.MediaRepository;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.request.CreateMediaRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MediaApiTest {

    @Autowired
    private MediaRepository mediaRepository;

    @LocalServerPort
    private int localPort;

    @BeforeEach
    void setup() {
        port = localPort;
    }

    @DisplayName("METHOD GET /media")
    @Nested
    class FindAllMedias {
        @Test
        void should_get_all_medias() {
            mediaRepository.deleteAll();
            MediaEntity filmMedia = new MediaEntity().setName("film");
            MediaEntity seriesMedia = new MediaEntity().setName("series");
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
        @Test
        void when_id_correspond_to_one_media_should_return_concerned_media() {
            MediaEntity filmMedia = new MediaEntity().setName("film");
            MediaEntity seriesMedia = new MediaEntity().setName("series");
            MediaEntity mangaMedia = new MediaEntity().setName("manga");
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
        }
    }

    @DisplayName("METHOD POST /media")
    @Nested
    class CreateMedia {
        // TODO : check if auth user is admin

        @Test
        public void when_name_is_correct_should_create_media_and_return_uri() {
            CreateMediaRequest request = new CreateMediaRequest();
            request.setName("series");
            var response = given()
                    .contentType(ContentType.JSON)
                    .body(request)
                    .when()
                    .post("/api/media")
                    .then()
                    .statusCode(201)
                    .extract()
                    .header("Location");
            var checkCreatedMedia = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(response)
                    .then()
                    .statusCode(200)
                    .extract()
                    .as(Media.class);
            assertThat(checkCreatedMedia.getId()).isNotNull().isInstanceOf(Long.class);
            assertThat(checkCreatedMedia.getName()).isEqualTo(request.getName());
        }

        @Test
        public void when_name_already_exist_should_send_error_response() {
            mediaRepository.save(new MediaEntity().setName("series2"));
            CreateMediaRequest request = new CreateMediaRequest();
            request.setName("series2");

            var response = given()
                    .contentType(ContentType.JSON)
                    .body(request)
                    .when()
                    .post("/api/media")
                    .then()
                    .statusCode(403)
                    .extract()
                    .asString();
            assertThat(response).isEqualTo("Media with name 'series2' is already created");
        }
    }

    @DisplayName("METHOD DELETE /media/{id}")
    @Nested
    class DeleteMedia {
        @Test
        void when_media_with_certain_id_found_should_delete_concerned_media() {
            var mediaToSave = new MediaEntity()
                    .setName("film")
                    ;
            var mediaToDelete = mediaRepository.save(mediaToSave);

            when()
                    .delete("/api/media/" + mediaToDelete.getId())
                    .then()
                    .statusCode(204);

            var maybeMedia = mediaRepository.findById(mediaToDelete.getId());

            assertThat(maybeMedia.isPresent()).isFalse();
        }
    }
}
