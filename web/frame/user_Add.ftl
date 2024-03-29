
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 党支部后台</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <link  href="../js/layui/layui.all.js">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<form class="layui-form" action="">

<br>
    <div class="layui-input-inline" style="display:none ">
        <label class="layui-form-label" style="margin-left: 85px">Id</label>
        <input id="id" name="id"   maxlength="20" value=""/>
    </div>
    <div class="layui-input-inline" style="display:none ">
        <label class="layui-form-label"  style="margin-left: 85px">职务id</label>
        <input id="dutyid" name="dutyid" maxlength="20" value=""/>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">用户名称</label>
        <div class="layui-input-inline">
            <input id="username" name="username" lay-verify="required" placeholder="请输入用户名称" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex"  value="1" title="男"/>
            <input type="radio" name="sex"  value="2" title="女"/>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" >身份证</label>
        <div class="layui-input-inline">
            <input id="ID_cord" name="ID_cord" lay-verify="required" placeholder="请输入身份证" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">学历</label>
        <div class="layui-input-inline">
            <select name="education" id="education">
                <option value="1">初中</option>
                <option value="2">高中</option>
                <option value="3">中专</option>
                <option value="4">大专</option>
                <option value="5" selected >本科</option>
                <option value="6">硕士</option>
                <option value="7">博士</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label ">用户年龄</label>
        <div class="layui-input-inline">
            <input id="age" name="age" lay-verify="number" placeholder="请输入年龄" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">政治面貌</label>
        <div class="layui-input-inline">
            <select name="type_name" id="type_name">
            </select>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" >手机号码</label>
        <div class="layui-input-inline">
            <input id="phone" name="phone" lay-verify="number" placeholder="请输入手机号码" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">入党时间</label>
        <div class="layui-input-inline">
            <input id="join_time" name="join_time" lay-verify="" placeholder="请输入入党时间" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">
        <label class="layui-form-label" >所在单位</label>
        <div class="layui-input-inline">
            <input id="company" name="company" lay-verify="required" placeholder="请输入所在单位" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

        <label class="layui-form-label" style="margin-left: 85px">单位职务</label>
        <div class="layui-input-inline">
            <input id="company_office" name="company_office" lay-verify="required" placeholder="请输入单位职务" maxlength="20"
                   autocomplete="off" class="layui-input" value="">
        </div>

    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label">困难情况</label>
        <div class="layui-input-inline">
            <select name="difficult" id="difficult">
                <option value="">全部</option>
                <option value="0" >非困难</option>
                <option value="1" >困难</option>
                <option value="2" >非常困难</option>
            </select>
        </div>

        <label class="layui-form-label" style="margin-left: 85px">党内职务</label>
        <div class="layui-input-inline">
            <select name="party_posts" id="party_posts">
                <option value="">党内职务</option>
                <option value="书记">书记</option>
                <option value="副书记">副书记</option>
                <option value="委员">委员</option>
                <option value="普通党员">普通党员</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item input_row_margin_top">

        <label class="layui-form-label" >所属班子</label>
        <div class="layui-input-inline">
            <select name="partyGroups_name" id="partyGroups_name">
                <option value="">没有则不选</option>
            </select>
        </div>

        <label class="layui-form-label" style="margin-left: 85px">所属小组</label>
        <div class="layui-input-inline">
            <select name="partyTeam_name" id="partyTeam_name">
                <option value="">没有则不选</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item input_row_margin_top">


        <div class="layui-input-inline" style="margin-left: 116px;">
            <button type="button" style="background-color: #5fb878" class="layui-btn" id="test1">
                <i class="layui-icon">&#xe67c;</i>上传头像
            </button>
        </div>

    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">用户简介</label>
        <div class="layui-input-inline">
            <textarea name="profiles" id="profiles" placeholder="请输入用户简介"
                      style="width: 592px; border:1px solid #e6e6e6; font-size: 10px; line-height: 23px;color: #0c060f;
                              max-width: 1500px; height: 79px; max-height: 1000px; outline: 0;"></textarea>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-form-item input_row_margin_top">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>

</form>

<script>

var headImg = [];

    $(function() {

        if (parent.PartitionData) {
            $("#username").val(parent.PartitionData.username);
            $("#age").val(parent.PartitionData.age);
            $("#company").val(parent.PartitionData.company);
            $("#phone").val(parent.PartitionData.phone);
            $("#ID_cord").val(parent.PartitionData.ID_cord);
            $("#join_time").val(parent.PartitionData.join_time);
            $("#dutyid").val(parent.PartitionData.dutyid);
            $("#id").val(parent.PartitionData.id);
            $("#profiles").val(parent.PartitionData.profiles);
            $("#company_office").val(parent.PartitionData.company_office);
            $("#party_posts").val(parent.PartitionData.party_posts);

        }

        function canvasDataURL(file, callback) { //压缩转化为base64
            var reader = new FileReader()
            reader.readAsDataURL(file)
            reader.onload = function (e) {
                const img = new Image()
                const quality = 0.8 // 图像质量
                const canvas = document.createElement('canvas')
                const drawer = canvas.getContext('2d')
                img.src = this.result
                img.onload = function () {
                    canvas.width = img.width
                    canvas.height = img.height
                    drawer.drawImage(img, 0, 0, canvas.width, canvas.height)
                    convertBase64UrlToBlob(canvas.toDataURL(file.type, quality), callback);
                }
            }
        }

        function convertBase64UrlToBlob(urlData, callback) { //将base64转化为文件格式
            const arr = urlData.split(',')
            const mime = arr[0].match(/:(.*?);/)[1]
            const bstr = atob(arr[1])
            let n = bstr.length
            const u8arr = new Uint8Array(n)
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n)
            }
            callback(new Blob([u8arr], {
                type: mime
            }));
        }


        layui.use(['laydate', 'layer','upload', 'table', 'carousel', 'element', 'form'], function () {
            var form = layui.form;
            var upload = layui.upload;
            var laydate = layui.laydate //日期
                    ,layer = layui.layer //弹层
                    ,element = layui.element;//元素操作

            laydate.render({
                elem: '#join_time'
                ,btns: ['confirm']
                ,theme: 'grid'
                ,trigger: 'click'
            });

            //执行实例
            var uploadInst = upload.render({
                elem: '#test1' //绑定元素
                , url: '/upload/uploadImage' //上传接口
                , auto: false
                , choose: function (obj) {  //选择图片后事件
                    var files = obj.pushFile(); //将每次选择的文件追加到文件队列

                    $('#upload_imgs').prop('disabled', false);

                    //预读本地文件
                    obj.preview(function (index, file, result) {

                        if (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion.split(";")[1]
                                .replace(/[ ]/g, "").replace("MSIE", "")) < 9) {
                            return obj.upload(index, file)
                        }

                        canvasDataURL(file, function (blob) {
                            var aafile = new File([blob], file.name, {
                                type: file.type
                            })
                            var isLt1M;
                            if (file.size < aafile.size) {
                                isLt1M = file.size
                            } else {
                                isLt1M = aafile.size
                            }

                            if (isLt1M / 1024 / 1024 > 2) {
                                return layer.alert('上传图片过大！')
                            } else {
                                if (file.size < aafile.size) {
                                    return obj.upload(index, file)
                                }
                                obj.upload(index, aafile)
                            }
                        })

                    });
                }
                ,done: function(res){
                    //上传完毕回调
                    headImg.push(res.src);
                    layer.tips('上传成功 (>‿◠)✌', '#test1', {
                        tips: [2, '#3595CC'],
                        time: 1000
                    });
                     // layer.msg("上传成功！");
                }
                ,error: function(){
                    //请求异常回调
                    layer.msg("上传失败！");
                }
            });



            if (parent.PartitionData) {
                if (parent.PartitionData.sex == '男'){
                    var sex = 1;
                }else if(parent.PartitionData.sex == '女'){
                    var sex = 2;
                }
                $("input:radio[value='"+sex+"']").prop("checked", "checked");
                form.render();
            }

            if (parent.PartitionData) {
                if (parent.PartitionData.education == '初中'){
                    var education = 1;
                }else if(parent.PartitionData.education == '高中'){
                    var education = 2;
                }else if(parent.PartitionData.education == '中专'){
                    var education = 3;
                }else if(parent.PartitionData.education == '大专'){
                    var education = 4;
                }else if(parent.PartitionData.education == '本科'){
                    var education = 5;
                }else if(parent.PartitionData.education == '硕士'){
                    var education = 6;
                }else if(parent.PartitionData.education == '博士'){
                    var education = 7;
                }
                $("#education").val(education);
                form.render();
            }

            if (parent.PartitionData) {
                if (parent.PartitionData.difficulty_type == '非困难'){
                    var difficulty_type = 0;
                }else if(parent.PartitionData.difficulty_type == '困难'){
                    var difficulty_type = 1;
                }else if(parent.PartitionData.difficulty_type == '非常困难'){
                    var difficulty_type = 2;
                }
                $("#difficult").val(difficulty_type);
                form.render();
            }

            //查询政治面貌种类
            $.ajax({
                url: "/duty/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {

                         if(parent.PartitionData != undefined && item.id == parent.PartitionData.dutyid) {
                             $("#type_name").append("<option selected='selected'  value='" + item.id + "'>" + item.type_name + "</option>");
                         }else{
                             $("#type_name").append("<option  value='" + item.id + "'>" + item.type_name + "</option>");
                         }
                    });
                    form.render('select');
                }
            });

            //查询支部班子种类
            $.ajax({
                url: "/partyGroup/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                         if(parent.PartitionData != undefined && item.id == parent.PartitionData.party_groups_id) {
                             $("#partyGroups_name").append("<option selected='selected'  value='" + item.id + "'>" + item.name + "</option>");
                         }else{
                             $("#partyGroups_name").append("<option  value='" + item.id + "'>" + item.name + "</option>");

                         }
                    });
                    form.render('select');
                }
            });

            //查询党小组种类
            $.ajax({
                url: "/partyTeam/allCategory",
                async: false,
                success: function (data) {
                    $.each(data.list, function (i, item) {
                        if(parent.PartitionData != undefined && item.id == parent.PartitionData.party_team_id) {
                            $("#partyTeam_name").append("<option selected='selected'  value='" + item.id + "'>" + item.name + "</option>");
                        }else{
                            $("#partyTeam_name").append("<option  value='" + item.id + "'>" + item.name + "</option>");
                        }
                    });
                    form.render('select');
                }
            });



            form.on('submit(formDemo)', function () {

                if(headImg.length == 0){
                    if(parent.PartitionData == undefined || parent.PartitionData == ""){
                        var defaultHeadImg = "/images/defaultHead.jpg";
                        headImg.push(defaultHeadImg);
                    }else{
                        headImg.push(parent.PartitionData.head_img);
                    }
                }

                $.ajax({
                            url: "/user/addUser",
                            type:'post',
                            data: {
                                id: $("#id").val(),
                                username: $("#username").val(),
                                age: $("#age").val(),
                                education: $("#education").val(),
                                age: $("#age").val(),
                                dutyid : $("#type_name").val(),
                                difficult : $("#difficult").val(),
                                partyGroupsId : $("#partyGroups_name").val(),
                                partyTeamId : $("#partyTeam_name").val(),
                                join_time: $("#join_time").val(),
                                sex: $('input[name="sex"]:checked').val(),
                                ID_cord: $("#ID_cord").val(),
                                company: $("#company").val(),
                                company_office: $("#company_office").val(),
                                party_posts: $("#party_posts").val(),
                                profiles: $("#profiles").val(),
                                head_img: headImg.toString(),
                                phone: $("#phone").val()
                            },
                            success: function () {
                                layer.msg('保存成功', {icon: 1});
                                setTimeout(function () {
                                     window.parent.location.reload();
                                    // window.location.href = '/frame/userList.ftl'
                                }, 1500);
                            }
                        });

                return false;
            });
        });
    });


</script>

</body>
</html>