package com.drinktea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id; // 主键
    private String openid; // wechat的唯一识别
    private String name;
    private String phone;
    private String sex;
    private String idNumber; // 身份证号
    private String avatar; // 头像
    private LocalDateTime createTime;
}
