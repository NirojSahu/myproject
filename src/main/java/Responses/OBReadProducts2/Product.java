
package Responses.OBReadProducts2;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Product {


    @JsonProperty("AccountId")
    private String mAccountId;

    @JsonProperty("ProductId")
    private String mProductId;

    @JsonProperty("ProductName")
    private String mProductName;

    @JsonProperty("ProductType")
    private String mProductType;

    public String getAccountId() {
        return mAccountId;
    }

    public String getProductId() {
        return mProductId;
    }

    public String getProductName() {
        return mProductName;
    }

    public String getProductType() {
        return mProductType;
    }

    public static class Builder {

        private String mAccountId;
        private String mProductId;
        private String mProductName;
        private String mProductType;

        public Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Builder withProductId(String productId) {
            mProductId = productId;
            return this;
        }

        public Builder withProductName(String productName) {
            mProductName = productName;
            return this;
        }

        public Builder withProductType(String productType) {
            mProductType = productType;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.mAccountId = mAccountId;
            product.mProductId = mProductId;
            product.mProductName = mProductName;
            product.mProductType = mProductType;
            return product;
        }

    }

}
