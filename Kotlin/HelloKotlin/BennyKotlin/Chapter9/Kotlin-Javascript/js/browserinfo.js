/**
 * Created by benny on 4/30/17.
 */
// 获取浏览器名称及版本信息
function browserInfo() {
    var browser = {
            msie: false,
            chrome: false,
            firefox: false,
            opera: false,
            netscape: false,
            safari: false,
            appname: 'unknown',
            version: 0
        },
        userAgent = window.navigator.userAgent.toLowerCase();
    if (/(msie|chrome|firefox|opera|netscape)\D+(\d[\d.]*)/.test(userAgent)) {
        browser[RegExp.$1] = true;
        browser.appname = RegExp.$1;
        browser.version = RegExp.$2;
    } else if (/version\D+(\d[\d.]*).*safari/.test(userAgent)) { // safari
        browser.safari = true;
        browser.appname = 'safari';
        browser.version = RegExp.$2;
    }
    return browser;
}

function browerInfoString(){
    var mybi = browserInfo(); // 调用示例
// 如果当前浏览器是IE，弹出浏览器版本,否则弹出当前浏览器名称和版本
    if (mybi.msie) {
        return "Internet Explorer"
    } else {
        return mybi.appname
    }
}

