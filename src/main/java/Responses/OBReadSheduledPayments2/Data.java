
package Responses.OBReadSheduledPayments2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("ScheduledPayment")
    private List<ScheduledPayment> mScheduledPayment;

    public List<ScheduledPayment> getScheduledPayment() {
        return mScheduledPayment;
    }

    public static class Builder {

        private List<ScheduledPayment> mScheduledPayment;

        public Data.Builder withScheduledPayment(List<ScheduledPayment> scheduledPayment) {
            mScheduledPayment = scheduledPayment;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mScheduledPayment = mScheduledPayment;
            return data;
        }

    }

}
