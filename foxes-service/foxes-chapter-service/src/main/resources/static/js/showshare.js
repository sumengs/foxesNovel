(function () {

    var _hasGetShareInfo = 0;
    var _objShareInfoBox;
    var _btnShowShare = $('.btnshowshare');

    _btnShowShare.click(function () {

        if(!window.hasLogin){
            window.login();
            return;
        }

        if (_hasGetShareInfo == 2) {
            _objShareInfoBox.show();
            return;
        }
        
        if (_hasGetShareInfo == 1)
            return;

        getShareInfo();
    });

    function getShareInfo() {

        var _shareBookId = parseInt(_btnShowShare.attr('data-bookid'));

        _hasGetShareInfo = 1;
        $.get('/partview/GetMyShareInfoBox', $.param({bookid:_shareBookId, rnd : Math.random() }), function (data) {
            if(data != ''){
                $('body').append(data);
                bindShareBoxEvent();
                _hasGetShareInfo = 2;
            }
        });
    }

    function bindShareBoxEvent() {
        _objShareInfoBox = $('#shareinfobox');

        $('#closesharebox').click(function () {
            _objShareInfoBox.hide();
        })

        var clipboard = new ClipboardJS('#copy');
        clipboard.on('success', function (e) {
            Util.showMsgBox('复制成功', 1000, true);
        });
        clipboard.on('error', function (e) {
            Util.showMsgBox('复制失败，请手动复制', 1000);
        });        
    }

   

})()