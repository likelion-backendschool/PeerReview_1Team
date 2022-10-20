package com.yejin.exam.wbook.domain.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostHashTag is a Querydsl query type for PostHashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostHashTag extends EntityPathBase<PostHashTag> {

    private static final long serialVersionUID = 1088381002L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostHashTag postHashTag = new QPostHashTag("postHashTag");

    public final com.yejin.exam.wbook.global.base.entity.QBaseEntity _super = new com.yejin.exam.wbook.global.base.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QPostKeyword keyword;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final QPost post;

    public QPostHashTag(String variable) {
        this(PostHashTag.class, forVariable(variable), INITS);
    }

    public QPostHashTag(Path<? extends PostHashTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostHashTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostHashTag(PathMetadata metadata, PathInits inits) {
        this(PostHashTag.class, metadata, inits);
    }

    public QPostHashTag(Class<? extends PostHashTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.keyword = inits.isInitialized("keyword") ? new QPostKeyword(forProperty("keyword")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

