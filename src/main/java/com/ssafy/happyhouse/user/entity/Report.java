package com.ssafy.happyhouse.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "REPORT")
public class Report {
    @Id
    @Column(name = "REPORT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(name = "DESCRIPTION")
    private String description;

    //User 당 5개 밖에 되지 않기 때문에 Eager
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

}
