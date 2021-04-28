package com.gotta_watch_them_all.app.usecase.work;

import com.gotta_watch_them_all.app.core.dao.WorkDao;
import com.gotta_watch_them_all.app.core.entity.Work;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FindWorkByTitleFromApi {

    private final WorkDao workDao;

    public Set<Work> execute(String title) {
        return null;
    }
}
