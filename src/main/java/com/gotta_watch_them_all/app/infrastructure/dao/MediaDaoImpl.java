package com.gotta_watch_them_all.app.infrastructure.dao;

import com.gotta_watch_them_all.app.core.dao.MediaDao;
import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.mapper.MediaMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaDaoImpl implements MediaDao {
    private final MediaRepository mediaRepository;

    @Override
    public List<Media> findAll() {
        var mediaEntityList = mediaRepository.findAll();
        return mediaEntityList.stream()
                .map(MediaMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Media findById(Long mediaId) {
        return mediaRepository.findById(mediaId)
                .map(MediaMapper::entityToDomain)
                .orElse(null);
    }

    @Override
    public Media findByName(String name) {
        return Optional.ofNullable(mediaRepository.findOneByName(name))
                .map(MediaMapper::entityToDomain)
                .orElse(null);
    }

    @Override
    public Long createMedia(String name) {
        MediaEntity newMedia = MediaEntity.builder().name(name).build();
        return mediaRepository.save(newMedia).getId();
    }
}
