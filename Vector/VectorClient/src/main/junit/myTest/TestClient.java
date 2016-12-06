package myTest;

import java.rmi.Naming;

import rmi.RemoteHelper;

public class TestClient {
	 private RemoteHelper remoteHelper;
	 
	 public TestClient(){
		 linkToServer();
	 }
	 
	 private void linkToServer(){
	      try{
	          remoteHelper=RemoteHelper.getInstance();
	          remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
	    
	      }catch (Exception e){
	          e.printStackTrace();
	      }
	 }
	 
	 
}
