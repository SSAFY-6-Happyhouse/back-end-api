package com.ssafy.happyhouse.spot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSpot is a Querydsl query type for Spot
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpot extends EntityPathBase<Spot> {

    private static final long serialVersionUID = 1503264712L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpot spot = new QSpot("spot");

    public final StringPath address = createString("address");

    public final StringPath categoryCode = createString("categoryCode");

    public final StringPath categoryName = createString("categoryName");

    public final com.ssafy.happyhouse.district.entity.QDong dong;

    public final NumberPath<Long> spotId = createNumber("spotId", Long.class);

    public final StringPath spotName = createString("spotName");

    public final ComparablePath<org.locationtech.jts.geom.Point> spotPoint = createComparable("spotPoint", org.locationtech.jts.geom.Point.class);

    public final NumberPath<Double> x = createNumber("x", Double.class);

    public final NumberPath<Double> y = createNumber("y", Double.class);

    public QSpot(String variable) {
        this(Spot.class, forVariable(variable), INITS);
    }

    public QSpot(Path<? extends Spot> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSpot(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSpot(PathMetadata metadata, PathInits inits) {
        this(Spot.class, metadata, inits);
    }

    public QSpot(Class<? extends Spot> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dong = inits.isInitialized("dong") ? new com.ssafy.happyhouse.district.entity.QDong(forProperty("dong")) : null;
    }

}

