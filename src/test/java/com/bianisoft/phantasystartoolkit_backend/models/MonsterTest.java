package com.bianisoft.phantasystartoolkit_backend.models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class MonsterTest {
    private Monster newMonster;

    @BeforeEach
    public void initEach() {
        newMonster = new Monster();
    }

    @Test
    public void isCreatedDefaultCorrectly() {
        assertNotNull(newMonster);
        assertEquals(" ", newMonster.getName());
        assertEquals(0x00, newMonster.getMaxNbInPartyNumber());
        assertEquals(0x00, newMonster.getHP());
        assertEquals(0x00, newMonster.getATK());
        assertEquals(0x00, newMonster.getDEF());
        assertEquals(0x00, newMonster.getIdItemDrop());
        assertEquals(0x00, newMonster.getNbMesetas());
        assertEquals(0x00, newMonster.getChanceTrap());
        assertEquals(0x00, newMonster.getNbEXP());
        assertEquals(0x00, newMonster.getChanceRun());
    }

    @Test
    public void isAllStandardSetterWorks() {
        newMonster.setName("A");
        assertEquals("A", newMonster.getName());

        newMonster.setName("ABCDEFGH");
        assertEquals("ABCDEFGH", newMonster.getName());

        newMonster.setMaxNbInPartyNumber(8);
        assertEquals(8, newMonster.getMaxNbInPartyNumber());

        newMonster.setHP(123);
        assertEquals(123, newMonster.getHP());

        newMonster.setATK(123);
        assertEquals(123, newMonster.getATK());

        newMonster.setDEF(123);
        assertEquals(123, newMonster.getDEF());

        newMonster.setDEF(123);
        assertEquals(123, newMonster.getDEF());

        newMonster.setIdItemDrop(123);
        assertEquals(123, newMonster.getIdItemDrop());

        newMonster.setNbMesetas(1234);
        assertEquals(1234, newMonster.getNbMesetas());

        newMonster.setNbEXP(1234);
        assertEquals(1234, newMonster.getNbEXP());

        newMonster.setChanceTrap(56);
        assertEquals(56, newMonster.getChanceTrap());

        newMonster.setChanceRun(56);
        assertEquals(56, newMonster.getChanceRun());
    }

    @Test
    public void isEdgeCasesSetterWorks() {
        //Minimum of 1 characters (space by default)
        newMonster.setName("");
        assertEquals(" ", newMonster.getName());

        //Only 8 characters
        newMonster.setName("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        assertEquals("ABCDEFGH", newMonster.getName());

        //Should not allowed negative value in these fields
        newMonster.setHP(-25);
        assertEquals(0, newMonster.getHP());

        //Should not allowed over a byte values
        newMonster.setHP(326);
        assertEquals((byte)0xFF, newMonster.getHP());

        //Should not allowed negative value in these fields
        newMonster.setATK(-25);
        assertEquals(0, newMonster.getATK());

        //Should not allowed over a byte values
        newMonster.setATK(326);
        assertEquals((byte)0xFF, newMonster.getATK());

        //Should not allowed negative value in these fields
        newMonster.setDEF(-25);
        assertEquals(0, newMonster.getDEF());

        //Should not allowed over a byte values
        newMonster.setDEF(326);
        assertEquals((byte)0xFF, newMonster.getDEF());

        //Should not allowed negative value in these fields
        newMonster.setIdItemDrop(-25);
        assertEquals(0, newMonster.getIdItemDrop());

        //Should not allowed over a byte values
        newMonster.setIdItemDrop(326);
        assertEquals((byte)0xFF, newMonster.getIdItemDrop());

        //Should not allowed negative value in these fields
        newMonster.setNbMesetas(-299);
        assertEquals(0, newMonster.getNbMesetas());

        //Should not allowed over a short values
        newMonster.setNbMesetas(125000);
        assertEquals(0xFFFF, newMonster.getNbMesetas());

        //Should not allowed negative value in these fields
        newMonster.setNbEXP(-299);
        assertEquals(0, newMonster.getNbEXP());

        //Should not allowed over a short values
        newMonster.setNbEXP(125000);
        assertEquals(0xFFFF, newMonster.getNbEXP());

        //Should not allowed negative value in these fields
        newMonster.setChanceTrap(-56);
        assertEquals(0, newMonster.getChanceTrap());

        //Should not allowed over a byte values
        newMonster.setChanceTrap(326);
        assertEquals((byte)0xFF, newMonster.getChanceTrap());

        //Should not allowed negative value in these fields
        newMonster.setChanceRun(-56);
        assertEquals(0, newMonster.getChanceRun());

        //Should not allowed over a byte values
        newMonster.setChanceRun(356);
        assertEquals((byte)0xFF, newMonster.getChanceRun());
    }


    @Test
    public void isSerializationToByteArrayWorks() {
        byte [] arExampleSerialization = {
                0x53, 0x57, 0x4F, 0x52, 0x4D, 0x65, 0x20, 0x20,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x08, 0x08, 0x0D, 0x09,
                0x00, 0x03, 0x00, 0x0C, 0x02, 0x00, 0x00, (byte)0xFF
        };
        byte [] arMonsterSerialized = new byte [32];

        //Minimum of 1 characters (space by default)
        newMonster.setName("SWORM");
        newMonster.setMaxNbInPartyNumber(8);
        newMonster.setHP(8);
        newMonster.setATK(0x0D);
        newMonster.setDEF(9);
        newMonster.setIdItemDrop(0);
        newMonster.setNbMesetas(3);
        newMonster.setNbEXP(2);
        newMonster.setChanceTrap(0x0C);
        newMonster.setChanceRun(0xFF);

        newMonster.serialize(arMonsterSerialized);

        assertArrayEquals(arExampleSerialization, arMonsterSerialized);
    }

    @Test
    public void isUnSerializationFromBytArrayWorks() {

        //Taken directly from the SMS rom
        byte [] arMonsterSworm = {
                0x53, 0x57, 0x4F, 0x52, 0x4D, 0x65, 0x20, 0x20,
                0x2A, 0x25, 0x05, 0x0A, 0x08, 0x04, 0x0C, 0x2F,
                0x0B, (byte)0xDA, (byte)0x9C, 0x12, 0x08, 0x08, 0x0D, 0x09,
                0x00, 0x03, 0x00, 0x0C, 0x02, 0x00, 0x38, (byte)0xFF
        };

        newMonster.unserialize(arMonsterSworm);

        assertEquals("SWORM", newMonster.getName());
        assertEquals(8, newMonster.getHP());
        assertEquals(8, newMonster.getMaxNbInPartyNumber());
        assertEquals(0x0D, newMonster.getATK());
        assertEquals(0x09, newMonster.getDEF());
        assertEquals(0, newMonster.getIdItemDrop());
        assertEquals(0x0003, newMonster.getNbMesetas());
        assertEquals(0x0002, newMonster.getNbEXP());
        assertEquals(0x0C, newMonster.getChanceTrap());
        assertEquals((byte)0xFF, newMonster.getChanceRun());

    }
}
