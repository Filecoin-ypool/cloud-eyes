/**
 * api接口统一管理
 */
import {get} from './http'

const api = {};

//获取日前列表
api.getDayList = () => get('/api/storage_deal/day_List', null);

//根据日前获取内容
api.getListByDay = p => get("/api/storage_deal/list/"+p, null)

export default api;

