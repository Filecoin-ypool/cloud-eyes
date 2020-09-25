package com.eyes.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 网页用户表
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private Long phone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 交易密码
     */
    private String tradePassword;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 左节点
     */
    private Integer lft;

    /**
     * 右节点
     */
    private Integer rgt;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 邀请码
     */
    private String invite;

    /**
     * 升级到生态节点前的父节点
     */
    private Integer promotion;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 托管收益比例
     */
    private BigDecimal depositRatio;


}
