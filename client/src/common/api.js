/**
 * api接口统一管理
 */
import {get, post} from './http'

const api = {};

//获取日前列表
api.getDayList = () => get('/api/storage_deal/day_List', null)

//根据日前获取内容
api.getListByDay = p => get("/api/storage_deal/list/" + p, null)

//登录
api.signIn = p => post("/api/f_user/login", p)

//注册
api.signUp = p => post('/api/f_user/register', p)

//获取用户名
api.getUsername = () => get("/api/f_user/get_user_name", null)

//获取miner
api.getMinerList = () => get('/api/miner/miner_list', null)

//上传数据统计
api.statistics = () => get('/api/storage_deal/statistics', null)

export default api;

