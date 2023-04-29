import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerSetTest {

    PowerSet powerSet = new PowerSet();

    @Test
    public void put_whenValueNonExistent() {
        powerSet.put("1");

        assertEquals(1, powerSet.size());
    }

    @Test
    public void put_whenValueExisting() {
        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");
        powerSet.put("3");

        assertEquals(3, powerSet.size());
    }

    @Test
    public void remove_whenValueNonExistent() {
        assertFalse(powerSet.remove("1"));
    }

    @Test
    public void remove_whenValueExisting() {
        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");

        assertTrue(powerSet.remove("1"));
        assertTrue(powerSet.remove("2"));
        assertTrue(powerSet.remove("3"));
        assertEquals(0, powerSet.size());
    }

    @Test
    public void intersection_whenEmptyResult() {
        PowerSet powerSet1 = new PowerSet();
        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");

        powerSet.put("4");
        powerSet.put("5");
        powerSet.put("6");

        assertEquals(0, powerSet.intersection(powerSet1).size());
    }

    @Test
    public void intersection_whenNonEmptyResult() {
        PowerSet powerSet1 = new PowerSet();
        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");
        powerSet1.put("4");
        powerSet1.put("5");
        powerSet1.put("6");

        powerSet.put("2");
        powerSet.put("3");
        powerSet.put("5");

        PowerSet result = powerSet.intersection(powerSet1);
        assertEquals(3, result.size());
        assertTrue(result.get("2"));
        assertTrue(result.get("3"));
        assertTrue(result.get("5"));

        assertFalse(result.get("1"));
        assertFalse(result.get("4"));
        assertFalse(result.get("6"));
    }

    @Test
    public void union_whenParameterEmptyPowerSet() {
        PowerSet powerSet1 = new PowerSet();

        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");

        PowerSet result = powerSet.union(powerSet1);
        assertEquals(3, result.size());
        assertTrue(result.get("1"));
        assertTrue(result.get("2"));
        assertTrue(result.get("3"));
    }

    @Test
    public void union_whenThisEmptyPowerSet() {
        PowerSet powerSet1 = new PowerSet();

        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");

        PowerSet result = powerSet.union(powerSet1);
        assertEquals(3, result.size());
        assertTrue(result.get("1"));
        assertTrue(result.get("2"));
        assertTrue(result.get("3"));
    }

    @Test
    public void union_whenThisAndParameterEmpty() {
        PowerSet powerSet1 = new PowerSet();

        PowerSet result = powerSet.union(powerSet1);
        assertEquals(0, result.size());
    }

    @Test
    public void union_whenThisAndParameterNonEmpty() {
        PowerSet powerSet1 = new PowerSet();

        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");

        powerSet1.put("2");
        powerSet1.put("3");
        powerSet1.put("4");

        PowerSet result = powerSet.union(powerSet1);
        assertEquals(4, result.size());
        assertTrue(result.get("1"));
        assertTrue(result.get("2"));
        assertTrue(result.get("3"));
        assertTrue(result.get("4"));
    }

    @Test
    public void difference_whenThisAndParameterNonEmpty() {
        PowerSet powerSet1 = new PowerSet();

        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");

        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");
        powerSet.put("4");
        powerSet.put("5");

        PowerSet result = powerSet.difference(powerSet1);
        assertEquals(2, result.size());
        assertTrue(result.get("4"));
        assertTrue(result.get("5"));
    }

    @Test
    public void difference_whenThisEmptyPowerSet() {
        PowerSet powerSet1 = new PowerSet();

        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");

        PowerSet result = powerSet.difference(powerSet1);
        assertEquals(0, result.size());
        assertFalse(result.get("1"));
        assertFalse(result.get("2"));
        assertFalse(result.get("3"));
    }

    @Test
    public void difference_whenParameterEmptyPowerSet() {
        PowerSet powerSet1 = new PowerSet();

        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");

        PowerSet result = powerSet.difference(powerSet1);
        assertEquals(3, result.size());
        assertTrue(result.get("1"));
        assertTrue(result.get("2"));
        assertTrue(result.get("3"));
    }
    @Test
    public void difference_whenThisAndParameterEmptyPowerSet() {
        PowerSet powerSet1 = new PowerSet();

        PowerSet result = powerSet.difference(powerSet1);
        assertEquals(0, result.size());
    }

    @Test
    public void isSubset_whenParameterIsSubset() {
        PowerSet powerSet1 = new PowerSet();

        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");

        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");
        powerSet.put("4");
        powerSet.put("5");

        assertTrue(powerSet.isSubset(powerSet1));
    }

    @Test
    public void isSubset_whenParameterIsNotSubset() {
        PowerSet powerSet1 = new PowerSet();

        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");
        powerSet1.put("4");
        powerSet1.put("5");

        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");

        assertFalse(powerSet.isSubset(powerSet1));
    }

    @Test
    public void isSubset_whenThisIsSubset() {
        PowerSet powerSet1 = new PowerSet();

        powerSet1.put("1");
        powerSet1.put("2");
        powerSet1.put("3");

        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");
        powerSet.put("4");
        assertTrue(powerSet.isSubset(powerSet1));
    }

    @Test()
    public void put_performanceTest() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            powerSet.put(i + " val");
        }
        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) < 2000);
    }

    @Test
    public void isSubset_performanceTest() {
        PowerSet powerSet1 = new PowerSet();
        for (int i = 0; i < 10000; i++) {
            powerSet1.put(i + " val");
        }

        for (int i = 0; i < 20000; i++) {
            powerSet.put(i + " val");
        }
        assertTrue(powerSet.isSubset(powerSet1));
    }
}