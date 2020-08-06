package Utilities;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.ho.yaml.Yaml;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.util.*;


public class JsonUtilities {
    public static JsonNode loadResource(String fileAbsPath) throws IOException {
        return JsonLoader.fromResource(fileAbsPath);
    }
    public static JsonNode loadResourceformString(String json) throws IOException {
        return JsonLoader.fromResource(json);
    }
    public static ProcessingReport validateScema(Response res,String fileAbsPath) throws IOException, ProcessingException {
        JsonSchemaFactory fectory=JsonSchemaFactory.byDefault();
        JsonSchema schema=fectory.getJsonSchema(loadResource(fileAbsPath));
        ProcessingReport report=schema.validate(getJsonNodefromString(res.getBody().asString()));
        return report;
    }

    public static JsonNode getJsonNodefromString(String jsonString) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        JsonFactory factory= mapper.getFactory();
        JsonParser parser=factory.createParser(jsonString);
        JsonNode actualObj=mapper.readTree(parser);
        return actualObj;
    }

    public static String getjsonStringfromPojo(Object ofClass) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        String jsonString=objectMapper.writeValueAsString(ofClass);
        System.out.println("Request at Mapper :"+jsonString);
        return jsonString;
    }
    public static List<String> LostofValue(String values){
        if(values.contains(",")){
            String [] temp=values.split(",");
            return Arrays.asList(temp);
        }
        else {
            List<String> temp=new ArrayList<String>();
            temp.add(values);
            return temp;
        }
    }

    public static JsonPath verifyResponse(Response response){
        JsonPath evalutor=new JsonPath(response.asString());
        return evalutor;
    }
    public static XmlPath verifyResponseasXml(Response response){
        XmlPath evaluator=new XmlPath(response.asString());
        return evaluator;
    }
    public static LinkedHashMap getYamlData(String YML_Path,String valuepath) throws JsonProcessingException {
        String jsonValue;
        LinkedHashMap<String,String> headervalues=new LinkedHashMap<String, String>();
        Object headervalues1=JsonUtilities.setHeaderFromYaml(YML_Path, valuepath);
        jsonValue=getjsonStringfromPojo(headervalues1);
        String value=jsonValue;
        value=value.substring(1,value.length()-1);
        value=value.replace("{","");
        value=value.replace("}","");
        value=value.replace("\"","");
        String[] kyeValuePairs=value.split(",");
        for(String pair:kyeValuePairs){
            String[] entry=pair.split(":");
            try{
                        headervalues.put(entry[0].trim(), entry[1].trim());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return headervalues;
    }
    public static Object setHeaderFromYaml(String YML_Path, String valuepath) throws JsonProcessingException {
        Yaml yaml=new Yaml();
        Map<String,Object> res=new HashMap<String, Object>();
        File file=new File(YML_Path);
        Map<String,Object> map=(Map<String, Object>) Yaml.load(YML_Path);
        String jsonStr=getjsonStringfromPojo(map);
        JSONObject json=new JSONObject(jsonStr);
        String jsonone=json.toString();
        Object jsonTwo= com.jayway.jsonpath.JsonPath.read(jsonone,valuepath);
        return jsonTwo;
    }

}
