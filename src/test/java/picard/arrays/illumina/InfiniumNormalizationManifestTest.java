package picard.arrays.illumina;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class InfiniumNormalizationManifestTest {
    private static final File TEST_DATA_DIR = new File("testdata/picard/arrays/illumina");
    private static final File TEST_NORMALIZATION_MANIFEST_FILE = new File(TEST_DATA_DIR, "Test.bpm.csv");
    private static final File TEST_BAD_NORMALIZATION_MANIFEST_FILE = new File(TEST_DATA_DIR, "Test.extended.csv");

    @Test
    public void testInfiniumNormalizationManifest() {
        final InfiniumNormalizationManifest normalizationManifest = new InfiniumNormalizationManifest(TEST_NORMALIZATION_MANIFEST_FILE);
        final Integer[] normIds = normalizationManifest.getAllNormIds();
        Assert.assertEquals(normIds.length, 5);
        final Set<Integer> allNormIdSet = new TreeSet<>(Arrays.asList(normIds));
        Assert.assertTrue(allNormIdSet.containsAll(new HashSet<>(Arrays.asList(0, 1, 2, 102, 202))));
        Assert.assertEquals(normalizationManifest.getNormIds().length, 39);
        Assert.assertEquals(normalizationManifest.getChromosomes().length, 39);
        Assert.assertEquals(normalizationManifest.getPositions().length, 39);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testInvalidInfiniumNormalizationManifest() {
        new InfiniumNormalizationManifest(TEST_BAD_NORMALIZATION_MANIFEST_FILE);
    }

}

