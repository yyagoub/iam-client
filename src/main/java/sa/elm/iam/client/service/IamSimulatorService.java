package sa.elm.iam.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sa.elm.iam.client.config.exception.UnprocessableEntityException;
import sa.elm.iam.client.model.AuthorizationRequest;
import sa.elm.iam.client.model.IamRequestUrl;

@Service
@AllArgsConstructor
public class IamSimulatorService {

    private final IamRequestUrl url;

    public String checkRequest(AuthorizationRequest authorizationRequest) {
        this.checkConstantQueryParam(authorizationRequest);
        return "this is request have been validated and it seems fine";
    }

    private void checkConstantQueryParam(AuthorizationRequest request){
        this.compareRequestValue(request.getScope(), url.getScope(), "scope");
        this.compareRequestValue(request.getResponse_type(), url.getResponseType(), "response_type");
        this.compareRequestValue(request.getResponse_mode(), url.getResponseMode(), "response_mode");
        this.compareRequestValue(request.getClient_id(), url.getClientId(), "client_id");
        this.compareRequestValue(request.getRedirect_uri(), url.getRedirectUri(), "redirect_uri");
        this.compareRequestValue(request.getUi_locales(), url.getUiLocales(), "ui_locales");
        this.compareRequestValue(request.getPrompt(), url.getPrompt(), "prompt");
    }

    private void compareRequestValue(String request, String url, String param){
        if (StringUtils.isEmpty(request))
            throw new UnprocessableEntityException(param+" can not be empty value");
        if (!request.equals(url))
            throw new UnprocessableEntityException("invalid " + param + " value");
    }
}
