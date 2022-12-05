
package Responses.OBReadStatement1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("Links")
    private Links mLinks;
    @JsonProperty("Meta")
    private Meta mMeta;
    @JsonProperty("Statement")
    private List<Statement> mStatement;

    public Links getLinks() {
        return mLinks;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public List<Statement> getStatement() {
        return mStatement;
    }

    public static class Builder {

        private Links mLinks;
        private Meta mMeta;
        private List<Statement> mStatement;

        public Data.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public Data.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public Data.Builder withStatement(List<Statement> statement) {
            mStatement = statement;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mLinks = mLinks;
            data.mMeta = mMeta;
            data.mStatement = mStatement;
            return data;
        }

    }

}
