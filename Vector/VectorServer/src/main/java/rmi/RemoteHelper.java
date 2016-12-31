package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Administrator on 2016-11-20.
 */
public class RemoteHelper {
    public RemoteHelper(){
    }

    public String initServer(){
        DataRemoteObject dataRemoteObject;
        try{
            dataRemoteObject = new DataRemoteObject();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/DataRemoteObject",dataRemoteObject);
            return "Server linked";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }

    public String stopServer() {
        try{
            Naming.unbind("rmi://localhost:8888/DataRemoteObject");
            return "Server stopped";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }
}
