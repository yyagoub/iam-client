package sa.elm.iam.client.config.security.certificate;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public abstract class Certificate {

    public abstract Key getKey() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException;

}
