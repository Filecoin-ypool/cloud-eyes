package com.eyes.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 短信模版表
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FSms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 短信模版编号
     */
    private String templateCode;

    /**
     * 模版类型，用于指定短信用途
     */
    private String templateType;

    /**
     * 模版需要的自定义参数，以逗号隔开
     */
    private String templateParam;

    /**
     * 短信平台，Aliyun，wx
     */
    private String platform;

    /**
     * 短信发送次数
     */
    private Integer num;

    /**
     * 分钟频率
     */
    private Integer minutes;

    /**
     * 小时频率
     */
    private Integer hour;

    /**
     * 日频率
     */
    private Integer day;


}
