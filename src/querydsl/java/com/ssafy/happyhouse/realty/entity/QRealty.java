package com.ssafy.happyhouse.realty.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRealty is a Querydsl query type for Realty
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRealty extends EntityPathBase<Realty> {

    private static final long serialVersionUID = 984967690L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRealty realty = new QRealty("realty");

    public final StringPath address = createString("address");

    public final DateTimePath<java.time.LocalDateTime> availableDate = createDateTime("availableDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> bathrooms = createNumber("bathrooms", Integer.class);

    public final TimePath<java.time.LocalTime> contactTime = createTime("contactTime", java.time.LocalTime.class);

    public final EnumPath<ContractProcess> contractProcess = createEnum("contractProcess", ContractProcess.class);

    public final EnumPath<ContractType> contractType = createEnum("contractType", ContractType.class);

    public final StringPath description = createString("description");

    public final com.ssafy.happyhouse.district.entity.QDong dong;

    public final NumberPath<Integer> elevators = createNumber("elevators", Integer.class);

    public final ListPath<com.ssafy.happyhouse.enquiry.entity.Enquiry, com.ssafy.happyhouse.enquiry.entity.QEnquiry> enquiries = this.<com.ssafy.happyhouse.enquiry.entity.Enquiry, com.ssafy.happyhouse.enquiry.entity.QEnquiry>createList("enquiries", com.ssafy.happyhouse.enquiry.entity.Enquiry.class, com.ssafy.happyhouse.enquiry.entity.QEnquiry.class, PathInits.DIRECT2);

    public final StringPath heat = createString("heat");

    public final NumberPath<Long> hitCount = createNumber("hitCount", Long.class);

    public final ListPath<com.ssafy.happyhouse.interest.entity.InterestRealty, com.ssafy.happyhouse.interest.entity.QInterestRealty> interestRealties = this.<com.ssafy.happyhouse.interest.entity.InterestRealty, com.ssafy.happyhouse.interest.entity.QInterestRealty>createList("interestRealties", com.ssafy.happyhouse.interest.entity.InterestRealty.class, com.ssafy.happyhouse.interest.entity.QInterestRealty.class, PathInits.DIRECT2);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final ListPath<Option, EnumPath<Option>> options = this.<Option, EnumPath<Option>>createList("options", Option.class, EnumPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> realtyId = createNumber("realtyId", Long.class);

    public final ListPath<RealtyPicture, QRealtyPicture> realtyPictures = this.<RealtyPicture, QRealtyPicture>createList("realtyPictures", RealtyPicture.class, QRealtyPicture.class, PathInits.DIRECT2);

    public final EnumPath<RealtyType> realtyType = createEnum("realtyType", RealtyType.class);

    public final DateTimePath<java.time.LocalDateTime> registerDate = createDateTime("registerDate", java.time.LocalDateTime.class);

    public final com.ssafy.happyhouse.user.entity.QUser registerer;

    public final NumberPath<Integer> rooms = createNumber("rooms", Integer.class);

    public final ListPath<com.ssafy.happyhouse.spot.entity.Segwon, EnumPath<com.ssafy.happyhouse.spot.entity.Segwon>> segwons = this.<com.ssafy.happyhouse.spot.entity.Segwon, EnumPath<com.ssafy.happyhouse.spot.entity.Segwon>>createList("segwons", com.ssafy.happyhouse.spot.entity.Segwon.class, EnumPath.class, PathInits.DIRECT2);

    public final NumberPath<Double> size = createNumber("size", Double.class);

    public QRealty(String variable) {
        this(Realty.class, forVariable(variable), INITS);
    }

    public QRealty(Path<? extends Realty> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRealty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRealty(PathMetadata metadata, PathInits inits) {
        this(Realty.class, metadata, inits);
    }

    public QRealty(Class<? extends Realty> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dong = inits.isInitialized("dong") ? new com.ssafy.happyhouse.district.entity.QDong(forProperty("dong")) : null;
        this.registerer = inits.isInitialized("registerer") ? new com.ssafy.happyhouse.user.entity.QUser(forProperty("registerer")) : null;
    }

}

