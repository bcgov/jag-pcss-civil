package ca.bc.gov.open.pcss.ords.pcss.client.civil.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AppearanceDocumentResponse  implements ResponseBase {

    private String responseMsg;

    private BigDecimal responseCd;

    private List<AppearanceDocumentData> data = new ArrayList<>();

    @Override
    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @Override
    public BigDecimal getResponseCd() {
        return responseCd;
    }

    public void setResponseCd(BigDecimal responseCd) {
        this.responseCd = responseCd;
    }

    public List<AppearanceDocumentData> getData() {
        return data;
    }

}
