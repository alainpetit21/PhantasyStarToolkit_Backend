package com.bianisoft.phantasystartoolkit_backend.models;


import java.util.ArrayList;


public class ModelFacade {
    private PSRomFile mobjFile = null;
    private ArrayList<Monster> marMonster = null;
    private ArrayList<IObserverMonsterLoaded> marObserverMonsterList= null;
    private ArrayList<IObserverPSRomFileOpened> marObserverPSRomFile= null;

    ///Singleton Design Pattern
    private static class SingletonClassHolder {
        static final ModelFacade mSingletonInstance = new ModelFacade();
    }

    public static ModelFacade getInstance() {
        return SingletonClassHolder.mSingletonInstance;
    }

    private ModelFacade() {
        mobjFile= new PSRomFile();
        marMonster= new ArrayList<>();
        marObserverMonsterList= new ArrayList<>();
        marObserverPSRomFile= new ArrayList<>();
    }


    ///Observer Design Pattern
    public void setObserverPSRomFile(IObserverPSRomFileOpened pObjObserver){
        marObserverPSRomFile.add(pObjObserver);
    }

    public void setObserverMonsterAdded(IObserverMonsterLoaded pObjObserver){
        marObserverMonsterList.add(pObjObserver);
    }

    public void addMonster(byte[] pdata){
        marMonster.add(new Monster(pdata));

        for(IObserverMonsterLoaded objObserverMonsterList : marObserverMonsterList)
            objObserverMonsterList.notify(marMonster);
    }

    //Setter / Getter
    public void setPSRomFile(String p_strObj){
        mobjFile.Open(p_strObj);

        for(IObserverPSRomFileOpened objObserverSMSFile : marObserverPSRomFile)
            objObserverSMSFile.notify(mobjFile);
    }

    public PSRomFile getSMSFile(){
        return mobjFile;
    }
}
