
package ma.notification;

import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class MessageService {
    
    private final String privateKey = "O9RxrXaYn1aJj5Roh_xq2aNw4TJGcACcHfmxvi7EXTg";
    private final String publicKey = "BOXpIQeIUm3_O9_EK4QdlnE3cc6Fmd0FgDcZTnToelS9-C2hp-Ra-pU9XZGgdsPnpDdNCh5HAk080L-CGNqfXqo";
    
    private PushService pushService;
    private List<Subscription> subscriptions = new ArrayList<>();
    
    public MessageService(){
        Security.addProvider(new BouncyCastleProvider());
        try {
            pushService = new PushService(publicKey, privateKey);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getPublicKey(){
        return publicKey;
    }
    
    
}
