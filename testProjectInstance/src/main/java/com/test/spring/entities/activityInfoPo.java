package com.test.spring.entities;

import javax.persistence.*;

@Table(name = "activity_info")
@Entity
public class activityInfoPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


}
