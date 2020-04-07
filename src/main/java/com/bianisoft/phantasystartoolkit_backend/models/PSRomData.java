package com.bianisoft.phantasystartoolkit_backend.models;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;


public class PSRomData {
    public static class Exception_InvalidateState extends Exception{
    }

    public static final int SIZE_MONSTER_STRUCT = 32;

    private byte[] data;

    private int nOffsetMonstersStart;
    private int nOffsetMonstersEnd;
    private int nNbMonsters;

    private int nOffsetMaps;

    public PSRomData(){
        invalidate();
    }

    public PSRomData(byte [] arData, int p_nOffsetMonstersStart, int p_nOffsetMonstersEnd){
        setData(arData);
        setOffsetsMonsters(p_nOffsetMonstersStart, p_nOffsetMonstersEnd);
    }

    public PSRomData(PSRomData objCopy){
        setData(new byte[objCopy.data.length]);
        setOffsetsMonsters(objCopy.nOffsetMonstersStart, objCopy.nOffsetMonstersEnd);
        this.nOffsetMaps = objCopy.nOffsetMaps;
    }

    private void invalidate(){
        nOffsetMonstersStart = -1;
        nOffsetMonstersEnd = -1;
        nNbMonsters = -1;
        nOffsetMaps = -1;
        data = null;
    }

    public void assertFileIsValid() throws Exception_InvalidateState {
        if (data == null)
            throw new Exception_InvalidateState();
    }

    public byte[] getMonsterData(int pnIdx) throws IndexOutOfBoundsException{
        if((pnIdx < 0) || (pnIdx >= getNbMonster()))
            throw new IndexOutOfBoundsException();

        return Arrays.copyOfRange(data, (nOffsetMonstersStart)+(pnIdx * SIZE_MONSTER_STRUCT),
                (nOffsetMonstersStart)+((pnIdx+1) * SIZE_MONSTER_STRUCT));
    }

    public byte[] getData(){
        return data;
    }
    public void setData(byte[] data){
        this.data= data;
    }

    public void setOffsetsMonsters(int p_nOffsetMonstersStart, int p_nOffsetMonstersEnd){
        nOffsetMonstersStart = p_nOffsetMonstersStart;
        nOffsetMonstersEnd = p_nOffsetMonstersEnd;
        nNbMonsters = (nOffsetMonstersEnd - nOffsetMonstersStart) / SIZE_MONSTER_STRUCT;
    }

    public int getNbMonster(){
        return nNbMonsters;
    }

    public int getOffsetMaps(){
        return nOffsetMaps;
    }
}
