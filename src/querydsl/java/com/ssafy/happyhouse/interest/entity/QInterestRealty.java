package com.ssafy.happyhouse.interest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestRealty is a Querydsl query type for InterestRealty
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestRealty extends EntityPathBase<InterestRealty> {

    private static final long serialVersionUID = -810598309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestRealty interestRealty = new QInterestRealty("interestRealty");

    public final NumberPath<Long> interestRealtyId = createNumber("interestRealtyId", Long.class);

    public final com.ssafy.happyhouse.realty.entity.QRealty realty;

    public final com.ssafy.happyhouse.user.entity.QUser user;

    public QInterestRealty(String variable) {
        this(InterestRealty.class, forVariable(variable), INITS);
    }

    public QInterestRealty(Path<? extends InterestRealty> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestRealty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestRealty(PathMetadata metadata, PathInits inits) {
        this(InterestRealty.class, metadata, inits);
    }

    public QInterestRealty(Class<? extends InterestRealty> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.realty = inits.isInitialized("realty") ? new com.ssafy.happyhouse.realty.entity.QRealty(forProperty("realty"), inits.get("realty")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.happyhouse.user.entity.QUser(forProperty("user")) : null;
    }

}

