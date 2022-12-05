
package Responses.OBReadStandingOrder4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("StandingOrder")
    private List<StandingOrder> mStandingOrder;

    public List<StandingOrder> getStandingOrder() {
        return mStandingOrder;
    }

    public static class Builder {

        private List<StandingOrder> mStandingOrder;

        public Data.Builder withStandingOrder(List<StandingOrder> standingOrder) {
            mStandingOrder = standingOrder;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mStandingOrder = mStandingOrder;
            return data;
        }

    }

}
