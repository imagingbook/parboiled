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

package org.parboiled.parserunners_tests;

import org.parboiled.BaseParser;
import org.parboiled.Parboiled;
import org.parboiled.parse.Rule;
import org.parboiled.testing.TestNgParboiledTest;
import org.junit.Test;

public class BugIn0990Test extends TestNgParboiledTest<Integer> {

    static class Parser extends BaseParser<Integer> {
        Rule Clause() {
            return Sequence(Id(), ZeroOrMore('.', Id()), ';');
        }

        Rule Id() {
            return OneOrMore(TestNot('.'), ANY);
        }
    }

    @Test
    public void testBugIn0990() {
        Parser parser = Parboiled.createParser(Parser.class);

        // threw IllegalStateException in 0.9.9.0
        testWithRecovery(parser.Clause(), "a.b;")
                .hasErrors("" +
                        "Unexpected end of input, expected ANY, '.' or ';' (line 1, pos 5):\n" +
                        "a.b;\n" +
                        "    ^\n");
    }
}
