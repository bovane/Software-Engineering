package com.bovane.commandPattern;
/*命令调用者类*/
import java.util.ArrayList;

public class User {
    private ArrayList<IShow> iShowList = new ArrayList<IShow>();

    public void show(IShow iShow) {
        iShow.execute();
        iShowList.add(iShow);
    }

    public void redo(int levels) {
        int i;

        for (i = 0; i < levels; i++) {
            if (levels < iShowList.size())
                iShowList.get(i).execute();
        }
    }

    public void undo(int levels) {
        int i;

        for (i = 0; i < levels; i++) {
            if (levels < iShowList.size())
                iShowList.get(i).unExecute();
        }
    }
}
