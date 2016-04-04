package htsjdk.samtools;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author nhomer
 */
public class SamFlagFieldTest {

    @Test
    public void testAllFlags() {
        final int flagAsInteger = SamFlagField.STRING.parse(SamFlagField.STRING.getFlag2CharTable());
        final String flagAsString = SamFlagField.STRING.format(flagAsInteger);
        
        Assert.assertEquals(flagAsString.compareTo(SamFlagField.STRING.getFlag2CharTable().replace("\0", "")), 0);
    }

    @Test
    public void testNoFlags() {
        final int flagAsInteger = SamFlagField.STRING.parse(SamFlagField.STRING.getNotFlag2CharTable());
        final String flagAsString = SamFlagField.STRING.format(flagAsInteger);

        Assert.assertEquals(flagAsString.compareTo(SamFlagField.STRING.getNotFlag2CharTable().replace("\0", "")), 0);
    }
    
    @Test
    public void testFlagTypesParsing() {
        Assert.assertEquals(SamFlagField.of("0"), SamFlagField.DECIMAL);
        Assert.assertEquals(SamFlagField.of("1234"), SamFlagField.DECIMAL);
        Assert.assertEquals(SamFlagField.of("0xDOESNOTMATTER"), SamFlagField.HEXADECIMAL);
        Assert.assertEquals(SamFlagField.of("0x"), SamFlagField.HEXADECIMAL);
        Assert.assertEquals(SamFlagField.of("0[^x]DOESNOTMATTER"), SamFlagField.OCTAL);
        Assert.assertEquals(SamFlagField.of("0a"), SamFlagField.OCTAL);
        Assert.assertEquals(SamFlagField.of("DOESNOTMATTER"), SamFlagField.STRING);
    }

    @Test
    public void testFlagTypesFormatting() {

        Assert.assertEquals(SamFlagField.DECIMAL.format(1), "1");
        Assert.assertEquals(SamFlagField.DECIMAL.format(124), "124");

        Assert.assertEquals(SamFlagField.HEXADECIMAL.format(1), "0x1");
        Assert.assertEquals(SamFlagField.HEXADECIMAL.format(9), "0x9");
        Assert.assertEquals(SamFlagField.HEXADECIMAL.format(10), "0xa");
        Assert.assertEquals(SamFlagField.HEXADECIMAL.format(16), "0x10");

        Assert.assertEquals(SamFlagField.OCTAL.format(1), "01");
        Assert.assertEquals(SamFlagField.OCTAL.format(124), "0174");

        Assert.assertEquals(SamFlagField.STRING.format(337), "pmMrF1s");
    }
    
    @Test(expectedExceptions = SAMFormatException.class)
    public void testIllegalStringFlagCharacter(){
        SamFlagField.STRING.parse("HELLO WORLD");
    }

    @Test(expectedExceptions = SAMFormatException.class)
    public void testIllegalStringFlagCharacterExclamation(){
        SamFlagField.STRING.parse("pmMr!F1s");
    }
}