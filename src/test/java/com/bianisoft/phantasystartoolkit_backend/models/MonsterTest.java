package com.bianisoft.phantasystartoolkit_backend.models;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class MonsterTest {

    @Test
    public void isCreatedDefaultCorrectly() {
        Monster newEmptyMonster = new Monster();

        assertNotNull(newEmptyMonster);
        assertEquals(" ", newEmptyMonster.getName());
        assertEquals(0x00, newEmptyMonster.getMaxNbInPartyNumber());
        assertEquals(0x00, newEmptyMonster.getHP());
        assertEquals(0x00, newEmptyMonster.getATK());
        assertEquals(0x00, newEmptyMonster.getDEF());
        assertEquals(0x00, newEmptyMonster.getIdItemDrop());
        assertEquals(0x00, newEmptyMonster.getNbMesetas());
        assertEquals(0x00, newEmptyMonster.getChanceTrap());
        assertEquals(0x00, newEmptyMonster.getNbEXP());
        assertEquals(0x00, newEmptyMonster.getChanceRun());
    }

    @Test
    public void isAllStandardSetterWorks() {
        Monster newEmptyMonster = new Monster();

        newEmptyMonster.setName("A");
        assertEquals("A", newEmptyMonster.getName());

        newEmptyMonster.setName("ABCDEFGH");
        assertEquals("ABCDEFGH", newEmptyMonster.getName());

        newEmptyMonster.setMaxNbInPartyNumber(8);
        assertEquals(8, newEmptyMonster.getMaxNbInPartyNumber());

        newEmptyMonster.setHP(123);
        assertEquals(123, newEmptyMonster.getHP());

        newEmptyMonster.setATK(123);
        assertEquals(123, newEmptyMonster.getATK());

        newEmptyMonster.setDEF(123);
        assertEquals(123, newEmptyMonster.getDEF());

        newEmptyMonster.setDEF(123);
        assertEquals(123, newEmptyMonster.getDEF());

        newEmptyMonster.setIdItemDrop(123);
        assertEquals(123, newEmptyMonster.getIdItemDrop());

        newEmptyMonster.setNbMesetas(1234);
        assertEquals(1234, newEmptyMonster.getNbMesetas());

        newEmptyMonster.setNbEXP(1234);
        assertEquals(1234, newEmptyMonster.getNbEXP());

        newEmptyMonster.setChanceTrap(56);
        assertEquals(56, newEmptyMonster.getChanceTrap());

        newEmptyMonster.setChanceRun(56);
        assertEquals(56, newEmptyMonster.getChanceRun());
    }

    @Test
    public void isEdgeCasesSetterWorks() {
        Monster newEmptyMonster = new Monster();

        newEmptyMonster.setName("");
        assertEquals(" ", newEmptyMonster.getName());       //Minimum of 1 characters (space by default)

        newEmptyMonster.setName("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        assertEquals("ABCDEFGH", newEmptyMonster.getName());//Only 8 characters

        newEmptyMonster.setHP(-25);
        assertEquals(0, newEmptyMonster.getHP());           //Should not allowed negative value in these fields

        newEmptyMonster.setHP(326);
        assertEquals((byte)0xFF, newEmptyMonster.getHP());            //Should not allowed over a byte values

        newEmptyMonster.setATK(-25);
        assertEquals(0, newEmptyMonster.getATK());          //Should not allowed negative value in these fields

        newEmptyMonster.setATK(326);
        assertEquals((byte)0xFF, newEmptyMonster.getATK());           //Should not allowed over a byte values

        newEmptyMonster.setDEF(-25);
        assertEquals(0, newEmptyMonster.getDEF());          //Should not allowed negative value in these fields

        newEmptyMonster.setDEF(326);
        assertEquals((byte)0xFF, newEmptyMonster.getDEF());           //Should not allowed over a byte values

        newEmptyMonster.setIdItemDrop(-25);
        assertEquals(0, newEmptyMonster.getIdItemDrop());   //Should not allowed negative value in these fields

        newEmptyMonster.setIdItemDrop(326);
        assertEquals((byte)0xFF, newEmptyMonster.getIdItemDrop());    //Should not allowed over a byte values

        newEmptyMonster.setNbMesetas(-299);
        assertEquals(0, newEmptyMonster.getNbMesetas());    //Should not allowed negative value in these fields

        newEmptyMonster.setNbMesetas(125000);
        assertEquals(0xFFFF, newEmptyMonster.getNbMesetas());    //Should not allowed over a short values

        newEmptyMonster.setNbEXP(-299);
        assertEquals(0, newEmptyMonster.getNbEXP());        //Should not allowed negative value in these fields

        newEmptyMonster.setNbEXP(125000);
        assertEquals(0xFFFF, newEmptyMonster.getNbEXP());        //Should not allowed over a short values

        newEmptyMonster.setChanceTrap(-56);
        assertEquals(0, newEmptyMonster.getChanceTrap());   //Should not allowed negative value in these fields

        newEmptyMonster.setChanceTrap(326);
        assertEquals((byte)0xFF, newEmptyMonster.getChanceTrap());    //Should not allowed over a byte values

        newEmptyMonster.setChanceRun(-56);
        assertEquals(0, newEmptyMonster.getChanceRun());    //Should not allowed negative value in these fields

        newEmptyMonster.setChanceRun(356);
        assertEquals((byte)0xFF, newEmptyMonster.getChanceRun());     //Should not allowed over a byte values
    }


    @Test
    public void isUnSerializationFromBytArrayWorks() {

    }


    @Test
    public void isSerializationToByteArrayWorks() {

    }
}
