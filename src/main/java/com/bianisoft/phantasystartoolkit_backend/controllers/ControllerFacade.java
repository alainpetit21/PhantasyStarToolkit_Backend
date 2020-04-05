package com.bianisoft.phantasystartoolkit_backend.controllers;


import com.bianisoft.phantasystartoolkit_backend.models.ModelFacade;


public class ControllerFacade {

    private static class SingletonClassHolder {
        static final ControllerFacade mSingletonInstance = new ControllerFacade();
    }

    public static ControllerFacade getInstance() {
        return SingletonClassHolder.mSingletonInstance;
    }

    private ControllerFacade() {
        ModelFacade objFacade = ModelFacade.getInstance();
        objFacade.setObserverPSRomFile(new MessageOnPSRomFileOpened());
    }

    public void SetPSRomFile(String p_strPathName){
        ModelFacade objFacade = ModelFacade.getInstance();

        objFacade.setPSRomFile(p_strPathName);
    }
}
