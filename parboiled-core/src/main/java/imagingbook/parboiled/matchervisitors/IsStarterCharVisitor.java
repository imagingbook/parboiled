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
import imagingbook.parboiled.support.Chars;

/**
 * A {@link MatcherVisitor} determining whether a matcher can start a match with a given char.
 */
public class IsStarterCharVisitor implements MatcherVisitor<Boolean> {

    private final CanMatchEmptyVisitor canMatchEmptyVisitor = new CanMatchEmptyVisitor();
    private final char starterChar;

    public IsStarterCharVisitor(char starterChar) {
        this.starterChar = starterChar;
    }

    public Boolean visit(ActionMatcher matcher) {
        return false;
    }

    public Boolean visit(AnyMatcher matcher) {
        return starterChar != Chars.EOI;
    }

    public Boolean visit(CharIgnoreCaseMatcher matcher) {
        return matcher.charLow == starterChar || matcher.charUp == starterChar;
    }

    public Boolean visit(CharMatcher matcher) {
        return matcher.character == starterChar;
    }

    public Boolean visit(CharRangeMatcher matcher) {
        return matcher.cLow <= starterChar && starterChar <= matcher.cHigh;
    }

    public Boolean visit(AnyOfMatcher matcher) {
        return matcher.characters.contains(starterChar);
    }

    public Boolean visit(CustomMatcher matcher) {
        return matcher.isStarterChar(starterChar);
    }

    public Boolean visit(EmptyMatcher matcher) {
        return false;
    }

    public Boolean visit(FirstOfMatcher matcher) {
        for (Matcher child : matcher.getChildren()) {
            if (child.accept(this)) return true;
        }
        return false;
    }

    public Boolean visit(NothingMatcher matcher) {
        return false;
    }

    public Boolean visit(OneOrMoreMatcher matcher) {
        return matcher.subMatcher.accept(this);
    }

    public Boolean visit(OptionalMatcher matcher) {
        return matcher.subMatcher.accept(this);
    }

    public Boolean visit(SequenceMatcher matcher) {
        for (Matcher child : matcher.getChildren()) {
            if (child.accept(this)) return true;
            if (!child.accept(canMatchEmptyVisitor)) break;
        }
        return false;
    }

    public Boolean visit(TestMatcher matcher) {
        return matcher.subMatcher.accept(this);
    }

    public Boolean visit(TestNotMatcher matcher) {
        return false;
    }

    public Boolean visit(ZeroOrMoreMatcher matcher) {
        return matcher.subMatcher.accept(this);
    }

}
