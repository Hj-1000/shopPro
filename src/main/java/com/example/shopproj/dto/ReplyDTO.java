package com.example.shopproj.dto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO{


    private Long rno;

    private Long bno;

    private String replyText;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    private ItemDTO itemDTO;

    private MemberDTO memberDTO;

    private String writerEmail;

    public ReplyDTO setMemberDTO(MemberDTO memberDTO){
        this.memberDTO = memberDTO;
        return this;
    }

    public ReplyDTO setItemDTO(ItemDTO itemDTO){
        this.itemDTO = itemDTO;
        return this;
    }

    public ReplyDTO setWriterEmail(String email){
        this.writerEmail = writerEmail;
        return this;
    }




}
