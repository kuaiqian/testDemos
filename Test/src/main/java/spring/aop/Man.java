package spring.aop;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Man  implements Human,Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7908874816950618049L;
    private String name;

    public Man() throws RemoteException {
        super();
    }

    public Man(String name) throws RemoteException{
        this.name = name;
    }

    @Override
    public void eat(int time) {
        System.out.println(name + ", 好吃吗?"+time);
    }
}
