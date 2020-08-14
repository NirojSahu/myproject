
package Responses.OBReadProducts2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("Product")
    private List<Product> mProduct;

    public List<Product> getProduct() {
        return mProduct;
    }

    public static class Builder {

        private List<Product> mProduct;

        public Data.Builder withProduct(List<Product> product) {
            mProduct = product;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mProduct = mProduct;
            return data;
        }

    }

}
