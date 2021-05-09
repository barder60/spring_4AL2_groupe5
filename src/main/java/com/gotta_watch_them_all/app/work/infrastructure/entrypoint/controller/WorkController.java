package com.gotta_watch_them_all.app.work.infrastructure.entrypoint.controller;

import com.gotta_watch_them_all.app.work.core.exception.IllegalTitleGivenException;
import com.gotta_watch_them_all.app.core.exception.NotFoundException;
import com.gotta_watch_them_all.app.work.infrastructure.entrypoint.response.WorkResponse;
import com.gotta_watch_them_all.app.work.infrastructure.entrypoint.adapter.WorkAdapter;
import com.gotta_watch_them_all.app.work.usecase.FindWorkByTitleFromApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/works")
public class WorkController {

    private final FindWorkByTitleFromApi findWorkByTitleFromApi;

    @GetMapping("/moviedb/{title}")
    public ResponseEntity<Set<WorkResponse>> findByTitle(
            @PathVariable("title") String title
    ) throws NotFoundException, IllegalTitleGivenException {
        var works = findWorkByTitleFromApi.execute(title);
        return ok(WorkAdapter.domainToResponseSet(works));
    }


}
