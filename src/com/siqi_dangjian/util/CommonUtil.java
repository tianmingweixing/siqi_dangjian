package com.siqi_dangjian.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {

    public static Timestamp getNowTimeStamp() {
        Date date = new Date();
        Timestamp tp = new Timestamp(date.getTime());
        return tp;
    }


    /**
     * 上传单张图片
     * @param file
     * @return path 返回图片路径
     * @throws IOException
     */
    public static String uploadImg(@RequestParam(value = "file", required = false) CommonsMultipartFile file,HttpServletRequest request) throws IOException {

            String fileName = file.getOriginalFilename();
            Calendar now = Calendar.getInstance();
            String year = String.valueOf(now.get(Calendar.YEAR));
            String month = String.valueOf(now.get(Calendar.MONTH) + 1);
            String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
            // 上传文件在服务器中的位置(目录绝对路径)
//          String saveServerPath = request.getSession().getServletContext().getRealPath(CommonString.FILE_PARENT_PATH);
            String saveServerPath = "F:/workspace/siqi_dangjian/web"+CommonString.FILE_PARENT_PATH+CommonString.FILE_IMAGE_PATH+year+month+day;

            File filePath = new File(new File(saveServerPath).getAbsolutePath() + "/" + fileName);//文件的完整路径
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            file.transferTo(filePath);
            String path =CommonString.FILE_PARENT_PATH+CommonString.FILE_IMAGE_PATH+year+month+day +"/"+ fileName;// 保存文件的相对路径
            return path;

    }

    /**
     * 截取类名,ex:com.util.User,截取为User
     * @param className
     * @return
     */
    public static String subStrClassName(String className) {
        int i;
        for (i = className.length() - 1; i > 0; i--) {
            if (className.charAt(i) == '.') {
                break;
            }
        }
        return className.substring(i + 1, className.length());
    }

    public static String appendBlurStr(String sql, Map map) {
        StringBuilder builder = new StringBuilder(sql);
        builder.append(" and");
        int count = 0;
        for (Object key : map.keySet()) {
            if (StringUtils.isNotEmpty((String) map.get(key))) {
                builder.append(" " + key + " like '%" + map.get(key) + "%' and");
                count++;
            }
        }
        int str_length = builder.toString().length();
        return builder.toString().substring(0, str_length - 4);//移除末尾的and
    }

    public static String appendBlurStrWithTable(String sql, Map map,String tableAlias) {
        StringBuilder builder = new StringBuilder(sql);
        builder.append(" and");
        int count = 0;
        for (Object key : map.keySet()) {
            if (StringUtils.isNotEmpty((String) map.get(key))) {
                builder.append(" " + tableAlias+"."+key + " like '%" + map.get(key) + "%' and");
                count++;
            }
        }
        int str_length = builder.toString().length();
        return builder.toString().substring(0, str_length - 4);//移除末尾的and
    }

    public static String appendDateStr(String sql, Map map, String table_alies) {
        StringBuilder builder = new StringBuilder(sql);
        builder.append(" and");
        for (Object key : map.keySet()) {
            if (null != map.get(key) && "" != map.get(key)) {
                if (key == "start_time" && StringUtils.isNotEmpty((String) map.get(key))) {
                    builder.append(" " + table_alies + ".year_limit>=str_to_date('" + map.get(key) + "','%Y-%m-%d') and");
                } else if (key == "end_time" && StringUtils.isNotEmpty((String) map.get(key))) {
                    builder.append(" " + table_alies + ".year_limit<=str_to_date('" + map.get(key) + "','%Y-%m-%d') and");
                }
            }
        }
        int str_length = builder.toString().length();
        return builder.toString().substring(0, str_length - 4);
        // here logon_time>=to_date('2018-04-19 00:00:00','yyyy-mm-dd hh24:mi:ss') and logon_time<to_date('2018-04-20 00:00:00','yyyy-mm-dd hh24:mi:ss')
    }

    public static String appendIntStr(String sql, Map map, String table_alies) {
        StringBuilder builder = new StringBuilder(sql);
        int flag = 0;
        builder.append(" and");
        for (Object key : map.keySet()) {
            if (key == "minPrice" && StringUtils.isNotEmpty((String) map.get(key))) {
                builder.append(" g.normal_price>=" + map.get(key) + " and");
            } else if (key == "maxPrice" && StringUtils.isNotEmpty((String) map.get(key))) {
                builder.append(" g.normal_price<=" + map.get(key) + " and");
            } else if (StringUtils.isNotEmpty((String) map.get(key))) {
                builder.append(" " + table_alies + "." + key + " = " + map.get(key) + " and");
            }
        }
        int str_length = builder.toString().length();
        return builder.toString().substring(0, str_length - 4);//移除末尾的and
    }

    public static String appendInSql(String sql, List list, String column) {
        StringBuilder builder = new StringBuilder(sql);
        builder.append(" where " + column + " in (");
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i) + ",");
        }
        builder = new StringBuilder(builder.substring(0, builder.toString().length() - 1));
        builder.append(")");
        return builder.toString();
    }

    public static Map queryList(Session session, String sql, String sqlCount, int limit, int page) {
        SQLQuery query = session.createSQLQuery(sql);
        SQLQuery query1 = session.createSQLQuery(sqlCount);
        query.setFirstResult(limit * (page - 1));
        query.setMaxResults(limit);
        List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        BigInteger temp = (BigInteger) query1.uniqueResult();
        int count = temp.intValue();
        Map res = new HashMap();
        res.put("list", list);
        res.put("count", count);
        return res;
    }

    public static String wirteImage(String path, String extension) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
//            URL url = new URL(path);
            byte[] bytes = new byte[1024];
//            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("get");
//            conn.setConnectTimeout(5000);
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            int len = -1;
            while ((len = is.read(bytes)) != -1) {
                data.write(bytes, 0, len);
            }
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String preix = "data:image/" + extension + ";base64,";
        String base64 = encoder.encode(data.toByteArray());
        return preix + base64;
    }

    public static boolean checkToken(RedisCacheManager redisCacheManager, String redis_kay, String redis_value) {
        if (redisCacheManager.hasKey(redis_kay)) {
            if (redisCacheManager.get(redis_kay).equals(redis_value)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static Date StringToDate(String dateStr,String format) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
    }


    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static Map<String,String>  doXMLParse(String strxml) throws Exception {
        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map<String,String> m = new HashMap<String,String>();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }

    private static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    @SuppressWarnings("rawtypes")
    private static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    /**
     *
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap   要排序的Map对象
     * @param urlEncode   是否需要URLENCODE
     * @param keyToLower    是否需要将Key转换为全小写
     *            true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower){
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try
        {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
            {
                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
                {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds)
            {
                if (StringUtils.isNotBlank(item.getKey()))
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode)
                    {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower)
                    {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else
                    {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false)
            {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e)
        {
            return null;
        }
        return buff;
    }

    public static String timeStrToDateStr(String timeStr){
        if (null == timeStr) {
            return null; }
        System.out.print(timeStr);

        String dateStr = null;
        SimpleDateFormat sdf_input = new SimpleDateFormat("yyyyMMddhhmmss");//输入格式 SimpleDateFormat sdf_target =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //转化成为的目标格式
        SimpleDateFormat sdf_target =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = sdf_target.format(sdf_input.parse(timeStr));
            System.out.println(dateStr);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return dateStr;
    }
}
