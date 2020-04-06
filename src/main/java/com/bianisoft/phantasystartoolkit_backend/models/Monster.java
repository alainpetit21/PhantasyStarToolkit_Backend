package com.bianisoft.phantasystartoolkit_backend.models;

import java.util.Arrays;

// Enemy Data - Comming from source code
//
// Bytes 0-7 = Name; if shorter than 8 bytes, needs to be terminated with Dialogue_Terminator65
// Byte 16 = Bank where graphics are located
// Bytes 17-18 = Graphics pointer
// Byte 20 = Party number
// Byte 21 = HP
// Byte 22 = Attack
// Byte 23 = Defense
// Byte 24 = Item drop
// Bytes 25-26 = Meseta
// Byte 27 = Trap chance
// Bytes 28-29 = EXP
// Byte 31 = Run chance
//-----------------------------------------------------------------


public class Monster {
    private String strName = " ";
    private byte[] unknown_8to15= {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private byte nBankOffset = 0x00;
    private int nGraphicOffset = 0x0000;
    private byte unknown_19 = 0x00;
    private byte nPartyNumber = 0x00;
    private byte nHP = 0x00;
    private byte nATK = 0x00;
    private byte nDEF = 0x00;
    private byte nIdItemDrop = 0x00;
    private int nMesetas = 0x0000;
    private byte nChanceTrap = 0x00;
    private int nEXP = 0x0000;
    private byte unknown_30 = 0x00;
    private byte nRun =0x00;


    public Monster(){
    }

    public Monster(byte[] pData){
        unserialize(pData);
    }

    public void unserialize(byte[] pData){
        strName = new String(Arrays.copyOfRange(pData, 0, 8));
        unknown_8to15 = Arrays.copyOfRange(pData, 8, 16);
        nBankOffset = pData[16];
        nGraphicOffset = ((pData[17]&0xFF)) | ((pData[18]&0xFF) << 8);
        unknown_19= pData[19];
        nPartyNumber = pData[20];
        nHP = pData[21];
        nATK = pData[22];
        nDEF = pData[23];
        nIdItemDrop = pData[24];
        nMesetas = ((pData[25]&0xFF)) | ((pData[26]&0xFF) << 8);
        nChanceTrap = pData[27];
        nEXP = ((pData[28]&0xFF)) | ((pData[29]&0xFF) << 8);
        unknown_30 = pData[30];
        nRun = pData[31];

        //abstract out the SMS format of 'e' end of string delimitation and extra padding withespace.
        int idxDeliminator = strName.indexOf("e");
        if(idxDeliminator != -1){
            strName= strName.substring(0, idxDeliminator);
        }
    }

    public void serialize(byte[] pData){
        if(strName.length() < 8)
            strName= strName + 'e';
        else if (strName.length() > 8)
            strName= strName.substring(0, 8);


        for(int i= 0; i < 8; ++i){
            if(i < strName.length())
                pData[i]= (byte)strName.charAt(i);
            else
                pData[i]= ' ';
        }

        for(int i= 8, j=0; i < 16; ++i, ++j)
            pData[i]= unknown_8to15[j];


        pData[16]= nBankOffset;
        pData[17]= (byte)(nGraphicOffset&0xFF);
        pData[18]= (byte)((nGraphicOffset&0xFF)>>8);
        pData[19]= unknown_19;
        pData[20]= nPartyNumber;
        pData[21]= nHP;
        pData[22]= nATK;
        pData[23]= nDEF;
        pData[24]= nIdItemDrop;
        pData[25]= (byte)(nMesetas&0xFF);
        pData[26]= (byte)((nMesetas&0xFF)>>8);
        pData[27]= nChanceTrap;
        pData[28]= (byte)(nEXP&0xFF);
        pData[29]= (byte)((nEXP&0xFF)>>8);
        pData[30]= unknown_30;
        pData[31]= nRun;
    }

    private byte cropByte(int input){
        //crop to 0 if lower;
        if(input < 0)
            return (byte) 0x00;

            //crop to 0xFF if higher;
        else if(input > 0xFF)
            return (byte) 0xFF;

        return (byte)input;
    }

    private int cropShort(int input){
        //crop to 0 if lower;
        if(input < 0)
            return 0x0000;

            //crop to 0xFF if higher;
        else if(input > 0xFFFF)
            return  0xFFFF;

        return input;
    }

    public String getName() {
        return strName;
    }

    public void setName(String strName) {
        if (strName.length() > 8)
            strName= strName.substring(0, 8);
        else  if (strName.length() == 0)
            strName= " ";

        this.strName = strName;
    }

    public byte getMaxNbInPartyNumber() {
        return nPartyNumber;
    }

    public void setMaxNbInPartyNumber(int nPartyNumber) {
        this.nPartyNumber = (byte) nPartyNumber;
    }

    public byte getHP() {
        return nHP;
    }

    public void setHP(int nHP) {
        this.nHP = cropByte(nHP);
    }

    public byte getATK() {
        return nATK;
    }

    public void setATK(int nATK) {
        this.nATK = cropByte(nATK);
    }

    public byte getDEF() {
        return nDEF;
    }

    public void setDEF(int nDEF) {
        this.nDEF = cropByte(nDEF);
    }

    public byte getIdItemDrop() {
        return nIdItemDrop;
    }

    public void setIdItemDrop(int nIdItemDrop) {
        this.nIdItemDrop = cropByte(nIdItemDrop);
    }

    public int getNbMesetas() {
        return nMesetas;
    }

    public void setNbMesetas(int nMesetas) {
        this.nMesetas = cropShort(nMesetas);
    }

    public byte getChanceTrap() {
        return nChanceTrap;
    }

    public void setChanceTrap(int nChanceTrap) {
        this.nChanceTrap = cropByte(nChanceTrap);
    }

    public int getNbEXP() {
        return nEXP;
    }

    public void setNbEXP(int nEXP) {
        this.nEXP = cropShort(nEXP);
    }

    public byte getChanceRun() {
        return nRun;
    }

    public void setChanceRun(int nRun) {
        this.nRun = cropByte(nRun);
    }
}
