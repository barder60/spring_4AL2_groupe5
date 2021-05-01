package com.gotta_watch_them_all.app.infrastructure.dao;

import com.gotta_watch_them_all.app.core.dao.WorkDao;
import com.gotta_watch_them_all.app.core.entity.Work;
import com.gotta_watch_them_all.app.core.exception.AnySearchValueFoundException;
import com.gotta_watch_them_all.app.core.exception.BadHttpRequestException;
import com.gotta_watch_them_all.app.core.exception.IllegalTitleGivenException;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.WorkMovieDbApiEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper.WorkMovieDbApiMapper;
import com.gotta_watch_them_all.app.infrastructure.util.ApiRequestBuilder;
import com.gotta_watch_them_all.app.infrastructure.util.ApiRequester;
import com.gotta_watch_them_all.app.infrastructure.util.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.net.http.HttpRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkDaoMovieDbApi implements WorkDao {

    private final ApiRequestBuilder apiRequestBuilder;
    private final ApiRequester apiRequester;
    private final JsonParser jsonParser;
    private final WorkMovieDbApiMapper mapper;

    @Override
    public Set<Work> findAllByTitle(String title) {
        try {
            HttpRequest request = apiRequestBuilder
                    .setTitleToSearch(title)
                    .build();
            String jsonRaw = apiRequester.request(request);
            List<WorkMovieDbApiEntity> workEntities = jsonParser.toObjectList(jsonRaw, WorkMovieDbApiEntity.class);
            return workEntities
                    .stream()
                    .map(mapper::toDomain)
                    .collect(Collectors.toSet());
        } catch (AnySearchValueFoundException | IllegalTitleGivenException | BadHttpRequestException e) {
            e.printStackTrace();
            return null;
        }
    }


}
