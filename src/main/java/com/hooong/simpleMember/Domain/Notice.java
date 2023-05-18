package com.hooong.simpleMember.Domain;

import lombok.Data;

@Data
public class Notice {

    private int id;
    private String title;
    private String texts;

    private String writer;
    private String password;

    private String updateDate;

}