package com.bianisoft.phantasystartoolkit_backend.controllers;


import com.bianisoft.phantasystartoolkit_backend.models.IObserverPSRomFileOpened;
import com.bianisoft.phantasystartoolkit_backend.models.ModelFacade;
import com.bianisoft.phantasystartoolkit_backend.models.PSRomData;



public class MessageOnPSRomFileOpened implements IObserverPSRomFileOpened {

    @Override
    public void notify(PSRomData pObjSMSFile) {
        loadPSRomFile(pObjSMSFile);
    }

    private void loadPSRomFile(PSRomData pObjPSRomFile) {
        //Known 'magic' words in the PSRomFile from reverse engineering
        byte[] nMagicWordMonsterStart = {
                (byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x02,
                (byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x02,
                (byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x02,
                (byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x02,
                (byte) 0x02, (byte) 0x03, (byte) 0x00, (byte) 0x08,
                (byte) 0x08, (byte) 0x08, (byte) 0x08, (byte) 0x03,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };

        byte[] nMagicWordMonsterEnd =  {
                (byte) 0x0F, (byte) 0x02, (byte) 0x27, (byte) 0x60,
                (byte) 0x57, (byte) 0x80, (byte) 0x57, (byte) 0x80,
                (byte) 0x03, (byte) 0x87, (byte) 0x93, (byte) 0x87,
                (byte) 0x93, (byte) 0x70, (byte) 0xB9, (byte) 0x5C,
        };


        //Phase 1 : Ensure that this Object is in valid State
        try{
            pObjPSRomFile.assertFileIsValid();
        }catch (PSRomData.Exception_InvalidateState e) {
            return;
        }


        //Phase 2 : Calculate where are the beginning and stop of the Monster Sections
        byte[] dataFile =  pObjPSRomFile.getData();

        int nOffsetMonsterStart = findMagicPatternOffset(dataFile, nMagicWordMonsterStart) + PSRomData.SIZE_MONSTER_STRUCT;
        int nOffsetMonsterEnd = findMagicPatternOffset(dataFile, nMagicWordMonsterEnd);

        pObjPSRomFile.setOffsetsMonsters(nOffsetMonsterStart, nOffsetMonsterEnd);


        //Phase 3 : Load Each monster individually
        for(int i= 0; i < pObjPSRomFile.getNbMonster(); ++i){
            try {
                byte[] monsterData = pObjPSRomFile.getMonsterData(i);
                ModelFacade.getInstance().addMonster(monsterData);

            }catch(IndexOutOfBoundsException iob){
                System.out.println("IndexOutOfBoundsException");
            }
        }
    }

    private int findMagicPatternOffset(byte[] data, byte[] pnPattern){
        int nSizeOfPattern= pnPattern.length;
        int nOffset= 0;

        while(true){
            int nDif = 0;

            for(int i= 0; i < nSizeOfPattern; ++i){
                nDif += data[nOffset+i] - pnPattern[i];

                if(nDif != 0)
                    break;
            }

            if(nDif == 0)
                return nOffset;
            else
                nOffset++;
        }
    }
}
