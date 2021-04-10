package com.gotta_watch_them_all.app.infrastructure.dataprovider.repository;

import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
    MediaEntity findOneByName(String name);
}
