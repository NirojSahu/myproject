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
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
//import org.ho.yaml.Yaml;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class JsonUtilities {

    public static JsonNode loadResource(String fileAbsPath) throws IOException {
        return JsonLoader.fromResource(fileAbsPath);
    }
    public static JsonNode loadResourcefromString(String json) throws IOException {
        return JsonLoader.fromString(json);
    }
    public static ProcessingReport validateSchema(Response res, String fileAbsPath) throws IOException, ProcessingException {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(loadResource(fileAbsPath));
        ProcessingReport report = schema.validate(getJsonNodefromString(res.getBody().asString()));
        System.out.println("=======SchemaPassed======");
        return report;
    }
    public static JsonNode getJsonNodefromString(String jsonString)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(jsonString);
        JsonNode actualObj = mapper.readTree(parser);
        return actualObj;

    }

    public static String getJsonStringfromPojo  (Object ofClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String jsonString = objectMapper.writeValueAsString(ofClass);
        System.out.println("Request at Mapper :" + jsonString);
        return  jsonString;
    }

    public static List<String> ListofValues (String Values)
    {
        if(Values.contains(","))
        {
            String [] temp = Values.split(",");
            return Arrays.asList(temp);

        }
        else
        {
            List<String> temp = new ArrayList<String>();
            temp.add(Values);
            return temp;
        }

    }

        public static JsonPath verifyResponse(Response response)
            {
                JsonPath  evaluator =new JsonPath(response.asString());
                return evaluator;
            }

    public static XmlPath verifyResponseasXML(Response response)
    {
        XmlPath  evaluator =new XmlPath(response.asString());
        return evaluator;
    }

    static public LinkedHashMap getYamlData(  String YML_Path, String valuepath) throws FileNotFoundException, JsonProcessingException, JSONException {
//  resource_API="account-access-consents_1";
//        authorization="Niroj";
         String jsonValue;
        LinkedHashMap <String,String> headervalues = new LinkedHashMap<>();

        Object headervalues1=JsonUtilities.setHeaderFromYaml( YML_Path, valuepath);
        jsonValue= getJsonStringfromPojo(headervalues1);
        String value = jsonValue;
        value = value.substring(1, value.length()-1);
        value= value.replace("{","");
        value= value.replace("}","");
        value= value.replace("\"","");
        String[] keyValuePairs = value.split(",");
        for(String pair : keyValuePairs)
        {
            String[] entry = pair.split(":");
//            if(entry[0].equalsIgnoreCase("Authorization")){
//                headervalues.put(entry[0].trim(), authorization);
//            }
//            else
            try {
                headervalues.put(entry[0].trim(), entry[1].trim());
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
//        Iterator it = headervalues.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            System.out.println(pair.getKey());
//            System.out.println(pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
//        }
        return headervalues;

    }
    public static Object setHeaderFromYaml(String YML_Path, String valuepath) throws JsonProcessingException, FileNotFoundException, JSONException {
        Yaml yaml = new Yaml();
        Map<String, Object> res = new HashMap<String, Object>();
        File file = new File(YML_Path);
        Map<String, Object> map = (Map<String, Object>) file.getAbsoluteFile();
//                (Map<String, Object>) Yaml.load(new File(YML_Path));
        String jsonStr = getJsonStringfromPojo(map);
        JSONObject json = new JSONObject(jsonStr);
        String jsonone=json.toString();
        Object jsonTwo= com.jayway.jsonpath.JsonPath.read(jsonone, valuepath);
        //System.out.println(jsonTwo);
        return  jsonTwo;
    }

}
