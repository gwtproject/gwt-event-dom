/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.event.dom;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.gwtproject.event.dom.client.DomEventGwt2Test;

/** Tests of the animation package. */
public class EventDOMGwtSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite("Tests of the event dom package");

    suite.addTestSuite(DomEventGwt2Test.class);

    return suite;
  }
}
