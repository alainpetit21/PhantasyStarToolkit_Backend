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
    private String strName;
    private byte[] unknown_8to15;
    private byte nBankOffset;
    private int nGraphicOffset;
    private byte unknown_19;
    private byte nPartyNumber;
    private byte nHP;
    private byte nATK;
    private byte nDEF;
    private byte ItemDrop;
    private int nMesetas;
    private byte nChanceTrap;
    private int nEXP;
    private byte unknown_30;
    private byte nRun;


    private Monster(){ }
    public Monster(byte[] pData){
        strName = new String(Arrays.copyOfRange(pData, 0, 8));
        unknown_8to15 = Arrays.copyOfRange(pData, 8, 16);
        nBankOffset = pData[16];
        nGraphicOffset = ((int)(pData[17]&0xFF)) | ((int)(pData[18]&0xFF) << 8);
        unknown_19= pData[19];
        nPartyNumber = pData[20];
        nHP = pData[21];
        nATK = pData[22];
        nDEF = pData[23];
        ItemDrop = pData[24];
        nMesetas = ((int)(pData[25]&0xFF)) | ((int)(pData[26]&0xFF) << 8);
        nChanceTrap = pData[27];
        nEXP = ((int)(pData[28]&0xFF)) | ((int)(pData[29]&0xFF) << 8);
        unknown_30 = pData[30];
        nRun = pData[31];
    }


    public void SaveToBuffer(byte[] pData){
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
        pData[24]= ItemDrop;
        pData[25]= (byte)(nMesetas&0xFF);
        pData[26]= (byte)((nMesetas&0xFF)>>8);
        pData[27]= nChanceTrap;
        pData[28]= (byte)(nEXP&0xFF);
        pData[29]= (byte)((nEXP&0xFF)>>8);
        pData[30]= unknown_30;
        pData[31]= nRun;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public byte getnPartyNumber() {
        return nPartyNumber;
    }

    public void setnPartyNumber(byte nPartyNumber) {
        this.nPartyNumber = nPartyNumber;
    }

    public byte getnHP() {
        return nHP;
    }

    public void setnHP(byte nHP) {
        this.nHP = nHP;
    }

    public byte getnATK() {
        return nATK;
    }

    public void setnATK(byte nATK) {
        this.nATK = nATK;
    }

    public byte getnDEF() {
        return nDEF;
    }

    public void setnDEF(byte nDEF) {
        this.nDEF = nDEF;
    }

    public byte getItemDrop() {
        return ItemDrop;
    }

    public void setItemDrop(byte itemDrop) {
        ItemDrop = itemDrop;
    }

    public int getnMesetas() {
        return nMesetas;
    }

    public void setnMesetas(int nMesetas) {
        this.nMesetas = nMesetas;
    }

    public byte getnChanceTrap() {
        return nChanceTrap;
    }

    public void setnChanceTrap(byte nChanceTrap) {
        this.nChanceTrap = nChanceTrap;
    }

    public int getnEXP() {
        return nEXP;
    }

    public void setnEXP(int nEXP) {
        this.nEXP = nEXP;
    }

    public byte getnRun() {
        return nRun;
    }

    public void setnRun(byte nRun) {
        this.nRun = nRun;
    }
}
