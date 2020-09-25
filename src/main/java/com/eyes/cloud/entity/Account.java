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
 * 
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 币种 cny,file
     */
    private String currency;

    /**
     * 可用数量
     */
    private BigDecimal amount;

    /**
     * 冻结数量
     */
    private BigDecimal freezeAmount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态0正常，异常
     */
    private Boolean status;


}
