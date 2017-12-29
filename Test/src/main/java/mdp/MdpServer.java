package mdp;

import java.util.Date;

import com.bill99.mdp.callback.MdpCallback;

public class MdpServer implements IMdpServer {
    @Override
    public Date now() {
        return null;
    }

    @Override
    public void speak(String word, MdpCallback callback) {
    }
}
