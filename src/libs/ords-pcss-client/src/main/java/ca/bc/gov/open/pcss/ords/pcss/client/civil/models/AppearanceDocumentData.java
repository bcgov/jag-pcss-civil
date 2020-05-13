package ca.bc.gov.open.pcss.ords.pcss.client.civil.models;

import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceDocumentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceIssueData;

import java.util.ArrayList;
import java.util.List;

public class AppearanceDocumentData extends SearchFileAppearanceDocumentData {

    private List<SearchFileAppearanceIssueData> searchFileAppearanceIssueDataList = new ArrayList<>();

    public List<SearchFileAppearanceIssueData> getSearchFileAppearanceIssueDataList() {
        return searchFileAppearanceIssueDataList;
    }

    public void addAll(List<SearchFileAppearanceIssueData> searchFileAppearanceIssueDataList) {
        this.searchFileAppearanceIssueDataList.addAll(searchFileAppearanceIssueDataList);
    }

}
