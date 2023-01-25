/*
 * Copyright (C) 2022 parboiled contributors
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

package imagingbook.parboiled.transform;

import imagingbook.parboiled.Rule;
import imagingbook.parboiled.parser.BaseParser;
import imagingbook.parboiled.parser.Parboiled;
import imagingbook.parboiled.testing.TestNgParboiledTest;
import org.junit.Assert;
import org.junit.Test;

import java.lang.invoke.MethodHandles;

public class LookupMethodTest extends TestNgParboiledTest<Integer> {
    static boolean lookupMethodInvoked = false;

    public static class Parser extends BaseParser<Integer> {

        public Rule A() {
            return Sequence('a', push(42));
        }

        public static MethodHandles.Lookup lookup() {
            lookupMethodInvoked = true;
            return MethodHandles.lookup();
        }
    }

    @Test
    public void testLookupMethodUsed() {
        Parser parser = Parboiled.createParser(Parser.class);
        Assert.assertTrue("The lookup() method must be used if available.", lookupMethodInvoked);
    }
}
