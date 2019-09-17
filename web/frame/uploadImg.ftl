

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>上传图片Test页面</title>
    <link rel="stylesheet" href="/js/layui/css/layui.css">
    <link rel="stylesheet" href="/css/public.css">

    <script src="/js/layui/layui.js"></script>
    <script src="/js/jquery/jquery-3.3.1.min.js"></script>


    <title>上传图片（最多可以上传5张图片）</title>
    <style>
        * {margin:0;padding:0;}
        .up-section .type-upimg {display:none;}
        ::-ms-clear,::-ms-reveal {display:none;}
        textarea {outline:none;line-height:14px;padding-left:4px;padding-top:4px;border:1px solid #ccc;color:#444;font-size:14px;outline:none;text-align:left;}
        .overflow {overflow:hidden;text-overflow:ellipsis;white-space:nowrap;}
        button {outline:none;border:0px;}
        input {outline:none;}
        a {outline:none;}
        a,button {cursor:pointer;}
        body {background:#f4f4f4;font-size:14px;}
        .clear {clear:both;}
        .full {width:1210px;min-width:1210px;margin:0 auto;}
        .full-big {width:1340px;min-width:1340px;margin:0 auto;}
        .img-full {display:block;width:100%;}
        ::-webkit-input-placeholder {color:#777;font-size:14px;}
        :-moz-placeholder {/* Firefox 18- */color:#777;font-size:14px;}
        ::-moz-placeholder {/* Firefox 19+ */color:#777;font-size:14px;}
        :-ms-input-placeholder {color:#777;font-size:14px;}
        /* ====clear float====== *//*nav a:visited {color:rgb(65,65,65);}
        aside a:visited {color:rgb(65,65,65);}
        */.fl {float:left;}
        .fr {float:right;}
        .clear:after {content:'';display:block;clear:both;}
        /* reset */.pic img {display:none;}
        em {font-style:normal}
        li {list-style:none}
        a {text-decoration:none;}
        img {border:none;}
        table {border-collapse:collapse;}
        /*上传图片插件的样式*/.img-box {margin-top:40px;}
        .img-box .up-p {margin-bottom:20px;font-size:16px;color:#555;}
        .z_photo {padding:18px;border:2px dashed #E7E6E6;}
        .z_photo .z_file {position:relative;}
        .z_file  .file {width:50%;height:50%;opacity:0;position:absolute;top:0px;left:0px;z-index:100;}
        .z_photo .up-section {position:relative;margin-right:20px;margin-bottom:20px;}
        .up-section .close-upimg {position:absolute;top:6px;right:8px;display:none;z-index:10;}
        .up-section .up-span {display:block;width:100%;height:100%;visibility:hidden;position:absolute;top:0px;left:0px;z-index:9;background:rgba(0,0,0,.5);}
        .up-section:hover {border:2px solid #f15134;}
        .up-section:hover .close-upimg {display:block;}
        .up-section:hover .up-span {visibility:visible;}
        .z_photo .up-img {display:block;width:100%;height:100%;}
        .loading {border:1px solid #D1D1D1;background:url(https://cs.m.xczhihui.com/xcview/images/shop/loading.gif) no-repeat center;}
        .up-opcity {opacity:0;}
        .img-name-p {display:none;}
        .upimg-div .up-section {width:190px;height:180px;}
        .img-box .upimg-div .z_file {width:190px;height:180px;}
        .z_file .add-img {display:block;width:190px;height:180px;}
        /*遮罩层样式*/.mask {z-index:1000;display:none;position:fixed;top:0px;left:0px;width:100%;height:100%;background:rgba(0,0,0,.4);}
        .mask .mask-content {width:500px;position:absolute;top:50%;left:50%;margin-left:-250px;margin-top:-80px;background:white;height:160px;text-align:center;}
        .mask .mask-content .del-p {color:#555;height:94px;line-height:94px;font-size:18px;border-bottom:1px solid #D1D1D1;}
        .mask-content .check-p {height:66px;line-height:66px;position:absolute;bottom:0px;left:0px;width:100%;}
        .mask-content .check-p span {width:49%;display:inline-block;text-align:center;color:#d4361d;font-size:18px;}
        .check-p .del-com {border-right:1px solid #D1D1D1;}
    </style>
</head>
<body>
<div class="img-box full">
    <section class=" img-section">
        <p class="up-p"><span class="up-span">上传图片（最多可以上传5张图片）</span></p>
        <div class="z_photo upimg-div clear">
            <section class="z_file fl">
                <img src="" class="add-img">
                <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" multiple="">
            </section>
        </div>
        <button type="button" class="layui-btn" id="uploadImg">上传图片</button>
    </section>
</div>
<aside class="mask works-mask">
    <div class="mask-content">
        <p class="del-p ">您确定要删除图片吗？</p>
        <p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span></p>
    </div>
</aside>
</body>
</html>
<!--<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>-->
<script>
    $(function(){
        var delParent;
        var defaults = {
            fileType: ["jpg","png","bmp","jpeg"],   // 上传文件的类型
            fileSize: 1024 * 1024 * 1                  // 上传文件的大小 10M
        };
        /*点击图片的文本框*/
        $(".file").change(function(){
            var idFile = $(this).attr("id");
            var file = document.getElementById(idFile);
            var imgContainer = $(this).parents(".z_photo"); //存放图片的父亲元素
            var fileList = file.files; //获取的图片文件
            var input = $(this).parent();//文本框的父亲元素
            var imgArr = [];

            //遍历得到的图片文件
            var numUp = imgContainer.find(".up-section").length;
            var totalNum = numUp + fileList.length;  //总的数量
            if(fileList.length > 5 || totalNum > 5 ){
                alert("上传图片数目不可以超过5个，请重新选择");  //一次选择上传超过5个 或者是已经上传和这次上传的到的总数也不可以超过5个
            }else if(numUp < 5){
                fileList = validateUp(fileList);
                for(var i = 0;i<fileList.length;i++){
                    var imgUrl = window.URL.createObjectURL(fileList[i]);
                    imgArr.push(imgUrl);
                    var $section = $("<section class='up-section fl loading'>");
                    imgContainer.prepend($section);
                    var $span = $("<span class='up-span'>");
                    $span.appendTo($section);

                    var $img0 = $("<img class='close-upimg'>").on("click",function(event){
                        event.preventDefault();
                        event.stopPropagation();
                        $(".works-mask").show();
                        delParent = $(this).parent();
                    });
                    $img0.attr("src","").appendTo($section);
                    var $img = $("<img class='up-img up-opcity'>");
                    $img.attr("src",imgArr[i]);
                    $img.appendTo($section);
                    var $p = $("<p class='img-name-p'>");
                    $p.html(fileList[i].name).appendTo($section);
                    var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
                    $input.appendTo($section);
                    var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                    $input2.appendTo($section);

                }
            }
            setTimeout(function(){
                $(".up-section").removeClass("loading");
                $(".up-img").removeClass("up-opcity");
            },450);
            numUp = imgContainer.find(".up-section").length;
            if(numUp >= 5){
                $(this).parent().hide();
            }


            for(var i = 0;i<fileList.length;i++){
                var formData = new FormData();
                formData.append("file:"+i,fileList[i]);
            }
            console.log(formData);
            // JSON.stringify(fileList);
            // console.log("2"+fileList);


            //点击上传
            $("#uploadImg").click(function () {
                $.ajax({
                    url: '/upload/uploadImage',
                    type: 'post',
                    data: {
                         formData
                    },
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (result) {
                        alert("成功");
                    }
                });

            });


        });

        $(".z_photo").delegate(".close-upimg","click",function(){
            $(".works-mask").show();
            delParent = $(this).parent();
        });

        $(".wsdel-ok").click(function(){
            $(".works-mask").hide();
            var numUp = delParent.siblings().length;
            if(numUp < 6){
                delParent.parent().find(".z_file").show();
            }
            delParent.remove();
        });

        $(".wsdel-no").click(function(){
            $(".works-mask").hide();
        });

        function validateUp(files){
            var arrFiles = [];//替换的文件数组
            for(var i = 0, file; file = files[i]; i++){
                //获取文件上传的后缀名
                var newStr = file.name.split("").reverse().join("");
                if(newStr.split(".")[0] != null){
                    var type = newStr.split(".")[0].split("").reverse().join("");
                    console.log(type+"===type===");
                    if(jQuery.inArray(type, defaults.fileType) > -1){
                        // 类型符合，可以上传
                        if (file.size >= defaults.fileSize) {
                            alert(file.size);
                            alert('您这个"'+ file.name +'"文件大小过大');
                        } else {
                            // 在这里需要判断当前所有文件中
                            arrFiles.push(file);
                        }
                    }else{
                        alert('您这个"'+ file.name +'"上传类型不符合');
                    }
                }else{
                    alert('您这个"'+ file.name +'"没有类型, 无法识别');
                }
            }
            return arrFiles;
        }






    });

</script>
