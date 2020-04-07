package com.bianisoft.phantasystartoolkit_backend.controllers;


import com.bianisoft.phantasystartoolkit_backend.models.ModelFacade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


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

    public byte[] getPSDataFromFile(String p_strPathName){
        File file;
        FileInputStream fis = null;
        byte [] data = null;

        try {
            file = new File(p_strPathName);
            fis = new FileInputStream(file);
            data= new byte[(int) file.length()];

            fis.read(data);
        }catch(FileNotFoundException fnfe) {
            System.out.println("File not found" + fnfe);
            fnfe.printStackTrace();
        }catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
            ioe.printStackTrace();
        }finally {
            try {
                if (fis != null)
                    fis.close();

            }catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }

        return data;
    }

    public void SetPSRomFile(String p_strPathName){
        ModelFacade objFacade = ModelFacade.getInstance();

        objFacade.setPSRomFileData(getPSDataFromFile(p_strPathName));
    }
}
