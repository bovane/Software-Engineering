package com.bovane.commandPattern;

/*具体执行图像类的接口*/
public class Graphics implements IShow{
    private Application application;

    public Graphics(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        application.showGraphics();
    }

    @Override
    public void unExecute() {
        application.closeGraphics();
    }
}
