package com.bianisoft.phantasystartoolkit_backend.models;


import java.util.ArrayList;


public class ModelFacade {
    private PSRomData mobjFile;
    private ArrayList<Monster> marMonster;
    private ArrayList<IObserverMonsterLoaded> marObserverMonsterList;
    private ArrayList<IObserverPSRomFileOpened> marObserverPSRomFile;


    ///Singleton Design Pattern
    private static class SingletonClassHolder {
        static final ModelFacade mSingletonInstance = new ModelFacade();
    }

    public static ModelFacade getInstance() {
        return SingletonClassHolder.mSingletonInstance;
    }

    private ModelFacade() {
        mobjFile= new PSRomData();
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

    public void setPSRomFileData(byte[] pdata){
        mobjFile.setData(pdata);

        for(IObserverMonsterLoaded objObserverMonsterList : marObserverMonsterList)
            marObserverPSRomFile.notify();
    }

    public PSRomData getSMSFile(){
        return mobjFile;
    }
}
