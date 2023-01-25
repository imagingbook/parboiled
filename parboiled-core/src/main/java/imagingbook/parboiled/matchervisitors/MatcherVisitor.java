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

package imagingbook.parboiled.matchervisitors;

import imagingbook.parboiled.matchers.ActionMatcher;
import imagingbook.parboiled.matchers.AnyMatcher;
import imagingbook.parboiled.matchers.AnyOfMatcher;
import imagingbook.parboiled.matchers.CharIgnoreCaseMatcher;
import imagingbook.parboiled.matchers.CharMatcher;
import imagingbook.parboiled.matchers.CharRangeMatcher;
import imagingbook.parboiled.matchers.CustomMatcher;
import imagingbook.parboiled.matchers.EmptyMatcher;
import imagingbook.parboiled.matchers.FirstOfMatcher;
import imagingbook.parboiled.matchers.Matcher;
import imagingbook.parboiled.matchers.NothingMatcher;
import imagingbook.parboiled.matchers.OneOrMoreMatcher;
import imagingbook.parboiled.matchers.OptionalMatcher;
import imagingbook.parboiled.matchers.SequenceMatcher;
import imagingbook.parboiled.matchers.TestMatcher;
import imagingbook.parboiled.matchers.TestNotMatcher;
import imagingbook.parboiled.matchers.ZeroOrMoreMatcher;

/**
 * The interface to be implemented by all visitors of {@link Matcher}s.
 *
 * @param <R> the return value of this visitor
 * @see <a href="http://en.wikipedia.org/wiki/Visitor_pattern">Visitor Pattern on Wikipedia</a>
 */
public interface MatcherVisitor<R> {

    R visit(ActionMatcher matcher);

    R visit(AnyMatcher matcher);

    R visit(CharIgnoreCaseMatcher matcher);

    R visit(CharMatcher matcher);

    R visit(CustomMatcher matcher);

    R visit(CharRangeMatcher matcher);

    R visit(AnyOfMatcher matcher);

    R visit(EmptyMatcher matcher);

    R visit(FirstOfMatcher matcher);

    R visit(NothingMatcher matcher);

    R visit(OneOrMoreMatcher matcher);

    R visit(OptionalMatcher matcher);

    R visit(SequenceMatcher matcher);

    R visit(TestMatcher matcher);

    R visit(TestNotMatcher matcher);

    R visit(ZeroOrMoreMatcher matcher);

}
