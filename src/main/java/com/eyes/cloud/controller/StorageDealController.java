package com.eyes.cloud.controller;


import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.config.CommonValue;
import com.eyes.cloud.dto.inDto.storageDeal.UploadDto;
import com.eyes.cloud.interceptor.Common;
import com.eyes.cloud.interceptor.UserLoginToken;
import com.eyes.cloud.service.IStorageDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

    /**
     * 根据id获取文件
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/get_file")
    void getFileById(HttpServletResponse response, HttpServletRequest request, @RequestParam String id) throws IOException {
        int uid = (int) request.getAttribute(Common.USER_ID);
        String filePath = storageDealService.getFileById(uid, id);
//        String filePath="C:\\Users\\Me\\Desktop\\work\\ssss.mp4";

        response.setHeader("Content-Disposition", "attachment;filename=" + "ssss" + ".mpr4");
        // 响应类型,编码
        response.setContentType("application/octet-stream;charset=UTF-8");
        // 形成输出流
        OutputStream osOut = response.getOutputStream();
        File file = new File(filePath);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                osOut.write(buf, 0, bytesRead);
            }
        } finally {
            assert input != null;
            input.close();
            osOut.close();
        }
//        return Result.ok();
    }

    /**
     * 文件上传至fil
     *
     * @param request
     * @param dto
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    Result upload(HttpServletRequest request, UploadDto dto) throws IOException {
        int uid = (int) request.getAttribute(Common.USER_ID);
        return storageDealService.upload(dto, uid);
    }

    @GetMapping("/statistics")
    Result statistics(HttpServletRequest request){
        int uid = (int) request.getAttribute(Common.USER_ID);
        return storageDealService.statistics(uid);
    }
}
