
package ma.notification;

import java.security.Security;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class MessageService {
    
    
    public void init(){
        Security.addProvider(new BouncyCastleProvider());
    }
    
    
}
