module.exports = {
    // 相当于webpack-dev-server，对本地服务器进行配置
    devServer: {
        port: 8080, // 端口
        proxy: {
            "/api": {
                target: "http://127.0.0.1:8001/api/v1", // 需要跨域的目标url，我这里用到的是豆瓣API
                changeOrigin: true, // 将基于名称的虚拟托管网站的选项，如果不配置，请求会报404
                ws: true,
                pathRewrite: {
                    "^/api": ''
                }
            }
        }
    }
}
