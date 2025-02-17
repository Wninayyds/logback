/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2015, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.classic.control;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.qos.logback.classic.Level;

/**
 * This class is for testing ControlLoggerContext which is a control class for
 * testing HLoggerContext.
 */
public class ControlLoggerContextTest {
    ControlLoggerContext clc;

    @BeforeEach
    public void setUp() throws Exception {
        clc = new ControlLoggerContext();
    }

    @Test
    public void smoke() {
        ControlLogger x = clc.getLogger("x");
        Assertions.assertEquals("x", x.getName());
        Assertions.assertEquals(clc.getRootLogger(), x.parent);

        ControlLogger abc = clc.getLogger("a.b.c");
        Assertions.assertEquals("a.b.c", abc.getName());
        Assertions.assertEquals(Level.DEBUG, abc.getEffectiveLevel());
    }

    @Test
    public void testCreation() {
        ControlLogger xyz = clc.getLogger("x.y.z");
        Assertions.assertEquals("x.y.z", xyz.getName());
        Assertions.assertEquals("x.y", xyz.parent.getName());
        Assertions.assertEquals("x", xyz.parent.parent.getName());
        Assertions.assertEquals("root", xyz.parent.parent.parent.getName());

        ControlLogger xyz_ = clc.exists("x.y.z");
        Assertions.assertEquals("x.y.z", xyz_.getName());

    }
}