package com.suntri.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class SimpleParserTests {

    private SimpleParser parser;

    @BeforeEach
    public void setup(){
        this.parser = new SimpleParser();
    }

    @Test
    public void shouldTakeSingleDashOption() throws Exception {
        String [] args = {"-h"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(1, inputs.size());
        Assertions.assertEquals(0, inputs.get("h").size());
    }

    @Test
    public void shouldTakeSingleDashOptionAndItsValue() throws Exception {
        String [] args = {"-o", "hello.txt", "world.txt"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(1, inputs.size());
        Assertions.assertEquals(2, inputs.get("o").size());
        Assertions.assertEquals("hello.txt", inputs.get("o").get(0));
        Assertions.assertEquals("world.txt", inputs.get("o").get(1));
    }

    @Test
    public void shouldTakeDuplicatedSingleDashOptionAndItsValue() throws Exception {
        String [] args = {"-o", "hello.txt", "-o", "world.txt"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(1, inputs.size());
        Assertions.assertEquals(2, inputs.get("o").size());
        Assertions.assertEquals("hello.txt", inputs.get("o").get(0));
        Assertions.assertEquals("world.txt", inputs.get("o").get(1));
    }

    @Test
    public void shouldTakeDoubleDashOption() throws Exception {
        String [] args = {"--help"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(1, inputs.size());
        Assertions.assertEquals(0, inputs.get("help").size());
    }

    @Test
    public void shouldTakeDoubleDashOptionAndItsValue() throws Exception {
        String [] args = {"--output", "hello.txt", "world.txt"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(1, inputs.size());
        Assertions.assertEquals(2, inputs.get("output").size());
        Assertions.assertEquals("hello.txt", inputs.get("output").get(0));
        Assertions.assertEquals("world.txt", inputs.get("output").get(1));
    }

    @Test
    public void shouldTakeDuplicatedDoubleDashOptionAndItsValue() throws Exception {
        String [] args = {"--output", "hello.txt", "--output", "world.txt"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(1, inputs.size());
        Assertions.assertEquals(2, inputs.get("output").size());
        Assertions.assertEquals("hello.txt", inputs.get("output").get(0));
        Assertions.assertEquals("world.txt", inputs.get("output").get(1));
    }

    @Test
    public void shouldTakeMixedDashOptions() throws Exception {
        String [] args = {"--help", "-h"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(2, inputs.size());
        Assertions.assertEquals(0, inputs.get("h").size());
        Assertions.assertEquals(0, inputs.get("help").size());
    }

    @Test
    public void shouldTakeMixedDashOptionsAndTheirValues() throws Exception {
        String [] args = {"--output", "hello.txt", "world.txt", "-o", "foo.txt", "bar.txt"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(2, inputs.size());
        Assertions.assertEquals(2, inputs.get("o").size());
        Assertions.assertEquals("foo.txt", inputs.get("o").get(0));
        Assertions.assertEquals("bar.txt", inputs.get("o").get(1));
        Assertions.assertEquals(2, inputs.get("output").size());
        Assertions.assertEquals("hello.txt", inputs.get("output").get(0));
        Assertions.assertEquals("world.txt", inputs.get("output").get(1));
    }

    @Test
    public void shouldTakeCompoundSingleDashOption() throws Exception {
        String [] args = {"-xvf"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(3, inputs.size());
        Assertions.assertEquals(0, inputs.get("x").size());
        Assertions.assertEquals(0, inputs.get("v").size());
        Assertions.assertEquals(0, inputs.get("f").size());
    }

    @Test
    public void shouldTakeCompoundSingleDashOptionAndProceedingValues() throws Exception {
        String [] args = {"-xvf", "hello.txt", "world.txt"};
        Map<String, List<String>> inputs = this.parser.parse(args);
        Assertions.assertNotNull(inputs);
        Assertions.assertEquals(3, inputs.size());
        Assertions.assertEquals(0, inputs.get("x").size());
        Assertions.assertEquals(0, inputs.get("v").size());
        Assertions.assertEquals(2, inputs.get("f").size());
        Assertions.assertEquals("hello.txt", inputs.get("f").get(0));
        Assertions.assertEquals("world.txt", inputs.get("f").get(1));
    }

}
