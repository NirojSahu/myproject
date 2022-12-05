package com.test.Utils;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
//import org.ho.yaml.Yaml;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.jayway.jsonpath.JsonPath.read;


public class YamlReader {
    public static final String USER_DIRECTORY= System.getProperty("user.dir")+ File.separator;
    public static String YML_Path = USER_DIRECTORY+"src/test/resources/src_test_resources_TestData_headerinfo.yml";

        public static void main(String args[]) throws IOException {
            YamlReader obj=new YamlReader();
            obj.ymlRead();
        }
        public void ymlRead() throws IOException {
            Yaml yaml = new Yaml();
            Map<String, Object> res = new HashMap<String, Object>();
            File file = new File(YML_Path);
            Map<String, Object> map =yaml.load(String.valueOf(file));
//                    (Map<String, Object>) Yaml.load(new File(YML_Path));
            for(Map.Entry<String,Object> m:map.entrySet()){
                System.out.println(m.getKey() +" values "+
                        m.getValue());
                System.out.println("--------------------");
            }
            //map to Json
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonStr= new String("default");
//            try {
//                jsonStr = objectMapper.writeValueAsString(map);
//                System.out.println(jsonStr);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
            String jsonStr = JsonUtilities.getJsonStringfromPojo(map);
//            jsonStr=jsonStr.replace("\\", "");
           //JSONObject json = new JSONObject(jsonStr);
            //String jsonone=json.toString();
            Object jsonTwo = JsonPath.read(jsonStr, "$.Resources.account-access-consents_1.headers");
          // String jsonTwo=jsonTwo.replace("\\", "");
            System.out.println(jsonTwo);
        }
}
