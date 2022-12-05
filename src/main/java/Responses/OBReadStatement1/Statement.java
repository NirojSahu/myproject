
package Responses.OBReadStatement1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Statement {

    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("CreationDateTime")
    private String mCreationDateTime;
    @JsonProperty("EndDateTime")
    private String mEndDateTime;
    @JsonProperty("StartDateTime")
    private String mStartDateTime;
    @JsonProperty("StatementAmount")
    private List<StatementAmount> mStatementAmount;
    @JsonProperty("StatementDescription")
    private List<String> mStatementDescription;
    @JsonProperty("StatementId")
    private String mStatementId;
    @JsonProperty("StatementReference")
    private String mStatementReference;
    @JsonProperty("Type")
    private String mType;

    public String getAccountId() {
        return mAccountId;
    }

    public String getCreationDateTime() {
        return mCreationDateTime;
    }

    public String getEndDateTime() {
        return mEndDateTime;
    }

    public String getStartDateTime() {
        return mStartDateTime;
    }

    public List<StatementAmount> getStatementAmount() {
        return mStatementAmount;
    }

    public List<String> getStatementDescription() {
        return mStatementDescription;
    }

    public String getStatementId() {
        return mStatementId;
    }

    public String getStatementReference() {
        return mStatementReference;
    }

    public String getType() {
        return mType;
    }

    public static class Builder {

        private String mAccountId;
        private String mCreationDateTime;
        private String mEndDateTime;
        private String mStartDateTime;
        private List<StatementAmount> mStatementAmount;
        private List<String> mStatementDescription;
        private String mStatementId;
        private String mStatementReference;
        private String mType;

        public Statement.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Statement.Builder withCreationDateTime(String creationDateTime) {
            mCreationDateTime = creationDateTime;
            return this;
        }

        public Statement.Builder withEndDateTime(String endDateTime) {
            mEndDateTime = endDateTime;
            return this;
        }

        public Statement.Builder withStartDateTime(String startDateTime) {
            mStartDateTime = startDateTime;
            return this;
        }

        public Statement.Builder withStatementAmount(List<StatementAmount> statementAmount) {
            mStatementAmount = statementAmount;
            return this;
        }

        public Statement.Builder withStatementDescription(List<String> statementDescription) {
            mStatementDescription = statementDescription;
            return this;
        }

        public Statement.Builder withStatementId(String statementId) {
            mStatementId = statementId;
            return this;
        }

        public Statement.Builder withStatementReference(String statementReference) {
            mStatementReference = statementReference;
            return this;
        }

        public Statement.Builder withType(String type) {
            mType = type;
            return this;
        }

        public Statement build() {
            Statement statement = new Statement();
            statement.mAccountId = mAccountId;
            statement.mCreationDateTime = mCreationDateTime;
            statement.mEndDateTime = mEndDateTime;
            statement.mStartDateTime = mStartDateTime;
            statement.mStatementAmount = mStatementAmount;
            statement.mStatementDescription = mStatementDescription;
            statement.mStatementId = mStatementId;
            statement.mStatementReference = mStatementReference;
            statement.mType = mType;
            return statement;
        }

    }

}
