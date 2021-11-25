package com.ssafy.happyhouse.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 2057371610L;

    public static final QUser user = new QUser("user");

    public final SetPath<Authority, EnumPath<Authority>> authority = this.<Authority, EnumPath<Authority>>createSet("authority", Authority.class, EnumPath.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.happyhouse.enquiry.entity.Enquiry, com.ssafy.happyhouse.enquiry.entity.QEnquiry> enquiries = this.<com.ssafy.happyhouse.enquiry.entity.Enquiry, com.ssafy.happyhouse.enquiry.entity.QEnquiry>createList("enquiries", com.ssafy.happyhouse.enquiry.entity.Enquiry.class, com.ssafy.happyhouse.enquiry.entity.QEnquiry.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.happyhouse.interest.entity.InterestDistrict, com.ssafy.happyhouse.interest.entity.QInterestDistrict> interestDistricts = this.<com.ssafy.happyhouse.interest.entity.InterestDistrict, com.ssafy.happyhouse.interest.entity.QInterestDistrict>createList("interestDistricts", com.ssafy.happyhouse.interest.entity.InterestDistrict.class, com.ssafy.happyhouse.interest.entity.QInterestDistrict.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.happyhouse.interest.entity.InterestRealty, com.ssafy.happyhouse.interest.entity.QInterestRealty> interestRealties = this.<com.ssafy.happyhouse.interest.entity.InterestRealty, com.ssafy.happyhouse.interest.entity.QInterestRealty>createList("interestRealties", com.ssafy.happyhouse.interest.entity.InterestRealty.class, com.ssafy.happyhouse.interest.entity.QInterestRealty.class, PathInits.DIRECT2);

    public final BooleanPath isDisabled = createBoolean("isDisabled");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<com.ssafy.happyhouse.realty.entity.Realty, com.ssafy.happyhouse.realty.entity.QRealty> realties = this.<com.ssafy.happyhouse.realty.entity.Realty, com.ssafy.happyhouse.realty.entity.QRealty>createList("realties", com.ssafy.happyhouse.realty.entity.Realty.class, com.ssafy.happyhouse.realty.entity.QRealty.class, PathInits.DIRECT2);

    public final ListPath<Report, QReport> reports = this.<Report, QReport>createList("reports", Report.class, QReport.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.happyhouse.spot.entity.Segwon, EnumPath<com.ssafy.happyhouse.spot.entity.Segwon>> segwons = this.<com.ssafy.happyhouse.spot.entity.Segwon, EnumPath<com.ssafy.happyhouse.spot.entity.Segwon>>createList("segwons", com.ssafy.happyhouse.spot.entity.Segwon.class, EnumPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

