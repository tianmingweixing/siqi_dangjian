<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>思齐党建后台</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">

    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>

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
        <label class="layui-form-label">使用须知</label>
        <div class="layui-input-block">
            <textarea name="desc" id="use_navigate" placeholder="请输入内容"
                      class="layui-textarea"><#if use_navigate??>${use_navigate}<#else></#if></textarea>
        </div>
    </div>

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
    <!--var count =<#if imageList??>${imageList?size}<#else >0</#if>;-->

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

</script>

</body>
</html>