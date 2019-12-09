package test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.*;
import com.jfinal.json.Json;
import entity.Student;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: Java8新特性
 * @author: daimin
 * @date: Create in 17:00 2019/12/3
 */
public class StreamTest {
    public static void main(String[] args) {
        try {
            String str = null;
            str = Optional.ofNullable(str).orElse("success");
            System.out.println(str);

            Student student = null;
            student = Optional.ofNullable(student).orElse(new Student("李四", 26, "F"));
            System.out.println(student.getName());


            String json = getJson();
            Map<String, Object> order = JSONUtil.parseObj(json);
            Stream.of(order).map((x) -> x.get("train_tickets")).collect(Collectors.toList()).stream().flatMap(StreamTest::getChangeTics);
            List<Map<String, Object>> tics = (List<Map<String, Object>>) Stream.of(order).map((x) -> x.get("train_tickets")).map(StreamTest::getChangeTics).collect(Collectors.toList()).get(0).collect(Collectors.toList());

            //老订单数据
            String newJson = getNewJson();
            Map<String, Object> oldOrder = JSONUtil.parseObj(newJson);
            List<Map<String, Object>> oldTics = (List<Map<String, Object>>) oldOrder.get("train_tickets");

            tics.stream().forEach((x) -> {
                try {
                    String passportSeno = getString(x.get("passportseno"), "");
                    oldTics.forEach((y) -> {
                        if (passportSeno.equals(y.get("passportseno"))) {
                            x.put("old_ticket_no", y.get("ticket_no"));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

//            tics.forEach(System.out::println);
            List<String> r = new ArrayList<>();
            oldOrder.forEach((k,v)->{
                if (k.equals("train_tickets")){
                    JSONArray list = (JSONArray) v;
                    Iterator iter = list.iterator();
                    while (iter.hasNext()){
                        JSONObject temp = (JSONObject) iter.next();
                        r.add(temp.get("zwcode")+"");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stream getChangeTics(Object obj) {
        List<Map> str = (List<Map>) obj;
        List<Map> changeTics = new ArrayList<>();
        str.stream().forEach((x) -> {
            Map changeTic = new HashMap();
            changeTic.put("passengersename", x.get("passengersename"));
            changeTic.put("passportseno", x.get("passportseno"));
            changeTic.put("passporttypeseid", x.get("passporttypeseid"));
            changeTic.put("piaotype", x.get("piaotype"));
            changeTic.put("old_ticket_no", "");
            changeTics.add(changeTic);
        });
        return changeTics.stream();
    }

    public static String getJson() {
        String json = " {\"formid\":\"CLSQDXCMX06-su1-191009-0002\",\"queryKey\":null,\"createUserId\":\"5461a84e-9d7f-4f0b-a159-450071f70215\",\"fee_invoice_type\":\"\",\"orderstatus\":\"1\",\"arrive_date\":\"11-01\",\"fee\":5.00,\"to_city\":\"苏州\",\"createdate\":\"2019-10-09 11:40:33\",\"start_week\":\"星期五\",\"standard_user_phone\":null,\"serviceprovider_msg\":\"\",\"to_station_code\":\"SZH\",\"createdby\":\"1e468aca-7a5f-491d-861f-f93bd6bd9faf\",\"orderamount\":14.50,\"smsPhone\":null,\"train_date\":\"2019-11-01\",\"payid\":\"\",\"orderstatusname\":\"待订票\",\"traintime\":\"2019-11-01 09:16:00\",\"run_time\":\"01:02\",\"serviceprovider_status\":null,\"LoginUserPassword\":\"\",\"from_city\":\"上海\",\"is_choose_seats\":false,\"createdorgcode\":\"5c02378b-9fbe-cd39-2882-08d658e37a82\",\"smsEmail\":null,\"createdname\":\"张素姿\",\"serviceprovider_status_name\":null,\"arrive_time\":\"10:18:00\",\"start_time\":\"09:16:00\",\"endGeoId\":\"L00522\",\"LoginUserName\":\"\",\"train_type\":\"K\",\"to_station_name\":\"苏州\",\"standard_user_name\":null,\"arrive_week\":\"星期五\",\"from_station_name\":\"上海\",\"createCompanyName\":\"共享测试企业\",\"endStationPinYin\":\"suzhou\",\"createdphone\":\"18569520012\",\"transactionid\":\"\",\"fee_tax_rate\":0.00,\"train_no\":\"550000K516A2\",\"fail_code\":null,\"ordernumber\":\"E255205573\",\"is_nigth_flag\":false,\"choose_seats\":null,\"train_code\":\"K516\",\"start_date\":\"11-01\",\"last_update_date\":\"2019-10-09 11:40:34\",\"fromCityPinYin\":\"ShangHaiShi\",\"fail_msg\":null,\"find_status\":1,\"run_time_minute\":62,\"orderid\":\"1219100911407510230\",\"accountCompanyId\":\"1000000012285728\",\"pay_company_id\":\"5c02378b-9fbe-cd39-2882-08d658e37a82\",\"startGeoId\":\"L00509\",\"is_accept_standing\":null,\"from_station_code\":\"SHH\",\"fromStationPinYin\":\"shanghai\",\"train_tickets\":[{\"zwcode\":\"1\",\"insurance\":null,\"reason\":\"\",\"distric\":\"\",\"city\":\"\",\"recipientname\":\"\",\"isOuter\":1,\"userkey\":\"1074\",\"recipientphone\":\"18721896795\",\"statusid\":8,\"province\":\"\",\"ticket_no\":\"\",\"price\":14.500,\"passengersename\":\"孙玥\",\"insurancetype\":null,\"id\":40230,\"last_update_date\":\"2019-10-09 11:40:34\",\"ticket_status\":\"待核实\",\"isneedinsure\":false,\"orderid\":\"1219100911407510230\",\"isoutstandard\":0,\"passporttypeseidname\":\"二代身份证\",\"zwname\":\"硬座\",\"piaotype\":\"1\",\"piaotypename\":\"成人票\",\"insurancedesc\":null,\"isVip\":0,\"zipcode\":null,\"passportseno\":\"321084199403141726\",\"passporttypeseid\":\"1\",\"passenger_stdnt_id\":null,\"cxin\":\"\",\"travelStandard\":\"\",\"passengerId\":\"\",\"detailedaddress\":\"\"},{\n" +
                "            \"zwcode\":\"1\",\n" +
                "            \"insurance\":null,\n" +
                "            \"reason\":\"\",\n" +
                "            \"distric\":\"\",\n" +
                "            \"city\":\"\",\n" +
                "            \"recipientname\":\"\",\n" +
                "            \"isOuter\":1,\n" +
                "            \"userkey\":\"1074\",\n" +
                "            \"recipientphone\":\"18721896795\",\n" +
                "            \"statusid\":8,\n" +
                "            \"province\":\"\",\n" +
                "            \"ticket_no\":\"\",\n" +
                "            \"price\":14.5,\n" +
                "            \"passengersename\":\"张素资\",\n" +
                "            \"insurancetype\":null,\n" +
                "            \"id\":40230,\n" +
                "            \"last_update_date\":\"2019-10-09 11:40:34\",\n" +
                "            \"ticket_status\":\"待核实\",\n" +
                "            \"isneedinsure\":false,\n" +
                "            \"orderid\":\"1219100911407510230\",\n" +
                "            \"isoutstandard\":0,\n" +
                "            \"passporttypeseidname\":\"二代身份证\",\n" +
                "            \"zwname\":\"硬座\",\n" +
                "            \"piaotype\":\"1\",\n" +
                "            \"piaotypename\":\"成人票\",\n" +
                "            \"insurancedesc\":null,\n" +
                "            \"isVip\":0,\n" +
                "            \"zipcode\":null,\n" +
                "            \"passportseno\":\"123456789\",\n" +
                "            \"passporttypeseid\":\"1\",\n" +
                "            \"passenger_stdnt_id\":null,\n" +
                "            \"cxin\":\"\",\n" +
                "            \"travelStandard\":\"\",\n" +
                "            \"passengerId\":\"\",\n" +
                "            \"detailedaddress\":\"\"\n" +
                "        }],\"endCityPinYin\":\"SuZhouShi\",\"serviceprovider_flag\":\"HTHY\",\"accountCompanyName\":\"研发中心\"}";
        return json;
    }

    public static String getNewJson() {
        String newJson = " {\"formid\":\"CLSQDXCMX06-su1-191009-0002\",\"queryKey\":null,\"createUserId\":\"5461a84e-9d7f-4f0b-a159-450071f70215\",\"fee_invoice_type\":\"\",\"orderstatus\":\"1\",\"arrive_date\":\"11-01\",\"fee\":5.00,\"to_city\":\"苏州\",\"createdate\":\"2019-10-09 11:40:33\",\"start_week\":\"星期五\",\"standard_user_phone\":null,\"serviceprovider_msg\":\"\",\"to_station_code\":\"SZH\",\"createdby\":\"1e468aca-7a5f-491d-861f-f93bd6bd9faf\",\"orderamount\":14.50,\"smsPhone\":null,\"train_date\":\"2019-11-01\",\"payid\":\"\",\"orderstatusname\":\"待订票\",\"traintime\":\"2019-11-01 09:16:00\",\"run_time\":\"01:02\",\"serviceprovider_status\":null,\"LoginUserPassword\":\"\",\"from_city\":\"上海\",\"is_choose_seats\":false,\"createdorgcode\":\"5c02378b-9fbe-cd39-2882-08d658e37a82\",\"smsEmail\":null,\"createdname\":\"张素姿\",\"serviceprovider_status_name\":null,\"arrive_time\":\"10:18:00\",\"start_time\":\"09:16:00\",\"endGeoId\":\"L00522\",\"LoginUserName\":\"\",\"train_type\":\"K\",\"to_station_name\":\"苏州\",\"standard_user_name\":null,\"arrive_week\":\"星期五\",\"from_station_name\":\"上海\",\"createCompanyName\":\"共享测试企业\",\"endStationPinYin\":\"suzhou\",\"createdphone\":\"18569520012\",\"transactionid\":\"\",\"fee_tax_rate\":0.00,\"train_no\":\"550000K516A2\",\"fail_code\":null,\"ordernumber\":\"E255205573\",\"is_nigth_flag\":false,\"choose_seats\":null,\"train_code\":\"K516\",\"start_date\":\"11-01\",\"last_update_date\":\"2019-10-09 11:40:34\",\"fromCityPinYin\":\"ShangHaiShi\",\"fail_msg\":null,\"find_status\":1,\"run_time_minute\":62,\"orderid\":\"1219100911407510230\",\"accountCompanyId\":\"1000000012285728\",\"pay_company_id\":\"5c02378b-9fbe-cd39-2882-08d658e37a82\",\"startGeoId\":\"L00509\",\"is_accept_standing\":null,\"from_station_code\":\"SHH\",\"fromStationPinYin\":\"shanghai\",\"train_tickets\":[{\"zwcode\":\"1\",\"insurance\":null,\"reason\":\"\",\"distric\":\"\",\"city\":\"\",\"recipientname\":\"\",\"isOuter\":1,\"userkey\":\"1074\",\"recipientphone\":\"18721896795\",\"statusid\":8,\"province\":\"\",\"ticket_no\":\"18871450913\",\"price\":14.500,\"passengersename\":\"孙玥\",\"insurancetype\":null,\"id\":40230,\"last_update_date\":\"2019-10-09 11:40:34\",\"ticket_status\":\"待核实\",\"isneedinsure\":false,\"orderid\":\"1219100911407510230\",\"isoutstandard\":0,\"passporttypeseidname\":\"二代身份证\",\"zwname\":\"硬座\",\"piaotype\":\"1\",\"piaotypename\":\"成人票\",\"insurancedesc\":null,\"isVip\":0,\"zipcode\":null,\"passportseno\":\"321084199403141726\",\"passporttypeseid\":\"1\",\"passenger_stdnt_id\":null,\"cxin\":\"\",\"travelStandard\":\"\",\"passengerId\":\"\",\"detailedaddress\":\"\"}],\"endCityPinYin\":\"SuZhouShi\",\"serviceprovider_flag\":\"HTHY\",\"accountCompanyName\":\"研发中心\"}";
        return newJson;
    }

    public static String getString(Object obj, String _default) throws Exception {
        return isEmpty(obj) ? _default : obj.toString();
    }
    public static boolean isEmpty(Object _obj) {
        if (_obj == null) {
            return true;
        } else if (_obj instanceof String) {
            return "null".equals(((String) _obj).toLowerCase()) ? false : "".equals(((String) _obj).trim());
        } else if (_obj instanceof Collection) {
            Collection map1 = (Collection) _obj;
            return map1.size() == 0;
        } else if (_obj instanceof Map) {
            Map map = (Map) _obj;
            return map.size() == 0;
        } else {
            return _obj.getClass().isArray() ? Array.getLength(_obj) == 0 : false;
        }
    }
}
