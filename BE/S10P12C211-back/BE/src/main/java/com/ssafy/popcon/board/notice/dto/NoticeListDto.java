package com.ssafy.popcon.board.notice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeListDto {

    private int noticeId;
    private String noticeName;
    private String noticeDate;
}
