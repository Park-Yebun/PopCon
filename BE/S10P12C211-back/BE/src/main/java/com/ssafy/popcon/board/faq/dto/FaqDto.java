package com.ssafy.popcon.board.faq.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FaqDto {

    private int faqId;
    private String faqName;
    private String faqContent;

}
