package com.gotta_watch_them_all.app.unit.infrastructure.dao;

import com.gotta_watch_them_all.app.infrastructure.dao.MediaDaoImpl;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MediaDaoImplTest {

    @Mock
    private MediaRepository mockMediaRepository;

    private MediaDaoImpl sut;

    @BeforeEach
    void setUp() {
        sut = new MediaDaoImpl(mockMediaRepository);
    }
}