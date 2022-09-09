package com.javaproject.test;

import org.junit.jupiter.api.Test;
import com.javaproject.JavaProject;

import static org.junit.jupiter.api.Assertions.*;

class JavaProjectTest {

    @Test
    void multiply() {
        JavaProject p = new JavaProject();
        assertEquals(100, p.multiply(10, 10));
    }
}