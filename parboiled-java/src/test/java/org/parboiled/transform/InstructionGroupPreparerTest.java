/*
 * Copyright (C) 2009-2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.transform;

import org.parboiled.common.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstructionGroupPreparerTest extends TransformationTest {

    private final List<RuleMethodProcessor> processors = ImmutableList.of(
            new UnusedLabelsRemover(),
            new ReturnInstructionUnifier(),
            new InstructionGraphCreator(),
            new ImplicitActionsConverter(),
            new InstructionGroupCreator(),
            new InstructionGroupPreparer()
    );

    @Before
    public void setup() throws IOException {
        setup(TestParser.class);
    }

    @Test   //(dependsOnGroups = "primary")
    public void testRuleWithComplexActionSetup() throws Exception {
        RuleMethod method = processMethod("RuleWithComplexActionSetup", processors);

        assertEquals(method.getGroups().size(), 3);

        InstructionGroup group = method.getGroups().get(1);
        assertEquals(group.getName(), "Action$LmzJHalG7AngCUsX");
        assertEquals(group.getFields().size(), 3);
        assertEquals(group.getFields().get(0).desc, "I");
        assertEquals(group.getFields().get(1).desc, "I");
        assertEquals(group.getFields().get(2).desc, "I");

        group = method.getGroups().get(2);
        assertEquals(group.getName(), "Action$xPqljUb88dWGGhyd");
        assertEquals(group.getFields().size(), 5);
        assertEquals(group.getFields().get(0).desc, "Lorg/parboiled/transform/TestParser;");
        assertEquals(group.getFields().get(1).desc, "I");
        assertEquals(group.getFields().get(2).desc, "Lorg/parboiled/support/Var;");
        assertEquals(group.getFields().get(3).desc, "I");
        assertEquals(group.getFields().get(4).desc, "I");
    }

}
