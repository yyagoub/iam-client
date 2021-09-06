package sa.elm.iam.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sa.elm.iam.client.config.exception.UnprocessableEntityException;
import sa.elm.iam.client.model.IamRequestUrl;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class IamRequestUrlService {

    private final IamRequestUrl iamRequestUrl;

    public IamRequestUrl createIamRequestUrl(String nonce, String maxAge) throws CloneNotSupportedException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        IamRequestUrl url = this.iamRequestUrl.clone();
        url.setNonce(nonce);
        url.setMaxAge(maxAge);
        return url;
    }

    public String toBeSignedUrl(IamRequestUrl url){
        return url.getHost()+"?"
                + "scope" + "=" + url.getScope()
                + "&" + "response_type" + "=" + url.getResponseType()
                + "&" + "response_mode" + "=" + url.getResponseMode()
                + "&" + "client_id" + "=" + url.getClientId()
                + "&" + "redirect_uri" + "=" + url.getRedirectUri()
                + "&" + "nonce" + "=" + url.getNonce()
                + "&" + "ui_locales" + "=" + url.getUiLocales()
                + "&" + "prompt" + "=" + url.getPrompt()
                + "&" + "max_age" + "=" + url.getMaxAge()
                ;
    }

    public String signedUrl(IamRequestUrl url){
        return this.toBeSignedUrl(url)
                + "&" + "state" + "=" + url.getState()
                ;
    }

    public void validateUrlsHost(URL url){
        String host = this.buildHost(url);
        if (!host.equals(iamRequestUrl.getHost()))
            throw new UnprocessableEntityException("invalid host: " + host + "\nplease replace it with: "+iamRequestUrl.getHost());
    }

    public String getStateFromUrl(URL url){
        try {
            String queryParamAndValue = url.getQuery().split("&")[9];
            return queryParamAndValue.split("=")[0];
        } catch (Exception e){
            throw new UnprocessableEntityException("state query are missing");
        }
    }

    private String buildHost(URL url){
        return url.getProtocol() +"://" + url.getHost() + "/authservice/authorize";
    }

    public void validateUrlsQuery(URL url) {
        String[] queryArray = url.getQuery().split("&");
        Map<String,String> queryMap = new HashMap<>();
        Arrays.stream(queryArray).forEach(query -> {
            String[] keyValue = query.split("=");
            this.validateUrlsQueryParam(keyValue);
            queryMap.put(keyValue[0], keyValue[1]);
        });
        this.validateUrlsQueryOrder(queryArray);
        this.validateUrlsQueryValues(queryMap);
    }

    private void validateUrlsQueryParam(String[] keyValue) {
        if (keyValue.length>2 && keyValue[0].equals("redirect_uri"))
            throw new UnprocessableEntityException("the query param redirect_uri value must be URL encoded");
        if (keyValue.length>2)
            throw new UnprocessableEntityException("the query param ["+keyValue[0]+"] contains invalid query value. Check for query value contains =");
        if (keyValue.length<2)
            throw new UnprocessableEntityException("the query param ["+keyValue[0]+"] are missing the value");
    }

    private void validateUrlsQueryOrder(String[] queryArray){
        String[] orderedQuery = this.getOrderedQueryParam();
        if (queryArray.length != orderedQuery.length)
            throw new UnprocessableEntityException("One of the query parameters are missing");
        for (int i = 0; i < orderedQuery.length; i++){
            String queryParam = queryArray[i].split("=")[0];
            if (!orderedQuery[i].equals(queryParam))
                throw new UnprocessableEntityException("Invalid query parameters order parameter["+i+"]: "+orderedQuery[i]+"!="+queryParam);
        }
    }

    private void validateUrlsQueryValues(Map<String,String> queryMap){
        // TODO
        String[] orderedQuery = this.getOrderedQueryParam();
    }

    private String[] getOrderedQueryParam() {
        // TODO: move it to a bean
        String[] query = new String[10];
        query[0] = "scope";
        query[1] = "response_type";
        query[2] = "response_mode";
        query[3] = "client_id";
        query[4] = "redirect_uri";
        query[5] = "nonce";
        query[6] = "ui_locales";
        query[7] = "prompt";
        query[8] = "max_age";
        query[9] = "state";
        return query;
    }

}
