package com.gotta_watch_them_all.app.infrastructure.entrypoint;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.usecase.media.FindAllMedias;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/media")
@RequiredArgsConstructor
public class MediaController {
    private final FindAllMedias findAllMedias;

    @GetMapping
    public ResponseEntity<List<Media>> findAll() {
        return ResponseEntity.ok(findAllMedias.execute());
    }
}
