package com.ssafy.happyhouse.realty.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRealtyPicture is a Querydsl query type for RealtyPicture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRealtyPicture extends EntityPathBase<RealtyPicture> {

    private static final long serialVersionUID = -875295852L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRealtyPicture realtyPicture = new QRealtyPicture("realtyPicture");

    public final StringPath location = createString("location");

    public final NumberPath<Long> pictureId = createNumber("pictureId", Long.class);

    public final QRealty realty;

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public QRealtyPicture(String variable) {
        this(RealtyPicture.class, forVariable(variable), INITS);
    }

    public QRealtyPicture(Path<? extends RealtyPicture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRealtyPicture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRealtyPicture(PathMetadata metadata, PathInits inits) {
        this(RealtyPicture.class, metadata, inits);
    }

    public QRealtyPicture(Class<? extends RealtyPicture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.realty = inits.isInitialized("realty") ? new QRealty(forProperty("realty"), inits.get("realty")) : null;
    }

}

