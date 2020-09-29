// 在http.js中引入axios
import axios from 'axios'; // 引入axios
import router from "../router";
import {Spin,LoadingBar,Message} from 'view-design'
// 环境的切换
// if (process.env.NODE_ENV == 'development') {
//     axios.defaults.baseURL = 'http://127.0.0.1:8080';
//
// } else if (process.env.NODE_ENV == 'debug') {
//     axios.defaults.baseURL = 'http://127.0.0.1:8080';
// } else if (process.env.NODE_ENV == 'production') {
//     axios.defaults.baseURL = 'http://127.0.0.1:8080';
// }

axios.defaults.timeout = 100000;

axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
/**
 * 请求拦截器
 */
axios.interceptors.request.use(
    config => {
        LoadingBar.start();
        // 每次发送请求之前判断是否存在token，如果存在，则统一在http请求的header都加上token，不用每次请求都手动添加了
        // 即使本地存在token，也有可能token是过期的，所以在响应拦截器中要对返回状态进行判断
        if (config.url.indexOf('/admin')) {
            // LoadingBar.start();
        }
        const token = localStorage.getItem("token")
        token && (config.headers.token = token);
        return config;
    },
    error => {
        return Promise.error(error);
    }
)

//请求拦截（登录）

// 响应拦截器
axios.interceptors.response.use(
    response => {
        LoadingBar.finish();
        // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
        if (response.status === 200) {
            if (!response.data.code) { //针对爬虫
                return Promise.resolve(response);
            } else if (response.data.code == 200) {
                return Promise.resolve(response.data);
            } else {
                switch (response.data.code) {
                    // 400: 未登录或登录异常,跳转登录页面，并携带当前页面的路径
                    case 400:
                        //清除缓存
                        localStorage.clear();
                        router.replace({
                            path: '/login',
                            query: {
                                redirect: router.currentRoute.fullPath
                            }
                        });
                        break;
                    default:
                        Message.error(response.data.msg);
                        Spin.hide();
                        return Promise.reject(response);
                }
                return Promise.reject(response);
            }
        } else {
            Spin.hide();
            return Promise.reject(response);
        }
    },
    error => {
        LoadingBar.finish();
        Spin.hide();
        return Promise.reject(error.response);
    }
)

/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params) {
    console.log("dsdsdsd")
    return new Promise((resolve, reject) => {
        axios.get(url, {
            params: params
        }).then(res => {
            resolve(res.data);
        }).catch(err => {
            reject(err.data)
        })
    });
}

/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function post(url, params) {
    return new Promise((resolve, reject) => {
        axios.post(url, params)
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                reject(err.data)
            })
    });
}

