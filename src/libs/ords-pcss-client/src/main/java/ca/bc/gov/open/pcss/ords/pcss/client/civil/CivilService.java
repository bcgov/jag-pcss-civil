package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentResponse;

public interface CivilService {

    AppearanceDocumentResponse getCivilSearchFileAppearanceDocument(String appearanceId);

}
