package com.eyes.cloud.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.config.MinerValues;
import com.eyes.cloud.dto.inDto.storageDeal.UploadDto;
import com.eyes.cloud.dto.outDto.storageDeal.*;
import com.eyes.cloud.entity.Local;
import com.eyes.cloud.entity.StorageDeal;
import com.eyes.cloud.exception.BusinessException;
import com.eyes.cloud.mapper.StorageDealMapper;
import com.eyes.cloud.service.ILocalService;
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
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    ILocalService localService;

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
     * 根据id获取文件
     *
     * @param uid
     * @param id
     * @return
     */
    @Override
    public String getFileById(Integer uid, String id) {
        LambdaQueryWrapper<StorageDeal> lambdaQueryWrapper = Wrappers.<StorageDeal>lambdaQuery().eq(StorageDeal::getUserId, uid).eq(StorageDeal::getId, id);
        StorageDeal storageDeal = getOne(lambdaQueryWrapper);
        if (storageDeal == null) {
            throw new BusinessException("当前用户无权没有此文件查看权限!");
        }
        String miner = storageDeal.getMiner();
        String cid = storageDeal.getCid();
        String pieceCid = storageDeal.getPieceCid();
        //存储配置信息
        String url = minerValues.getUrl();
        String token = minerValues.getToken();
        String wallet = minerValues.getWallet();

        //进行检索订单请求
        StorageOrder storageOrder = getStorageOrder(miner, cid, pieceCid, url, token);
        MinerPeer minerPeer = storageOrder.getMinerPeer();
        if (minerPeer.getPieceCid() != null && minerPeer.getPieceCid().length() < 10) {
            System.out.println(storageOrder.getError());
            throw new BusinessException("检索订单错误!");
        }
        System.out.println("订单查询成功!");
        //进行检索请求
        String filePath = "/var/video/temp/" + storageDeal.getId() + "_" + LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        boolean isCar = false;
        retrieve(cid, pieceCid, url, token, wallet, storageOrder, minerPeer, filePath, isCar);
        System.out.println("订单检索成功!");

        return filePath;
    }

    private void retrieve(String cid, String pieceCid, String url, String token, String wallet, StorageOrder storageOrder, MinerPeer minerPeer, String filePath, boolean isCar) {
        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("id", 1);
        jsonMap.put("jsonrpc", "2.0");
        jsonMap.put("method", "Filecoin.ClientRetrieve");
        JSONArray jsonArray = new JSONArray();
        String orderStr = "{\n" +
                "                    \"Root\": {\n" +
                "                        \"/\": \"" + cid + "\"\n" +
                "                    },\n" +
                "                    \"Piece\": {\n" +
                "                        \"/\": \"" + pieceCid + "\"\n" +
                "                    },\n" +
                "                    \"Size\": " + storageOrder.getSize() + ",\n" +
                "                    \"Total\": \"" + storageOrder.getMinPrice() + "\",\n" +
                "                    \"UnsealPrice\": \"" + storageOrder.getUnsealPrice() + "\",\n" +
                "                    \"PaymentInterval\": " + storageOrder.getPaymentInterval() + ",\n" +
                "                    \"PaymentIntervalIncrease\": " + storageOrder.getPaymentIntervalIncrease() + ",\n" +
                "                    \"Client\": \"" + wallet + "\",\n" +
                "                    \"Miner\": \"" + storageOrder.getMiner() + "\",\n" +
                "                    \"MinerPeer\": {\n" +
                "                        \"Address\": \"" + minerPeer.getAddress() + "\",\n" +
                "                        \"ID\": \"" + minerPeer.getId() + "\",\n" +
                "                        \"PieceCID\": {\n" +
                "                            \"/\": \"" + pieceCid + "\"\n" +
                "                        }\n" +
                "                    }\n" +
                "                }";
        JSONObject orderObj = JSONObject.parseObject(orderStr);

        String fileRefStr = "      {\n" +
                "                    \"Path\": \"" + filePath + "\",\n" +
                "                    \"IsCAR\": " + isCar + "\n" +
                "                   }";
        JSONObject fileRefObj = JSONObject.parseObject(fileRefStr);
        jsonArray.add(orderObj);
        jsonArray.add(fileRefObj);
        jsonMap.put("params", jsonArray);
        //发起http请求
        String bodyStr = sendHttpRequest(url, token, jsonMap);
        //获取json对象
        JSONObject body = JSONObject.parseObject(bodyStr);
        if (body.get("error") != null) {
            System.out.println(body.get("error"));
            throw new BusinessException("检索交易执行失败!");
        }
    }

    private StorageOrder getStorageOrder(String miner, String cid, String pieceCid, String url, String token) {
        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("id", 1);
        jsonMap.put("jsonrpc", "2.0");
        jsonMap.put("method", "Filecoin.ClientMinerQueryOffer");
        JSONArray jsonArray = new JSONArray();
        JSONObject cidObj = new JSONObject();
        cidObj.put("/", cid);
        JSONObject pieceCidObj = new JSONObject();
        pieceCidObj.put("/", pieceCid);
        jsonArray.add(miner);
        jsonArray.add(cidObj);
        jsonArray.add(pieceCidObj);
        jsonMap.put("params", jsonArray);

        //发起http请求
        String bodyStr = sendHttpRequest(url, token, jsonMap);
        //获取json对象
        JSONObject body = JSONObject.parseObject(bodyStr);
        JSONObject result = (JSONObject) body.get("result");
        return result.toJavaObject(StorageOrder.class);
    }

    /**
     * 开始执行交易表状态刷新任务
     */
    @Override
    @Transactional()
    public void refreshDealsList() {
        //存储配置信息
        String url = minerValues.getUrl();
        String token = minerValues.getToken();

        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("id", 1);
        jsonMap.put("jsonrpc", "2.0");
        jsonMap.put("method", "Filecoin.ClientListDeals");
        JSONArray jsonArray = new JSONArray();
        jsonMap.put("params", jsonArray);

        //发起http请求
        String bodyStr = sendHttpRequest(url, token, jsonMap);
        //获取json对象
        JSONObject body = JSONObject.parseObject(bodyStr);
        JSONArray result = (JSONArray) body.get("result");
        List<StorageDealByFind> deals = result.toJavaList(StorageDealByFind.class);
//        System.out.println(deals);

        Map<String, StorageDealByFind> dealMap = getDealMap(deals);

        List<StorageDeal> storageDealList = list();
        List<StorageDeal> collect = storageDealList.stream().peek(storageDeal -> {
            StorageDealByFind dealByFind = dealMap.get(storageDeal.getDealCid());
            if (dealByFind != null) {
                storageDeal.setPieceCid(dealByFind.getPieceCid());
                storageDeal.setDealId(dealByFind.getDealId());
                storageDeal.setMessage(dealByFind.getMessage());
                storageDeal.setStatus(dealByFind.getState());
            }
        }).collect(Collectors.toList());

        boolean b = saveOrUpdateBatch(collect);
        if (!b) {
            throw new BusinessException("交易信息更新保存操作失败!");
        }
    }

    private Map<String, StorageDealByFind> getDealMap(List<StorageDealByFind> deals) {
        return deals.stream().peek(storageDeal -> {
            //            String proposalCid = storageDeal.getProposalCid();
            //            String subProposalCid = proposalCid.substring(6, proposalCid.length() - 2);
            //
            //            String pieceCid = storageDeal.getPieceCid();
            //            String subPieceCid = pieceCid.substring(6, pieceCid.length() - 2);
            //
            //            String dataRef = storageDeal.getDataRef();
            //            String subDataRef = dataRef.substring(14, 14+62);

            JSONObject proposalCidObj = JSONObject.parseObject(storageDeal.getProposalCid());
            String proposalCid = proposalCidObj.get("/").toString();
            JSONObject pieceCidObj = JSONObject.parseObject(storageDeal.getPieceCid());
            String pieceCid = pieceCidObj.get("/").toString();
            JSONObject dataRef = JSONObject.parseObject(storageDeal.getDataRef());
            JSONObject root = (JSONObject) dataRef.get("Root");
            String cid = root.get("/").toString();

            storageDeal.setCid(cid);
            storageDeal.setProposalCid(proposalCid);
            storageDeal.setPieceCid(pieceCid);
        }).collect(Collectors.toMap(StorageDealByFind::getProposalCid, storageDealByFind -> storageDealByFind));
    }

    /**
     * 上传文件
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Result upload(UploadDto dto, Integer uid) throws IOException {
        LocalDate nowDay = LocalDate.now();
        //服务器存储路径
        MultipartFile file = dto.getFile();
        String filePath = "/var/video/" + nowDay + "/";
        String fileName = file.getOriginalFilename() + "_" + uid + "_" + LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        //存储配置信息
        String url = minerValues.getUrl();
        String token = minerValues.getToken();
        String miner = dto.getMiner();
        String wallet = minerValues.getWallet();

        //保存文件
        saveFile(file, filePath + fileName);

        //import文件
        String cid = importFile(fileName, filePath, url, token);

        //发起交易
        String dealId = startDeal(cid, wallet, miner, url, token);

        //存储交易记录
        saveData(uid, filePath + fileName, miner, wallet, cid, dealId);

        return Result.ok("文件上传成功!");
    }

    /**
     * 存储交易记录数据
     *
     * @param uid
     * @param fileAllPath
     * @param miner
     * @param wallet
     * @param cid
     * @param dealId
     */
    private void saveData(Integer uid, String fileAllPath, String miner, String wallet, String cid, String dealId) {
        StorageDeal storageDeal = new StorageDeal();
        storageDeal.setCid(cid);
        storageDeal.setDealCid(dealId);
        storageDeal.setCreateTime(LocalDateTime.now());
        //存储时间(指成功传输到服务器)todo 大文件传输时间确认!
        storageDeal.setDate(LocalDateTime.now());
        //现在全部都是固定时间固定价格
        storageDeal.setEpochPrice("500000000");
        storageDeal.setMinBlocksDuration("600000");
        storageDeal.setFileName(fileAllPath);
        storageDeal.setMiner(miner);
        storageDeal.setStatus(0);
        storageDeal.setUserId(uid);
        storageDeal.setWallet(wallet);
        storageDeal.setStorageDealStatus("start");
        boolean b = save(storageDeal);
        if (!b) {
            throw new BusinessException("交易数据记录保存失败!");
        }
    }

    /**
     * 发起交易
     *
     * @param cid
     * @param wallet
     * @param miner
     * @param url
     * @param token
     * @return
     */
    private String startDeal(String cid, String wallet, String miner, String url, String token) {

        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("id", 1);
        jsonMap.put("jsonrpc", "2.0");
        jsonMap.put("method", "Filecoin.ClientStartDeal");

        JSONArray jsonArray = new JSONArray();
        String str = "{" +
                "            \"Data\": {" +
                "                \"TransferType\": \"graphsync\"," +
                "                \"Root\": {" +
                "                    \"/\": \"" + cid + "\"" +
                "                }," +
                "                \"PieceCid\": null," +
                "                \"PieceSize\":0" +
                "            }," +
                "            \"Wallet\":\"" + wallet + "\"," +
                "            \"Miner\": \"" + miner + "\"," +
                "            \"EpochPrice\": \"500000000\"," +
                "            \"MinBlocksDuration\": 600000" +
                "        }";
        JSONObject jsonObject = JSONObject.parseObject(str);
        jsonArray.add(jsonObject);
        jsonMap.put("params", jsonArray);

        //发起http请求
        String bodyStr = sendHttpRequest(url, token, jsonMap);
        System.out.println("+++++++++++++发起交易++++++++++");
        System.out.println(bodyStr);
        System.out.println("+++++++++++++发起交易++++++++++");
        //获取json对象
        JSONObject result = getJsonObject(bodyStr);
        return result.get("/").toString();
    }

    /**
     * 获取"result"json对象
     *
     * @param bodyStr
     * @return
     */
    private JSONObject getJsonObject(String bodyStr) {
        JSONObject body = JSONObject.parseObject(bodyStr);
        return (JSONObject) body.get("result");
    }

    /**
     * 发起http请求
     *
     * @param url
     * @param token
     * @param jsonMap
     * @return
     */
    private String sendHttpRequest(String url, String token, Map<String, Object> jsonMap) {
        ResponseEntity<String> apiResponse = restTemplate.postForEntity
                (
                        url,
                        HttpUtil.generatePostJson(jsonMap, token),
                        String.class
                );
        String body = apiResponse.getBody();
//        System.out.println("+++++++++++++++++++++++");
//        System.out.println(body);
//        System.out.println("+++++++++++++++++++++++");
        return body;
    }

    /**
     * import文件到fil系统
     *
     * @param fileName
     * @param filePath
     */
    private String importFile(String fileName, String filePath, String url, String token) {

        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("id", 1);
        jsonMap.put("jsonrpc", "2.0");
        jsonMap.put("method", "Filecoin.ClientImport");

        JSONArray jsonArray = new JSONArray();
        JSONObject fileRef = new JSONObject();
        fileRef.put("Path", filePath + fileName);
        fileRef.put("IsCAR", false);
        jsonArray.add(fileRef);

        jsonMap.put("params", jsonArray);

        String bodyStr = sendHttpRequest(url, token, jsonMap);
        System.out.println("+++++++++++++import文件到fil系统++++++++++");
        System.out.println(bodyStr);
        System.out.println("+++++++++++++import文件到fil系统++++++++++");
        JSONObject result = getJsonObject(bodyStr);
        JSONObject root = (JSONObject) result.get("Root");
        String cid = root.get("/").toString();
        String importId = result.get("ImportID").toString();
        //保存数据记录
        Local local = new Local();
        local.setId(importId);
        local.setCid(cid);
        local.setDate(LocalDateTime.now());
        local.setFile(filePath + fileName);

        boolean b = localService.save(local);
        if (!b) {
            throw new BusinessException("import数据记录保存失败!");
        }
        return cid;
    }

    /**
     * 保存文件
     *
     * @param file
     * @param fileAllName
     */
    private void saveFile(MultipartFile file, String fileAllName) {
        if (file.isEmpty()) {
            throw new BusinessException("文件流获取异常!");
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(fileAllName);
        if (!dest.getParentFile().exists()) {
            boolean mkdir = dest.getParentFile().mkdir();
            if (!mkdir) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++");
                System.out.println(fileAllName);
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
