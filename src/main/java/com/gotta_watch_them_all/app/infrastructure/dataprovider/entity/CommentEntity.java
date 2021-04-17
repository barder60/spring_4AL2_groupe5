package com.gotta_watch_them_all.app.infrastructure.dataprovider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "comment")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String content;

    @Column
    private Boolean vulgar;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "work_id")
    private Long workId;
}
