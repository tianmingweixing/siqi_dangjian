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

