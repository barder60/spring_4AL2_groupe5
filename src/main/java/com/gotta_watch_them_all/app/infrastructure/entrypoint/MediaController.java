package com.gotta_watch_them_all.app.infrastructure.entrypoint;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.core.exception.AlreadyCreatedException;
import com.gotta_watch_them_all.app.core.exception.NotFoundException;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.request.CreateMediaRequest;
import com.gotta_watch_them_all.app.usecase.media.AddMedia;
import com.gotta_watch_them_all.app.usecase.media.FindAllMedias;
import com.gotta_watch_them_all.app.usecase.media.FindOneMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/media")
@Validated
@RequiredArgsConstructor
public class MediaController {
    private final FindAllMedias findAllMedias;
    private final FindOneMedia findOneMedia;
    private final AddMedia addMedia;

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

    @PostMapping
    public ResponseEntity<URI> createMedia(@Valid @RequestBody CreateMediaRequest request) throws AlreadyCreatedException {
        var newMediaId = addMedia.execute(request.getName());
        var uid = ServletUriComponentsBuilder.fromCurrentRequest() // /api/users
                .path("/{id}")
                .buildAndExpand(newMediaId)
                .toUri();
        return created(uid).build();
    }
}
