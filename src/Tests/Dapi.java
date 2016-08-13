package Tests;
import java.util.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
/**
 * Created by wentaod on 8/5/16.
 */
public class Dapi {

    static String inputPath = "/Users/wentaod/Desktop/xtrader/xtrader-50000.json";
    static String outputPath = "/Users/wentaod/Desktop/xtrader/dapi-50000.json";
    static JSONParser parser = new JSONParser();

    static Map<String, Integer> demoMapApp;
    static Map<String, Integer> demoMapDesk;
    static {
        demoMapApp = new HashMap<>();
        demoMapApp.put("14-19", 565);
        demoMapApp.put("20-24", 566);
        demoMapApp.put("25-29", 567);
        demoMapApp.put("30-34", 568);
        demoMapApp.put("35-39", 569);
        demoMapApp.put("40-44", 570);
        demoMapApp.put("45-49", 571);

        demoMapApp.put("m", 562);
        demoMapApp.put("f", 563);

        demoMapDesk = new HashMap<>();
        demoMapDesk.put("14-19", 565);
        demoMapDesk.put("20-24", 566);
        demoMapDesk.put("25-29", 567);
        demoMapDesk.put("30-34", 568);
        demoMapDesk.put("35-39", 569);
        demoMapDesk.put("40-44", 570);
        demoMapDesk.put("45-49", 571);

        demoMapDesk.put("m", 10000);
        demoMapDesk.put("f", 10001);
    }


    static public void main(String args[]) {
        BufferedReader inputputFile = null;
        FileWriter outputFile = null;

        int input = 0;
        int output = 0;
        try {
            inputputFile = new BufferedReader(new FileReader(inputPath));
            outputFile = new FileWriter(outputPath);
            String line;
            while ((line = inputputFile.readLine()) != null) {
                input++;
                JSONObject logObj = (JSONObject) parser.parse(line);
                JSONObject dapiObj = transform((JSONObject) logObj.get("bidderLog"));

                if (dapiObj != null) {
                    output++;
                    //System.out.println(dapiObj);
                    outputFile.write(dapiObj.toJSONString() + "\n");
                }

                System.out.println("Input: " + input + "\tOutput: " + output);
            }
            outputFile.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    static JSONObject transform(JSONObject bidderLog) throws Exception {
        JSONArray age = (JSONArray) bidderLog.get("age");
        JSONArray gender = (JSONArray) bidderLog.get("gender");
        //JSONArray age = null;
        //String ageStr = (String) bidderLog.get("age");
        //if (ageStr != null && !ageStr.isEmpty()) {
        //    age = (JSONArray) parser.parse(ageStr);
        //}
        //JSONArray gender = null;
        //String genderStr = (String) bidderLog.get("gender");
        //if (genderStr != null && !genderStr.isEmpty()) {
        //    gender = (JSONArray) parser.parse(genderStr);
        //}

        if ((age == null || age.isEmpty()) && (gender == null || gender.isEmpty())) {
            return null;
        }

        JSONObject requestObj = transformRequest(bidderLog.get("requestBytes").toString());
        JSONObject dapiObj = new JSONObject();

        // csid
        dapiObj.put("csid", "F09828");

        // uids
        JSONArray uids = transformUids(bidderLog);
        dapiObj.put("uids", uids);

        // locationUrl
        JSONObject siteObj = (JSONObject) requestObj.get("site");
        if (siteObj != null) {
            JSONObject pageObj = (JSONObject) siteObj.get("page");
            if (pageObj != null) {
                dapiObj.put("locationUrl", pageObj.toString());
            }
        }

        // userAgent & ip
        JSONObject deviceObj = (JSONObject) requestObj.get("device");
        if (deviceObj != null) {
            String uaObj = (String) deviceObj.get("ua");
            if (uaObj != null) {
                dapiObj.put("userAgent", uaObj);
            }
            String ipObj = (String) deviceObj.get("ip");
            if (ipObj != null) {
                dapiObj.put("ip", ipObj);
            }
        }

        // events
        JSONObject appObj = (JSONObject) requestObj.get("app");
        JSONArray events = transformEvents(age, gender, appObj);
        dapiObj.put("events", events);

        return dapiObj;
    }

    static JSONObject transformRequest(String requestBytes) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(requestBytes);
        String s = new String(bytes, "US-ASCII");
        return (JSONObject) parser.parse(s);
    }

    static JSONArray transformEvents(JSONArray age, JSONArray gender, JSONObject appObj) throws Exception {
        boolean mobile = appObj != null;
        JSONArray eventsObj = new JSONArray();
        if (age != null) {
            for (int i=0; i<age.size(); i++) {
                String ageStr = age.get(i).toString();
                int code = mobile? demoMapApp.get(ageStr) : demoMapDesk.get(ageStr);
                eventsObj.add("xtd.event=" + code);
            }
        }
        if (gender != null) {
            for (int i=0; i<gender.size(); i++) {
                String genderStr = gender.get(i).toString();
                int code = mobile? demoMapApp.get(genderStr) : demoMapDesk.get(genderStr);
                eventsObj.add("xtd.event=" + code);
            }
        }

        if (mobile && appObj.get("bundle") != null)
            eventsObj.add("xtd.bundle=" + appObj.get("bundle"));

        return eventsObj;
    }

    static JSONArray transformUids(JSONObject bidderLog) {
        JSONArray uidsObj = new JSONArray();
        String asiId = (String) bidderLog.get("asciUserId");
        if (asiId != null) {
            JSONObject uid = new JSONObject();
            uid.put("type", "ASI");
            uid.put("uid", asiId);
            uidsObj.add(uid);
        }
        String keyType = (String) bidderLog.get("keyType");
        String foreignUserId = (String) bidderLog.get("foreignUserId");
        if (foreignUserId != null && keyType != null) {
            JSONObject uid = new JSONObject();
            uid.put("type", keyType);
            uid.put("uid", foreignUserId);
            uidsObj.add(uid);
        }
        return uidsObj;
    }
}
