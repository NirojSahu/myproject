
package Responses.OBReadStatement1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class OBReadStatement {

    @JsonProperty("Data")
    private Data mData;

    public Data getData() {
        return mData;
    }

    public static class Builder {

        private Data mData;

        public OBReadStatement.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadStatement build() {
            OBReadStatement oBReadStatement = new OBReadStatement();
            oBReadStatement.mData = mData;
            return oBReadStatement;
        }

    }

}
