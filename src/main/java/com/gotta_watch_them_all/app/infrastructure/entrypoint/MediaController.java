package com.gotta_watch_them_all.app.infrastructure.entrypoint;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.exception.NotFoundException;
import com.gotta_watch_them_all.app.usecase.media.FindAllMedias;
import com.gotta_watch_them_all.app.usecase.media.FindOneMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/media")
@Validated
@RequiredArgsConstructor
public class MediaController {
    private final FindAllMedias findAllMedias;
    private final FindOneMedia findOneMedia;

    @GetMapping
    public ResponseEntity<List<Media>> findAll() {
        return ok(findAllMedias.execute());
    }

    @GetMapping("{id}")
    public ResponseEntity<Media> findById(
            @PathVariable("id")
            @Min(value = 1, message = "id has to be more than 1") Long mediaId
    ) throws NotFoundException {
        return ok(findOneMedia.execute(mediaId));
    }
}
