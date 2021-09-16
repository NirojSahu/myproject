
package Responses.OBReadStatement2;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Statement {

    @JsonProperty("AccountId")
    private String accountId;
    @JsonProperty("CreationDateTime")
    private String creationDateTime;
    @JsonProperty("EndDateTime")
    private String endDateTime;
    @JsonProperty("StartDateTime")
    private String startDateTime;
    @JsonProperty("StatementAmount")
    private List<StatementAmount> statementAmount;
    @JsonProperty("StatementId")
    private String statementId;
    @JsonProperty("Type")
    private String type;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public List<StatementAmount> getStatementAmount() {
        return statementAmount;
    }

    public void setStatementAmount(List<StatementAmount> statementAmount) {
        this.statementAmount = statementAmount;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
