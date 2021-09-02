package sa.elm.iam.client.model;

import lombok.Data;

@Data
public class AuthorizationRequest {

    /*
     * production: https://www.iam.gov.sa/authservice/authorize?
     * or :
     * staging: https://www.iam.sa/authservice/authorize?
     * scope=openid
     * &response_type=id_token
     * &response_mode=form_post
     * &client_id=<CLIENT ID>
     * &redirect_uri=https://www.service_provider.com/callback
     * &nonce=GUID_RANDOM (example: b55224f7-e83d-4250-aa4a-451d32666e59)
     * &ui_locales=ar/en
     * &prompt=login (optional, when set the user will be forced to re-authenticate)
     * &max_age=timestamp (issue time of the request in seconds - using Saudi time is a must) in epoch format
     * &state=<Message_Signature>
     */

    private String scope;
    private String response_type;
    private String response_mode;
    private String client_id;
    private String redirect_uri;
    private String nonce;
    private String ui_locales;
    private String prompt;
    private long max_age;
    private String state;
}
