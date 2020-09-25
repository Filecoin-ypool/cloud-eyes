package com.eyes.cloud.dto.inDto.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @version V3.0
 * @Title: FUserAddIn
 * @Description: 描述
 * @author: Trident
 * @date 2020/3/25 上午11:40
 */
@Data
public class FUserAddIn {
    private String PInvite; //父亲邀请码
    private Long phone;     //手机号
    private String password;     //密码
    private LocalDateTime createAt; //创建时间
    private Integer level = 0;   //等级,默认为最低级0
    private Integer resultId;
}
