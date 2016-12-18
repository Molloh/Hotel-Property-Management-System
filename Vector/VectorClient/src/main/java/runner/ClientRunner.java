package runner;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.common.ViewFxmlPath;
import rmi.RemoteHelper;

/**
 * @ author lienming
 * @ version 2016/11/20
 * @ description 带RMI版的利用这个类，对自己写的方法进行了简单测试，写在这个类的main方法里
 */
public class ClientRunner extends Application {
    private RemoteHelper remoteHelper;

    @Override
    public void start(Stage primaryStage) throws Exception {
        linkToServer();
        //TimedTask timedTask = new TimedTask();
        Parent root = FXMLLoader.load(getClass().getResource(ViewFxmlPath.SignIn_View_Path));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void linkToServer(){
        try{
            remoteHelper=RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
            System.out.println("get RemoteHelper SUCCEED,set Remote SUCCEED");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException{
        launch(args);
    }
  
}
