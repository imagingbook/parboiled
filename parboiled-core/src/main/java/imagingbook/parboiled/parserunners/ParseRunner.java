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

package imagingbook.parboiled.parserunners;

import imagingbook.parboiled.buffers.InputBuffer;
import imagingbook.parboiled.errors.ParseError;
import imagingbook.parboiled.support.ParsingResult;
import imagingbook.parboiled.support.ValueStack;

import java.util.List;

/**
 * A ParseRunner performs the actual parsing run of a given parser rule on a given input text.
 */
public interface ParseRunner<V> {

    /**
     * Initializes the parse runner with the given error list.
     *
     * @param parseErrors the error list to start off with
     * @return this instance
     */
    ParseRunner<V> withParseErrors(List<ParseError> parseErrors);

    /**
     * Initializes the parse runner with the given ValueStack instance.
     *
     * @param valueStack the ValueStack to use
     * @return this instance
     */
    ParseRunner<V> withValueStack(ValueStack<V> valueStack);

    /**
     * Performs the actual parse and creates a corresponding ParsingResult instance.
     *
     * @param input the input text to parse
     * @return the ParsingResult for the run
     */
    ParsingResult<V> run(String input);

    /**
     * Performs the actual parse and creates a corresponding ParsingResult instance.
     *
     * @param input the input text to parse
     * @return the ParsingResult for the run
     */
    ParsingResult<V> run(char[] input);

    /**
     * Performs the actual parse and creates a corresponding ParsingResult instance.
     *
     * @param inputBuffer the inputBuffer to use
     * @return the ParsingResult for the run
     */
    ParsingResult<V> run(InputBuffer inputBuffer);
}
