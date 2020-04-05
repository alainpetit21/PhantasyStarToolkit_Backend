package com.bianisoft.phantasystartoolkit_backend.models;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;


public class PSRomFile {
    public class Exception_InvalidateState extends Exception{

    }

    private String strPathFileName;
    private byte[] data;
    private int nOffsetMonstersStart;
    private int nOffsetMonstersEnd;
    private int nNbMonsters;
    private int nOffsetMaps;

    public PSRomFile(){
        invalidate();
    }

    private void invalidate(){
        strPathFileName = "";
        nOffsetMonstersStart = -1;
        nOffsetMonstersEnd = -1;
        nNbMonsters = -1;
        nOffsetMaps = -1;
        data = null;
    }

    public void Open(String p_strPathName){
        invalidate();
        strPathFileName = p_strPathName;

        File file = null;
        FileInputStream fis = null;
        try {
            file = new File(p_strPathName);
            fis = new FileInputStream(file);
            data= new byte[(int) file.length()];

            fis.read(data);
        }catch(FileNotFoundException fnfe) {
            System.out.println("File not found" + fnfe);
        }catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }finally {
            try {
                if (fis != null)
                    fis.close();

            }catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }
    }

    private void Close(){
        invalidate();
    }

    public String getPathFileName() throws Exception_InvalidateState{
        if("".equals(strPathFileName))
            throw new Exception_InvalidateState();

        return strPathFileName;
    }

    public byte[] getMonsterData(int pnIdx) throws Exception_InvalidateState, IndexOutOfBoundsException{
        if(nNbMonsters == -1)
            throw new Exception_InvalidateState();
        if((pnIdx < 0) || (pnIdx >= getNbMonster()))
            throw new IndexOutOfBoundsException();

        return Arrays.copyOfRange(data, (nOffsetMonstersStart)+(pnIdx*32),
                (nOffsetMonstersStart)+((pnIdx+1)*32));
    }

    public byte[] getData() throws Exception_InvalidateState{
        if("".equals(strPathFileName))
            throw new Exception_InvalidateState();

        return data;
    }

    public void setOffsetsMonsters(int p_nOffsetMonstersStart, int p_nOffsetMonstersEnd){
        nOffsetMonstersStart = p_nOffsetMonstersStart;
        nOffsetMonstersEnd = p_nOffsetMonstersEnd;
        nNbMonsters = (nOffsetMonstersEnd - nOffsetMonstersStart) / 32;
    }

    public int getNbMonster(){
        if(nNbMonsters == -1)
            return 0;

        return nNbMonsters;
    }

    public void setOffsetMaps(int p_nOffsetMaps){
        nOffsetMaps = p_nOffsetMaps;
    }

    public int getOffsetMaps() throws Exception_InvalidateState{
        if(nOffsetMaps == -1)
            throw new Exception_InvalidateState();

        return nOffsetMaps;
    }
}
