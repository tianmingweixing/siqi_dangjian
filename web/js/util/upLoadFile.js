$(function () {
    var $ = jQuery;
    var $list = $("#thelist");
    var $btn = $("#ctlBtn");
    var state = 'pending'; // 上传文件初始化
    var uploader = WebUploader.create({
        swf: '/WImageUpload/Uploader.swf',
        server: 'http://localhost:8080/upload/uploadImage',
        pick: '#picker',
        resize: false,
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });


    uploader.on('fileQueued', function (file) {
        count++;
        var $li;
        if (count == 1||count%9==1) {
            $li = $(
                '<div id="' + file.id + '" style="display:inline-block;float: left;margin-top: 5px">' +
                '<img>' +
                '<div  style="font-size: 14px;text-align: center;width: 100px;">' + file.name + '</div></div>'
            );
        } else {
            $li = $(
                '<div id="' + file.id + '" style="display:inline-block;float: left; margin-left: 40px;margin-top:5px">' +
                '<img >' +
                '<div  style="font-size: 14px;text-align: center;width: 100px;">' + file.name + '</div></div>'
            );
        }
        var $img = $li.find('img');
        var $btns = $('<div style="width: 100px;text-align: center;margin-top: 7px"><img id="delete_icon" style="width: 25px;height: 25px;"/><text style="font-size: 14px;margin-left: 3px">待上传</text></div>').appendTo($li);
        $li.find('#delete_icon').attr("src", "/images/delete_icon.png");

        // $list为容器jQuery实例
        $list.append($li);
        $btns.on('click', 'img', function () {
            var index = $(this).index();
            switch (index) {
                case 0:
                    uploader.removeFile(file);
                    removeFile(file);
                    return;
            }
        });


        //创建缩略图
        //如果为非图片文件，可以不用调用此方法。
        //thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $img.attr('src', src);
        }, 100, 100);
    });

    // uploader.on('fileQueued', function (file) {
    //     var $li = $(
    //         '<div id="' + file.id + '" class="cp_img">' +
    //         '<img>' +
    //         '<div id="delete_icon" class="delete_icon"></div>'+
    //         '<div class="cp_img_jian"></div></div>'
    //         ),
    //         $img = $li.find('img');
    //     var $btns = $('<div class="file-panel">' +
    //         '<span class="cancel" >删除</span>').appendTo($li);
    //
    //     // $("#delete" + file.id).on('click',function () {
    //     //    console.warn(99999);
    //     // });
    //     // $list为容器jQuery实例
    //     $list.append($li);
    //
    //     $btns.on('click', 'span', function () {
    //         var index = $(this).index();
    //         switch (index) {
    //             case 0:
    //                 uploader.removeFile(file);
    //                 removeFile(file);
    //                 return;
    //         }
    //     });
    //
    // //创建缩略图
    // //如果为非图片文件，可以不用调用此方法。
    // //thumbnailWidth x thumbnailHeight 为 100 x 100
    //     uploader.makeThumb(file, function (error, src) {
    //         if (error) {
    //             $img.replaceWith('<span>不能预览</span>');
    //             return;
    //         }
    //         $img.attr('src', src);
    //     }, 100, 100);
    // });

    uploader.on('uploadProgress',
        function (file, percentage) {
            var $li = $('#' + file.id), $percent = $li
                .find('.progress .progress-bar');

            // 避免重复创建
            if (!$percent.length) {
                $percent = $(
                    '<div class="progress progress-striped active" style="width: 100px;height: 15px;border-radius: 5px;background-color: #ee232a">'
                    + '<div class="progress-bar" role="progressbar" style="width: 0%;background-color: #111111">'
                    + '</div>' + '</div>')
                    .appendTo($li).find('.progress-bar');
            }

            $li.find('text').text('上传中...');

            $percent.css('width', percentage * 100 + '%');
        });

    uploader.on('uploadBeforeSend', function (obj, data) {
        //传入表单参数
        data = $.extend(data, {
            uuid: uuid
        });
    });
    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('text').text('已上传');
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('text').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });
    $btn.on('click', function () {

    });

    function removeFile(file) {
        var $li = $('#' + file.id);
        $li.off().find('.file-panel').off().end().remove();
        count--;
    }


});