package mdp;

import java.util.Date;

import com.bill99.mdp.callback.MdpCallback;

public interface IMdpServer {
    public void speak(String word, MdpCallback callback);

    public Date now();
}
