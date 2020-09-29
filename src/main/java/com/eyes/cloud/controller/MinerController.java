package com.eyes.cloud.controller;


import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.config.CommonValue;
import com.eyes.cloud.service.IMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AC
 * @since 2020-09-29
 */
@RestController
@RequestMapping(CommonValue.API_PREFIX + "/miner")
public class MinerController {
    @Autowired
    IMinerService minerService;

    @GetMapping("/miner_list")
    Result getList(){
        return minerService.getList();
    }
}
