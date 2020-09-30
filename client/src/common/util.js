//字节转换
let getSize = function (limit, num) {
    let size = '';
    if (limit < 1 * 1024) {                            //小于0.1KB，则转化成B
        size = limit.toFixed(2) + "iB"
    } else if (limit < 1 * 1024 * 1024) {            //小于0.1MB，则转化成KB
        size = (limit / 1024).toFixed(2) + " KiB"
    } else if (limit < 1 * 1024 * 1024 * 1024) {        //小于0.1GB，则转化成MB
        size = (limit / (1024 * 1024)).toFixed(2) + " MiB"
    } else if (limit < 1 * 1024 * 1024 * 1024 * 1024) {                                            //其他转化成GB
        size = (limit / (1024 * 1024 * 1024)).toFixed(num) + " GiB"
    } else if (limit < 1 * 1024 * 1024 * 1024 * 1024 * 1024) {
        size = (limit / (1024 * 1024 * 1024 * 1024)).toFixed(num) + " TiB"
    } else {
        size = (limit / (1024 * 1024 * 1024 * 1024 * 1024)).toFixed(num) + " PiB"
    }
    return size;
}

export {
    getSize
}
