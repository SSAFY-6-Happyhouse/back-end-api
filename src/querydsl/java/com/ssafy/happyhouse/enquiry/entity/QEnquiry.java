package com.ssafy.happyhouse.enquiry.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnquiry is a Querydsl query type for Enquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEnquiry extends EntityPathBase<Enquiry> {

    private static final long serialVersionUID = -990645430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnquiry enquiry = new QEnquiry("enquiry");

    public final NumberPath<Long> enquiryId = createNumber("enquiryId", Long.class);

    public final com.ssafy.happyhouse.realty.entity.QRealty realty;

    public final StringPath realtyProviderPhone = createString("realtyProviderPhone");

    public final com.ssafy.happyhouse.user.entity.QUser user;

    public QEnquiry(String variable) {
        this(Enquiry.class, forVariable(variable), INITS);
    }

    public QEnquiry(Path<? extends Enquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnquiry(PathMetadata metadata, PathInits inits) {
        this(Enquiry.class, metadata, inits);
    }

    public QEnquiry(Class<? extends Enquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.realty = inits.isInitialized("realty") ? new com.ssafy.happyhouse.realty.entity.QRealty(forProperty("realty"), inits.get("realty")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.happyhouse.user.entity.QUser(forProperty("user")) : null;
    }

}

