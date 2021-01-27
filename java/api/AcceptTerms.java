package api;

import global.api.Auth;
import global.api.HeadersModel;
import global.api.RestConfiguration;
import global.entity.Member;
import global.services.AcceptTermsService;
import global.services.GDPRService;

public class AcceptTerms {

    public void acceptTerms(Member member){
        Auth auth = new Auth();
        RestConfiguration restConfiguration = new RestConfiguration();

        HeadersModel headersModel = auth.createAuthHeaders(restConfiguration.getOrionBaseUrl(), member, true);

        AcceptTermsService acceptTermsService = new AcceptTermsService();
        acceptTermsService.acceptTerms(headersModel);
    }

    public void acceptGDPR(Member member){
        Auth auth = new Auth();
        RestConfiguration restConfiguration = new RestConfiguration();

        HeadersModel headersModel = auth.createAuthHeaders(restConfiguration.getOrionBaseUrl(), member, true);

        GDPRService gdprService = new GDPRService();
        gdprService.acceptGDPR(headersModel);
    }
}
