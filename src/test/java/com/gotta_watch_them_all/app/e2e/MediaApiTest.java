package com.gotta_watch_them_all.app.e2e;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.MediaRepository;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

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
        @BeforeEach
        void setup() {
            mediaRepository.save(MediaEntity.builder().name("film").build());
            mediaRepository.save(MediaEntity.builder().name("series").build());
        }

        @DisplayName("should get all medias")
        @Test
        void shouldGetAll() {
            List<Media> response = given()
                    .when()
                    .get("/api/media")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath()
                    .getList(".", Media.class);
            assertThat(response).isNotNull();
            assertThat(response.size()).isEqualTo(2);
        }
    }
}
