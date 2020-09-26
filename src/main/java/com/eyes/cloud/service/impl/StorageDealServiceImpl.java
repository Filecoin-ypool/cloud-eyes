package com.eyes.cloud.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.config.MinerValues;
import com.eyes.cloud.dto.outDto.storageDeal.StorageDealDayList;
import com.eyes.cloud.dto.outDto.storageDeal.StorageDealOutDto;
import com.eyes.cloud.entity.Local;
import com.eyes.cloud.entity.StorageDeal;
import com.eyes.cloud.exception.BusinessException;
import com.eyes.cloud.mapper.StorageDealMapper;
import com.eyes.cloud.service.IStorageDealService;
import com.eyes.cloud.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Service
public class StorageDealServiceImpl extends ServiceImpl<StorageDealMapper, StorageDeal> implements IStorageDealService {
    @Autowired
    MinerValues minerValues;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取日期分类列表
     *
     * @param uid
     * @return
     */
    @Override
    public Result getDayList(Integer uid) {
        List<StorageDealDayList> dayList = baseMapper.getDayList(uid);
        return Result.ok(dayList);
    }

    /**
     * 获取某日期下的内容列表
     *
     * @param uid
     * @param day
     * @return
     */
    @Override
    public Result getList(Integer uid, String day) {
        List<StorageDealOutDto> list = baseMapper.getList(uid, day);
        return Result.ok(list);
    }

    /**
     * 详情获取
     *
     * @param uid
     * @param id
     * @return
     */
    @Override
    public Result getById(Integer uid, String id) {
        LambdaQueryWrapper<StorageDeal> lambdaQueryWrapper = Wrappers.<StorageDeal>lambdaQuery().eq(StorageDeal::getUserId, uid).eq(StorageDeal::getId, id);
        StorageDeal storageDeal = getOne(lambdaQueryWrapper);
        return Result.ok(storageDeal);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    @Transactional
    public Result upload(MultipartFile file, Integer uid) throws IOException {
        LocalDate nowDay = LocalDate.now();
        //服务器存储路径
        String filePath = "/var/video/" + nowDay + "/";
        //保存文件
//        saveFile(file, filePath);
        //import文件
        String fileAllPath = filePath + file.getOriginalFilename();
        String url = minerValues.getUrl();
        String token = minerValues.getToken();

        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("id", 1);
        jsonMap.put("jsonrpc", "2.0");
        jsonMap.put("method", "Filecoin.ClientImport");

        JSONArray jsonArray = new JSONArray();
        JSONObject fileRef = new JSONObject();
        fileRef.put("Path",fileAllPath);
        fileRef.put("IsCAR",false);
        jsonArray.add(fileRef);
        jsonMap.put("params", jsonArray);

        ResponseEntity<String> apiResponse = restTemplate.postForEntity
                (
                        url,
                        HttpUtil.generatePostJson(jsonMap, token),
                        String.class
                );

        String body = apiResponse.getBody();
        //保存数据记录
        Local local = new Local();
        local.setDate(LocalDateTime.now());
        local.setFile(filePath + file.getOriginalFilename());


        return Result.ok("文件上传成功!");
    }


    private void saveFile(MultipartFile file, String filePath) {
        if (file.isEmpty()) {
            throw new BusinessException("文件流获取异常!");
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            boolean mkdir = dest.getParentFile().mkdir();
            if (!mkdir) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++");
                System.out.println(dest.getName());
                System.out.println(filePath);
                System.out.println("+++++++++++++++++++++++++++++++++++++++");
                throw new BusinessException("文件夹创建失败!");
            }
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new BusinessException("文件上传保存失败!");
        }
    }
}
