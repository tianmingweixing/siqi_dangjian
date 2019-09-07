package com.siqi_dangjian.util;

public class CommonString {

    public final static String RESULT_SUCCESS = "success";
    public final static String RESULT_FAIL = "fail";
    public final static int ADMIN_USER_TYPE = 1;
    public final static int WX_USER_TYPE = 2;
    public final static String SAVEORDE_NOMORESTOCK = "商品库存不足";
    public final static String SAVEORDE_ORDER_PRICE = "商品价格计算异常";
    public final static String SAVEORDE_SYSTEMERROR = "系统异常";
    public final static int TOKEN_CHECK_FAIL = 201;//redis无用户信息
    public final static int REQUEST_SUCCESS = 0;//请求成功
    public final static int SYSTEM_EXPECTION = 202;//系统异常
    public final static int INVILD_CODE = 203;//不合法的用户code
    public final static int WX_USER_NOT_EXIT = 204;//用户不存在
    public final static int WX_SERVICE_EXPECTION = 205;//用户不存在
    public final static String ORDER_HAS_SEND = "商家已发货";
    public final static String BACK_GOOD_SUCCESS = "退货成功";
    public final static String BACK_ORDER_FAIL = "退货失败";
    public final static Integer FILETYPE_IMAGE = 1;//文件中的图片类型
    public final static Integer FILETYPE_VEDIO = 1;//文件中的视频类型
    public final static String FILE_PARENT_PATH = "/home/up_load/";//文件上传的全局路径
    public final static String FILE_IMAGE_PATH = "image/";//文件上传的图片子路径
    public final static String FILE_VEDIO_PATH = "/video/";//文件上传的视频子路径
    public final static String BILL_NO_PRE = "DFBILL";
    public final static String PAY_NO_PRE = "DFPAY";
    public final static String BACK_NO_PRE = "DFBACK";
    public final static String WX_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public final static String WX_BACK_URL = "https://api.maotouying.com/secapi/pay/refund";//退款url
    public final static String APP_ID ="wx7f5643445c60911c";//小程序appId
    public final static String APP_SECRET="8ea6be66d2b52d38a2972de0325c2fe0";//小程序secret
    public final static String MCH_ID ="1539812551";//微信支付商户Id
    public final static String APP_PAY_TYPE="JSAPI";
    public final static String APP_WX_PAYMENT_BODY="丁府茶行-用户购买商品";
    public final static String APP_SPBILL_CREATE_IP = "118.190.151.241";
    public final static String APP_WX_PAY_NOTIFY_URL = "https://demo.yz-demo.ml/mini/weixinotify";
    public final static String BUSINESS_REC_NAME_CODE = "business_rec_name";
    public final static String BUSINESS_REC_PHONE_CODE = "business_rec_phone";
    public final static String BUSINESS_REC_ADDRESS_CODE = "business_rec_address";
    public final static String ALI_DELIVER_APPSCRET = "459c8aa9e3d44de3bfdecb95070d2431";//阿里物流Appscret
    public final static String DELIVER_QUERY_EXCEPTION = "物流查询异常";
    public final static String WX_PAY_SING_KEY = "0ShiningVeins7FrightfulRuckusw21";

    //日志
    public final static boolean IS_OPEN_LOG = true;//1:打开；0：关闭


}
