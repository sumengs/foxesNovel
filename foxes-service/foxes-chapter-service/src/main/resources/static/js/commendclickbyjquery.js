function request(name, defVal) {
    var r = null;
    try {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        r = window.location.search.substr(1).match(reg);
    } catch (e) { }
    if (r != null) return decodeURIComponent(r[2]);
    else {
        var CmdPossionObj = jQuery('#CmdPossion');
        return $(CmdPossionObj).attr(name) ? $(CmdPossionObj).attr(name) : defVal;
    }
    return defVal;
}
if (jQuery) {
    jQuery('a[xid]').bind('click',function () {
        var xid = jQuery(this).attr('xid');
        var tid = jQuery(this).attr('tid');
        var ispc = request("ispc", 0);
        var userid = request("userid", 0);

        jQuery.getScript('https://img.xxsy.net/CommendDayCount?xid=' + xid + '&tid=' + tid + '&userid=' + userid + '&ispc=' + ispc);
        if (jQuery('base[target=_blank]').length > 0) {
            jQuery(this).attr('target', jQuery(this).attr('_blank'));
        }
        return true;
    });
}