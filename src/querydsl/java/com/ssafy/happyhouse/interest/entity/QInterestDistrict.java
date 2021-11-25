package com.ssafy.happyhouse.interest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestDistrict is a Querydsl query type for InterestDistrict
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestDistrict extends EntityPathBase<InterestDistrict> {

    private static final long serialVersionUID = -447746746L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestDistrict interestDistrict = new QInterestDistrict("interestDistrict");

    public final com.ssafy.happyhouse.district.entity.QDong dong;

    public final NumberPath<Long> interestDistrictId = createNumber("interestDistrictId", Long.class);

    public final com.ssafy.happyhouse.user.entity.QUser user;

    public QInterestDistrict(String variable) {
        this(InterestDistrict.class, forVariable(variable), INITS);
    }

    public QInterestDistrict(Path<? extends InterestDistrict> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestDistrict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestDistrict(PathMetadata metadata, PathInits inits) {
        this(InterestDistrict.class, metadata, inits);
    }

    public QInterestDistrict(Class<? extends InterestDistrict> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dong = inits.isInitialized("dong") ? new com.ssafy.happyhouse.district.entity.QDong(forProperty("dong")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.happyhouse.user.entity.QUser(forProperty("user")) : null;
    }

}

