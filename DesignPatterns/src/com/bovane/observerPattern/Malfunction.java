package com.bovane.observerPattern;

import java.util.ArrayList;

public class Malfunction extends IMalfunction{
    public String errorState;

    public String getErrorState() {
        return errorState;
    }

    public void setErrorState(String errorState) {
        this.errorState = errorState;
    }
    @Override
    public void notice(ArrayList observers) {
        
    }
}
