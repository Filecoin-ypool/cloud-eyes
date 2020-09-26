package com.eyes.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.entity.StorageDeal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
public interface IStorageDealService extends IService<StorageDeal> {
    /**
     * 获取日期分类列表
     * @param uid
     * @return
     */
    Result getDayList(Integer uid);

    /**
     * 获取某日期下的内容列表
     * @param uid
     * @param day
     * @return
     */
    Result getList(Integer uid,String day);

    /**
     * 详情获取
     * @param uid
     * @param id
     * @return
     */
    Result getById(Integer uid,String id);

    /**
     * 上传文件
     * @param file
     * @return
     */
    Result upload(MultipartFile file,Integer uid) throws IOException;
}
