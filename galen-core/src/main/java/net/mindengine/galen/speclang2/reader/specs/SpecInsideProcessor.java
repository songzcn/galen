/*******************************************************************************
* Copyright 2015 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.galen.speclang2.reader.specs;

import net.mindengine.galen.parser.Expectations;
import net.mindengine.galen.parser.SyntaxException;
import net.mindengine.galen.specs.Location;
import net.mindengine.galen.specs.Spec;
import net.mindengine.galen.specs.SpecInside;
import net.mindengine.galen.specs.reader.StringCharReader;

import java.util.List;

public class SpecInsideProcessor implements SpecProcessor {

    public static final String MISSING_OBJECT_NAME = "Missing object name";

    @Override
    public Spec process(StringCharReader reader) {
        boolean partly = false;
        String name;

        String firstWord = reader.readWord();
        if (firstWord.isEmpty()) {
            throw new SyntaxException(MISSING_OBJECT_NAME);
        }

        if (firstWord.equals("partly")) {
            partly = true;
            name = reader.readWord();
            if (name.isEmpty()) {
                throw new SyntaxException(MISSING_OBJECT_NAME);
            }
        } else {
            name = firstWord;
        }

        List<Location> locations = Expectations.locations().read(reader);

        SpecInside specInside = new SpecInside(name, locations);
        specInside.setPartly(partly);

        return specInside;
    }
}
