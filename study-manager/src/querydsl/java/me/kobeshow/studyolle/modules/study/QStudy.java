package me.kobeshow.studyolle.modules.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = 1276716826L;

    public static final QStudy study = new QStudy("study");

    public final BooleanPath closed = createBoolean("closed");

    public final DateTimePath<java.time.LocalDateTime> closedDateTime = createDateTime("closedDateTime", java.time.LocalDateTime.class);

    public final StringPath fullDescription = createString("fullDescription");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final SetPath<me.kobeshow.studyolle.modules.account.Account, me.kobeshow.studyolle.modules.account.QAccount> managers = this.<me.kobeshow.studyolle.modules.account.Account, me.kobeshow.studyolle.modules.account.QAccount>createSet("managers", me.kobeshow.studyolle.modules.account.Account.class, me.kobeshow.studyolle.modules.account.QAccount.class, PathInits.DIRECT2);

    public final SetPath<me.kobeshow.studyolle.modules.account.Account, me.kobeshow.studyolle.modules.account.QAccount> members = this.<me.kobeshow.studyolle.modules.account.Account, me.kobeshow.studyolle.modules.account.QAccount>createSet("members", me.kobeshow.studyolle.modules.account.Account.class, me.kobeshow.studyolle.modules.account.QAccount.class, PathInits.DIRECT2);

    public final StringPath path = createString("path");

    public final BooleanPath published = createBoolean("published");

    public final DateTimePath<java.time.LocalDateTime> publishedDateTime = createDateTime("publishedDateTime", java.time.LocalDateTime.class);

    public final BooleanPath recruiting = createBoolean("recruiting");

    public final DateTimePath<java.time.LocalDateTime> recruitingUpdatedDateTime = createDateTime("recruitingUpdatedDateTime", java.time.LocalDateTime.class);

    public final StringPath shortDescription = createString("shortDescription");

    public final SetPath<me.kobeshow.studyolle.modules.tag.Tag, me.kobeshow.studyolle.modules.tag.QTag> tags = this.<me.kobeshow.studyolle.modules.tag.Tag, me.kobeshow.studyolle.modules.tag.QTag>createSet("tags", me.kobeshow.studyolle.modules.tag.Tag.class, me.kobeshow.studyolle.modules.tag.QTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final BooleanPath useBanner = createBoolean("useBanner");

    public final SetPath<me.kobeshow.studyolle.modules.zone.Zone, me.kobeshow.studyolle.modules.zone.QZone> zones = this.<me.kobeshow.studyolle.modules.zone.Zone, me.kobeshow.studyolle.modules.zone.QZone>createSet("zones", me.kobeshow.studyolle.modules.zone.Zone.class, me.kobeshow.studyolle.modules.zone.QZone.class, PathInits.DIRECT2);

    public QStudy(String variable) {
        super(Study.class, forVariable(variable));
    }

    public QStudy(Path<? extends Study> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudy(PathMetadata metadata) {
        super(Study.class, metadata);
    }

}

