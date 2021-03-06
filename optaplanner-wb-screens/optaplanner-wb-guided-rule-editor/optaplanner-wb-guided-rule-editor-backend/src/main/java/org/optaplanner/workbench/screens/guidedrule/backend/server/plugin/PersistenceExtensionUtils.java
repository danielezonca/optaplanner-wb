/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.workbench.screens.guidedrule.backend.server.plugin;

public final class PersistenceExtensionUtils {

    public static final String EXCEPTION_MESSAGE_BASE = "Could not unmarshal action string ";

    private PersistenceExtensionUtils() {
    }

    private static String unwrap(final String stringToUnwrap,
                                 final Character wrappingCharacterStart,
                                 final Character wrappingCharacterEnd) {
        int start = stringToUnwrap.indexOf(wrappingCharacterStart);
        int end = stringToUnwrap.lastIndexOf(wrappingCharacterEnd);

        if (start < 0 && end < 0) {
            return stringToUnwrap;
        }

        if (start < 0 || end < 0 || end <= start) {
            final String message = String.format("\"%s\" had not characters '%c':%d and '%c':%d in appropriate order.",
                                                 stringToUnwrap, wrappingCharacterStart, start, wrappingCharacterEnd, end);
            throw new IllegalArgumentException(message);
        }
        return stringToUnwrap.substring(start + 1,
                                        end).trim();
    }

    /**
     * Return content that was wrapped in '(' and ')' characters
     * @param stringToUnwrap
     * @return unwrapped text
     */
    public static String unwrapParenthesis(final String stringToUnwrap) {
        return unwrap(stringToUnwrap, '(', ')');
    }

    /**
     * Return content that was wrapped in '{' and '}' characters
     * @param stringToUnwrap
     * @return unwrapped text
     */
    public static String unwrapCurlyBrackets(final String stringToUnwrap) {
        return unwrap(stringToUnwrap, '{', '}');
    }

    public static String extractConstraintMatchValue(final String s) {
        return s.equals("null") ? null : s;
    }
}
