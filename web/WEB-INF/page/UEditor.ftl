<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
<!--    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">-->
    <title>思齐党建后台 editor</title>
    <script type="text/javascript" src="../../ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="../../ueditor/ueditor.all.js"></script>

    <style type="text/css">
        div{
            width:90%;
        }
    </style>
</head>


<body>
    <div>
        <script id="editor1" name="content" type="text/plain"></script>
    </div>

    <div class="details">
        <div>
            <script type="text/plain" id="editor2"></script>
        </div>
    </div>

    <div id="content" class="w900 border-style1 bg">
        <div class="section">
            <div class="details">
                <div>
                    <script type="text/plain" id="editor"></script>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript">

        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，
        // 如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var ue = UE.getEditor('editor');

        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function(action) {
            if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
                return 'http://localhost:8080/ueditor/imgUpload';
                //'http://localhost:8080/imgUpload';为方法imgUpload的访问地址
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }

    </script>



</body>

</html>