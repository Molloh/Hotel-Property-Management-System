package presentation.controller;

/**
 * @author Molloh
 * @version 2016/11/25
 * @description 创建一个单例类，实现activeId的set和get
 */
public class SingletonId {
    private String activateId;

    private static SingletonId instance = new SingletonId();

    private SingletonId() {
        activateId = null;
    }

    public static SingletonId getInstance() {
        return instance;
    }

    public String getActivateId() {
        return activateId;
    }

    public void setActivateId(String activateId) {
        this.activateId = activateId;
    }

}
