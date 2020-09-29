package com.eyes.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
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
public class StorageDeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * import返回的cid
     */
    private String cid;

    /**
     * 发起交易返回的cid
     */
    private String dealCid;

    /**
     * 查询和检索时需要使用的id
     */
    private String pieceCid;

    /**
     * 交易id
     */
    private String dealId;

    /**
     * 支付钱包地址
     */
    private String wallet;

    /**
     * 存储矿工编号
     */
    private String miner;

    /**
     * 支付价格(单位是位/时间段)
     */
    private String epochPrice;

    /**
     * 最小存储时间段
     */
    private String minBlocksDuration;

    /**
     * 存储时间(指成功传输到服务器)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /**
     * 文件名(文件会根据存储时间存储到不同的文件夹)
     */
    private String fileName;

    private Long fileSize;

    /**
     * 存储交易状态
     */
    private String storageDealStatus;

    /**
     * 内部数据状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 错误信息
     */
    private String message;
}
