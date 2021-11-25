package com.ssafy.happyhouse.district.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGugun is a Querydsl query type for Gugun
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGugun extends EntityPathBase<Gugun> {

    private static final long serialVersionUID = 1745644544L;

    public static final QGugun gugun = new QGugun("gugun");

    public final StringPath gugunCode = createString("gugunCode");

    public final NumberPath<Long> gugunId = createNumber("gugunId", Long.class);

    public final StringPath gugunName = createString("gugunName");

    public QGugun(String variable) {
        super(Gugun.class, forVariable(variable));
    }

    public QGugun(Path<? extends Gugun> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGugun(PathMetadata metadata) {
        super(Gugun.class, metadata);
    }

}

