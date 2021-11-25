package com.ssafy.happyhouse.district.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDong is a Querydsl query type for Dong
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDong extends EntityPathBase<Dong> {

    private static final long serialVersionUID = -775067818L;

    public static final QDong dong = new QDong("dong");

    public final StringPath dongCode = createString("dongCode");

    public final NumberPath<Long> dongId = createNumber("dongId", Long.class);

    public final StringPath dongName = createString("dongName");

    public final StringPath gugunName = createString("gugunName");

    public final ListPath<com.ssafy.happyhouse.realty.entity.Realty, com.ssafy.happyhouse.realty.entity.QRealty> realties = this.<com.ssafy.happyhouse.realty.entity.Realty, com.ssafy.happyhouse.realty.entity.QRealty>createList("realties", com.ssafy.happyhouse.realty.entity.Realty.class, com.ssafy.happyhouse.realty.entity.QRealty.class, PathInits.DIRECT2);

    public final StringPath sidoName = createString("sidoName");

    public final ListPath<com.ssafy.happyhouse.spot.entity.Spot, com.ssafy.happyhouse.spot.entity.QSpot> spots = this.<com.ssafy.happyhouse.spot.entity.Spot, com.ssafy.happyhouse.spot.entity.QSpot>createList("spots", com.ssafy.happyhouse.spot.entity.Spot.class, com.ssafy.happyhouse.spot.entity.QSpot.class, PathInits.DIRECT2);

    public QDong(String variable) {
        super(Dong.class, forVariable(variable));
    }

    public QDong(Path<? extends Dong> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDong(PathMetadata metadata) {
        super(Dong.class, metadata);
    }

}

