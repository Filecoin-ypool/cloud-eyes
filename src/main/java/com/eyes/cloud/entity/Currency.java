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
 * 币种表
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 币种
     */
    private String currency;

    /**
     * 最小提币数量
     */
    private BigDecimal miniWithdrawQuantity;

    /**
     * 最小充值数量
     */
    private BigDecimal miniInvestQuantity;

    /**
     * 提现手续费
     */
    private BigDecimal withdrawFee;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态 -1删除 0正常
     */
    private Integer status;

    /**
     * 充值地址
     */
    private String investAddress;


}
