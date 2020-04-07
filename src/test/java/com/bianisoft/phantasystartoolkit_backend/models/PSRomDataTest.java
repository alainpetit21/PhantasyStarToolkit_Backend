package com.bianisoft.phantasystartoolkit_backend.models;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("The Phantasy Star Rom Data object should ...")
class PSRomDataTest {
    private PSRomData newPSRomData;

    @BeforeEach
    public void initEach() {
        newPSRomData = new PSRomData();
    }

    @Nested
    @DisplayName("... be able to be created ... ")
    class PSRomFileObjectCreationTests {
        @Test
        @DisplayName(" ... as an empty Object with Invalid state.")
        void shouldBeCreatedEmptyAndInvalid() {
            assertNotNull(newPSRomData);
            assertThrows(PSRomData.Exception_InvalidateState.class, () -> newPSRomData.assertFileIsValid());
        }

        @Test
        @DisplayName("... with valid Data Injected")
        void shouldBeCreatedWithDataInjected() {
            byte [] injectedData = new byte[32];

            assertNotNull(newPSRomData);
            newPSRomData.setData(injectedData);
            newPSRomData.setOffsetsMonsters(0, 32);
            assertDoesNotThrow(() -> newPSRomData.assertFileIsValid());

            assertEquals(1, newPSRomData.getNbMonster());
            assertSame(injectedData, newPSRomData.getData());
        }

        @Test
        @DisplayName("... from a copy constructor.")
        void shouldBeCreatedWithCopyConstructor() {
            assertNotNull(newPSRomData);

        }
    }


}