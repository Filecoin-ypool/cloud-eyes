package com.eyes.cloud.controller;


import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.config.CommonValue;
import com.eyes.cloud.interceptor.Common;
import com.eyes.cloud.interceptor.UserLoginToken;
import com.eyes.cloud.service.IStorageDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@RestController
@RequestMapping(CommonValue.API_PREFIX + "/storage_deal")
@UserLoginToken
public class StorageDealController {
    @Autowired
    IStorageDealService storageDealService;

    /**
     * 获取日期分类列表
     *
     * @param request
     * @return
     */
    @GetMapping("/day_List")
    Result getDayList(HttpServletRequest request) {
        int uid = (int) request.getAttribute(Common.USER_ID);
        return storageDealService.getDayList(uid);
    }

    /**
     * 获取某日期下的内容列表
     *
     * @param request
     * @param day
     * @return
     */
    @GetMapping("/list/{day}")
    Result getList(HttpServletRequest request, @PathVariable String day) {
        int uid = (int) request.getAttribute(Common.USER_ID);
        return storageDealService.getList(uid, day);
    }

    /**
     * 获取详细信息
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result getById(HttpServletRequest request, @PathVariable String id) {
        int uid = (int) request.getAttribute(Common.USER_ID);
        return storageDealService.getById(uid, id);
    }


}
