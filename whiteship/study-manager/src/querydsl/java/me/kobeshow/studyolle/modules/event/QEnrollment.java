package me.kobeshow.studyolle.modules.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnrollment is a Querydsl query type for Enrollment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEnrollment extends EntityPathBase<Enrollment> {

    private static final long serialVersionUID = -1476160798L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnrollment enrollment = new QEnrollment("enrollment");

    public final BooleanPath accepted = createBoolean("accepted");

    public final me.kobeshow.studyolle.modules.account.QAccount account;

    public final BooleanPath attended = createBoolean("attended");

    public final DateTimePath<java.time.LocalDateTime> enrolledAt = createDateTime("enrolledAt", java.time.LocalDateTime.class);

    public final QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEnrollment(String variable) {
        this(Enrollment.class, forVariable(variable), INITS);
    }

    public QEnrollment(Path<? extends Enrollment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnrollment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnrollment(PathMetadata metadata, PathInits inits) {
        this(Enrollment.class, metadata, inits);
    }

    public QEnrollment(Class<? extends Enrollment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new me.kobeshow.studyolle.modules.account.QAccount(forProperty("account")) : null;
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event"), inits.get("event")) : null;
    }

}

