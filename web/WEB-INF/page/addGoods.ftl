<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/addGood.css">
    <link rel="stylesheet" type="text/css" href="/WImageUpload/webuploader.css">
    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>
    <!--引入JS-->
    <script type="text/javascript" src="/WImageUpload/webuploader.js"></script>

</head>
<body>

<form class="layui-form" action="">

    <div class="layui-form-item input_row_margin_top" style="margin-top: 30px">
        <label class="layui-form-label">商品类别</label>
        <div class="layui-input-inline">
            <select id="category_type" name="category_type" lay-filter="select_first_category">
                <option value=''>请选择</option>
            </select>
        </div>
        <div class="layui-input-inline" style="margin-left: 30px">
            <select id="category_second_type" name="category_second_type">
                <option value=''>请选择</option>
            </select>
        </div>
    </div>
    <input type="hidden" id="good_price_id" value="<#if price_id??>${price_id}<#else></#if>">
    <input type="hidden" id="good_id" value="<#if id??>${id}<#else></#if>">
    <input type="hidden" id="creat_time_hidden" value="<#if create_time??>${create_time}<#else></#if>">
    <input type="hidden" id="saled_count" value="<#if saled_count??>${saled_count}<#else></#if>">
    <input type="hidden" id="uuid" value="<#if uuid??>${uuid}<#else></#if>">
    <input type="hidden" id="shareCount" value="<#if share_count??>${share_count}<#else></#if>">
    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">商品名称</label>
        <div class="layui-input-inline">
            <input id="good_name" name="good_name" lay-verify="required" placeholder="请输入商品名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if goods_name??>${goods_name}<#else></#if>">
        </div>
        <label class="layui-form-label" style="margin-left: 85px">出品厂家</label>
        <div class="layui-input-inline">
            <input id="vender" name="vender" lay-verify="required" placeholder="请输入出品厂家" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if vender??>${vender}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">商品编码</label>
        <div class="layui-input-inline">
            <input id="good_code" name="good_code" lay-verify="required" placeholder="请输入编码" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if code??>${code}<#else></#if>">
        </div>

        <label class="layui-form-label " style="margin-left: 85px">商品单位</label>
        <div class="layui-input-inline">
            <input id="unit" name="unit" lay-verify="required" placeholder="请输入单位" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if unit??>${unit}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">正常价格</label>
        <div class="layui-input-inline">
            <input id="normal_price" name="normal_price" lay-verify="required|number" placeholder="请输入价格" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if normal_price??>${normal_price}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux">元</div>
        <label class="layui-form-label " style="margin-left: 60px">商品折扣</label>
        <div class="layui-input-inline">
            <input id="discount" name="discount" lay-verify="number" placeholder="请输入商品折扣" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if discount??>${discount}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">库存数量</label>
        <div class="layui-input-inline">
            <input id="store_count" name="credit" lay-verify="number" placeholder="请输入库存" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if store_count??>${store_count}<#else></#if>">
        </div>
        <label class="layui-form-label " style="margin-left: 84px">保质期</label>
        <div class="layui-input-inline">
            <input id="period"  lay-verify="number" placeholder="请输入有效期" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if period??>${period}<#else></#if>">
        </div>
        <div class="layui-form-mid layui-word-aux">天</div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" >是否上架</label>
        <div class="layui-input-inline">
            <input type="radio" name="isSale" value="1" title="上架" <#if isSale??&&isSale==1>checked</#if>>
            <input type="radio" name="isSale" value="0" title="不上架" <#if isSale??&&isSale==0>checked</#if>>
        </div>
        <label class="layui-form-label " style="margin-left: 84px">商品规格</label>
        <div class="layui-input-inline">
            <input id="format"   placeholder="请输入商品规格" maxlength="20"
                   autocomplete="off" class="layui-input" value="<#if format??>${format}<#else></#if>">
        </div>
    </div>

    <div class="layui-form-item layui-form-text input_row_margin_top" style="padding-right: 900px">
        <label class="layui-form-label">商品描述</label>
        <div class="layui-input-block">
            <textarea name="desc" id="brief" placeholder="请输入内容"
                      class="layui-textarea"><#if brief??>${brief}<#else></#if></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text input_row_margin_top" style="padding-right: 900px">
        <label class="layui-form-label">选择视频</label>
        <input type="file" id="chooseVedio" />
    </div>



    <div class="layui-form-item layui-form-text input_row_margin_top" style="padding-right: 900px">
        <label class="layui-form-label">使用须知</label>
        <div class="layui-input-block">
            <textarea name="desc" id="use_navigate" placeholder="请输入内容"
                      class="layui-textarea"><#if use_navigate??>${use_navigate}<#else></#if></textarea>
        </div>
    </div>



    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">商品属性</label>-->
        <#--<div class="layui-input-inline" style="width: 70%">-->
            <#--<table class="layui-table">-->
                <#--<colgroup>-->
                    <#--<col width="150">-->
                    <#--<col width="200">-->
                    <#--<col>-->
                <#--</colgroup>-->
                <#--<thead>-->
                <#--<tr>-->
                    <#--<th>属性名</th>-->
                    <#--<th>属性编码</th>-->
                    <#--<th>属性值1</th>-->
                    <#--<th>属性值2</th>-->
                    <#--<th>属性值3</th>-->
                    <#--<th>属性值4</th>-->
                    <#--<th>属性值5</th>-->
                    <#--<th>属性值6</th>-->
                    <#--<th style="width: 100px">操作</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody id="table_body">-->

                <#--</tbody>-->
            <#--</table>-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="layui-form-item input_row_margin_top">-->
        <#--<div class="layui-input-block">-->
            <#--<a class="layui-btn layui-bg-blue" onclick="appendTableBody()">添加属性</a>-->
        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <div class="input_row_margin_top">
            <label class="layui-form-label">商品图片</label>
            <div id="uploader">
                <!--用来存放文件信息-->
                <div id="thelist" class="uploader-list layui-input-inline" style="width: 1192px">
                    <#if  imageList??>
                        <#list imageList as image>
                            <#if image_index==0>
                              <div style="display:inline-block;float: left;margin-top: 5px;">
                            <#else >
                              <div style="display:inline-block;float: left;margin-top: 5px;margin-left:40px">
                            </#if>
                            <img src="<#if image??>${image.src}<#else >''</#if>" style="width: 100px;height: 100px;">
                            <div style="font-size: 14px;text-align: center;width: 100px;">${image.name}</div>
                        <div style="width: 100px;text-align: center;margin-top: 7px">
                            <img id="delete_icon" onclick="deleteImg(${image.id})" style="width: 25px;height: 25px;"
                                 src="/images/delete_icon.png"/>
                        </div>
                        </div>
                        </#list>
                    </#if>
                </div>

                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <div id="picker" style="display: inline-block;">选择文件</div>
            </div>
        </div>
        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

</form>

<script>
    //Demo

    var tr_array = [];
    var row_count = 1;
    var attr_array = [];
    var category = <#if category_name??>"${category_name}"<#else>""</#if>;
    var Pcode = <#if pcode??>"${pcode}"<#else>""</#if>;
    var Pname = <#if pname??>"${pname}"<#else>""</#if>;
    var uuid = <#if uuid??>"${uuid}"<#else>""</#if>;
    var count =<#if imageList??>${imageList?size}<#else >0</#if>;
    $(function () {
        var $ = jQuery;
        var $list = $("#thelist");
        var $btn = $("#ctlBtn");
        var state = 'pending'; // 上传文件初始化
        var uploader = WebUploader.create({
            swf: '/WImageUpload/Uploader.swf',
            server: 'http://localhost:8081/upload/uploadImage',//图片上传服务器地址
            pick: '#picker',
            resize: false,
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        uploader.on('uploadBeforeSend', function (obj, data) {
            //传入表单参数
            data = $.extend(data, {
                uuid: uuid

            });
        });


        uploader.on('fileQueued', function (file) {
            count++;
            var $li;
            if (count == 1||count%9==1) {
                $li = $(
                        '<div id="' + file.id + '" style="display:inline-block;float: left;margin-top: 5px">' +
                        '<img>' +
                        '<div  style="font-size: 14px;text-align: center;width: 100px;">' + file.name.substr(0,file.name.length-4) + '</div></div>'
                );
            } else {
                $li = $(
                        '<div id="' + file.id + '" style="display:inline-block;float: left; margin-left: 40px;margin-top:5px">' +
                        '<img >' +
                        '<div  style="font-size: 14px;text-align: center;width: 100px;">' + file.name.substr(0,file.name.length-4) + '</div></div>'
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

        layui.use('form', function () {
            var form = layui.form;
            //监听提交
            form.on('select(select_first_category)', function (data) {
                var first_catrgory = $("#category_type").val();
                $.ajax({
                    url: "/category/secondCategory",//请求地址
                    type: "POST",//请求方式
                    dataType: "JSON",//返回数据类型
                    data: {Pcode: first_catrgory},//发送的参数
                    success: function (data) {
                        $("#category_second_type").empty();
                        if (data.list.length == 0) {
                            $("#category_second_type").append("<option>暂无数据</option>")
                        }
                        $.each(data.list, function (index, item) {
                            $("#category_second_type").append("<option  value='" + item.category_code + "'>" + item.name + "</option>")
                        });
                        form.render('select');
                    },
                    error: function () {
                        //失败执行的方法
                        layer.msg("查询出错");
                    }
                });
            });
            $.ajax({
                url: "/category/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        if (item.name == Pname)
                            $("#category_type").append("<option selected='selected' value='" + item.category_code + "'>" + item.name + "</option>");
                        else
                            $("#category_type").append("<option  value='" + item.category_code + "'>" + item.name + "</option>");

                    });
                    $.ajax({
                        url: "/category/secondCategory",//请求地址
                        type: "POST",//请求方式
                        dataType: "JSON",//返回数据类型
                        data: {Pcode: Pcode},//发送的参数
                        success: function (data) {
                            $.each(data.list, function (index, item) {
                                if (item.name == category)
                                    $("#category_second_type").append("<option selected='selected' value='" + item.category_code + "'>" + item.name + "</option>")
                                else
                                    $("#category_second_type").append("<option  value='" + item.category_code + "'>" + item.name + "</option>")
                            });
                            form.render('select');
                        },
                        error: function () {
                            //失败执行的方法
                            layer.msg("查询出错");
                        }
                    });
                }
            });
            form.on('submit(formDemo)', function (data) {
                var formData = new FormData();
                if(document.getElementById("chooseVedio").files[0]){
                    formData.append("vedio", document.getElementById("chooseVedio").files[0]);
                    formData.append("uuid", uuid);
                    $.ajax({
                        url: "/upload/uploadVedio",
                        type: "POST",
                        data: formData,
                        /**
                         *必须false才会自动加上正确的Content-Type
                         */
                        contentType: false,
                        /**
                         * 必须false才会避开jQuery对 formdata 的默认处理
                         * XMLHttpRequest会对 formdata 进行正确的处理
                         */
                        processData: false,
                        success: function (data) {
                            if (data.status == "error") {
                                layer.msg('视频上传失败', {icon: 2});

                            }
                        },
                        error: function () {
                            layer.msg('视频上传失败', {icon: 2});
                        }
                    });
                }
                if (state === 'uploading') {
                    uploader.stop();
                } else {
                    uploader.upload();
                }
                // appendGoodAttr();
                $.ajax({
                    url: "../good/addGoods",
                    data: {
                        category_type: $("#category_type").val(),
                        good_name: $("#good_name").val(),
                        vender: $("#vender").val(),
                        good_code: $("#good_code").val(),
                        unit: $("#unit").val(),
                        normal_price: $("#normal_price").val(),
                        credit: $("#credit").val(),
                        isSale: $('input[name="isSale"]:checked').val(),
                        desc: $("#brief").val(),
                        id: $("#good_id").val(),
                        price_id: $("#good_price_id").val(),
                        create_time: $("#creat_time_hidden").val(),
                        store_count: $("#store_count").val(),
                        saled_count: $("#saled_count").val(),
                        category_second_type: $("#category_second_type").val(),
                        discount:$("#discount").val(),
                        uuid: $("#uuid").val(),
                        format:$("#format").val(),
                        quality_period:$("#period").val(),
                        use_navigate:$("#use_navigate").val()
                    },
                    success: function (data) {
                        layer.msg('保存成功', {icon: 1});
                        setTimeout(function () {
                            window.location.href = '../mainMenu/gotoGoodList'
                        }, 1500);
                    }
                });
                return false;
            });
        });


        function appendGoodAttr() {
            var id_str = $("#table_body").children(":first").attr("id");
            var id_i = id_str.substr(id_str.length - 1, id_str.length - 1);
            var next = $("#" + id_str).next();
            parseAttrRow(id_i);
            for (var i = 1; i <= row_count; i++) {
                id_str = $("#" + id_str).next().attr("id");
                id_i = id_str.substr(id_str.length - 1, id_str.length - 1);
                parseAttrRow(id_i);
                next = next.next();
            }
        }

        // function parseAttrRow(id_i) {
        //     var obj = {};
        //     for (var j = 0; j < 8; j++) {
        //         var value = $("#input_" + j + "_Attr" + id_i).val();
        //         if (j == 1 && value != "") {
        //             obj.attrName = value;//每一行第一个input是属性名
        //         } else if (j == 2 && value != "") {
        //             obj.attrCode = value;
        //         } else {
        //             obj.attrValue[j] = value;
        //         }
        //     }
        //     if (obj.attrName && obj.attrName != "") {
        //         attr_array.push(obj)
        //     }
        // }
    });

    function deleteImg(id) {
        $.ajax({
            url: "../upload/deleteImage",
            type: "POST",
            data: {
                imageId: id
            },
            success: function (data) {
                if (data.result == "success")
                    layer.msg("删除成功");
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
                if (data.result == "fail")
                    layer.msg("后台异常");
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            }
        })
    };

    function appendTableBody() {
        tr_array[row_count] = "<tr id='bodytr" + row_count + "'><td><input id='input_1_Attr" + row_count + "' name='input_1_Attr" + row_count + "' class='layui-input'/></td> <td><input id='input_2_Attr" + row_count + "' name='input_2_Attr" + row_count + "' class='layui-input'/></td> <td><input id='input_3_Attr" + row_count + "' name='input_3_Attr" + row_count + "' class='layui-input'/></td>" +
                "<td ><input id='input_4_Attr" + row_count + "' name='input_4_Attr" + row_count + "' class='layui-input'/></td><td><input id='input_5_Attr" + row_count + "' name='input_5_Attr" + row_count + "' class='layui-input'/></td><td><input id='input_6_Attr" + row_count + "' name='input_6_Attr" + row_count + "' class='layui-input'/></td><td><input id='input_7_Attr" + row_count + "' name='input_7_Attr" + row_count + "' class='layui-input'/></td>" +
                "<td><input id='input_8_Attr" + row_count + "' name='input_1_Attr\" + row_count + \"' class='layui-input'/></td><td style='text-align: center'><a  class='op_button'>" +
                " <i class='layui-icon'>&#xe642;</i>" +
                "</a><a  class='op_button' style='margin-left:10px' onclick='delete_attr_row(" + row_count + ")'><i class='layui-icon'>&#xe640;</i></a></td></tr>";
        $("#table_body").append(tr_array[row_count]);
        row_count++;
    }


</script>

</body>
</html>