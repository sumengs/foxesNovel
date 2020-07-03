/**
 * 创建名称为name，值为value，过期时间为expiredTime（毫秒）的cookie
 * @param {string} name cookie名称
 * @param {string} value cookie值
 * @param {number} expiredTime 过期时间(毫秒)
 */
function setCookie(name, value, expiredTime) {
    // 设置过期时间
    var expdate = new Date();
    if (!expiredTime && expiredTime != 0) {
        expiredTime = 30 * 60 * 1000;
    }
    expdate.setTime(expdate.getTime() + expiredTime);
    //即document.cookie= name+"="+value+";path=/"; 时间可以不要，但路径(path)必须要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！~
    document.cookie =
        name + "=" + value + ";expires=" + expdate.toGMTString() + ";path=/";
}

/**
 * 从cookie中取出名称为name的值
 * @param {string} c_name cookie名称
 */
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

/**
 * 获取url参数
 * @param {string} param 参数名
 */
function getUrlParam(param) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == param) { return decodeURI(pair[1]); }
    }
    return null;
}

/**
 * 日期格式化
 * @param {string} fmt 格式化表达式
 * @param {*} date 日期对象
 */
function dateFormat(fmt, date) {
    // 空值检测
    if (!fmt || !date) {
        return;
    }
    // 兼容字符串、毫秒值的格式化
    if (typeof(date)=='string' || typeof(date)=='number') {
        date = new Date(date);
    }
    let ret;
    const opt = {
        "y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "s+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}