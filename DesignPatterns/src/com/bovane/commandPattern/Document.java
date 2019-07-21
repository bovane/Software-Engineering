package com.bovane.commandPattern;

/*具体执行文档的命令类*/
public class Document implements IShow{
    private Application application;

    public Document(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        application.showText();
    }

    @Override
    public void unExecute() {
        application.closeText();
    }
}
