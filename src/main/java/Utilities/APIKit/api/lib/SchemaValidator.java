//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.APIKit.api.lib;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaValidator {
    private static final Logger log = LoggerFactory.getLogger(SchemaValidator.class);

    public SchemaValidator() {
    }

    public ProcessingReport getValidatorReport(JsonNode jsonSchema, JsonNode jsonData) throws ProcessingException {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(jsonSchema);
        ProcessingReport report = schema.validate(jsonData);
        System.out.println(report);
        log.info("Schema validation repor", report);
        return report;
    }
}
