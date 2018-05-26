/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * Copyright 2018 The GWT Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.event.dom.client;

import com.google.gwt.junit.client.GWTTestCase;
import org.gwtproject.event.shared.EventHandler;

import java.util.HashSet;

/**
 * Support code for handler tests.
 */
public abstract class HandlerTestBase extends GWTTestCase {

    class Adaptor implements ClickHandler, MouseDownHandler {

        @Override
        public void onClick(ClickEvent event) {
            add(this);
        }

        @Override
        public void onMouseDown(MouseDownEvent event) {
            add(this);
        }

        @Override
        public String toString() {
            return "adaptor 1";
        }
    }


    Adaptor adaptor1 = new Adaptor();
    private HashSet<EventHandler> active = new HashSet<>();
    MouseDownHandler mouse1 = new MouseDownHandler() {
        @Override
        public void onMouseDown(MouseDownEvent event) {
            add(mouse1);
        }

        @Override
        public String toString() {
            return "mouse 1";
        }
    };
    MouseDownHandler mouse2 = new MouseDownHandler() {
        @Override
        public void onMouseDown(MouseDownEvent event) {
            add(mouse2);
        }

        @Override
        public String toString() {
            return "mouse 2";
        }
    };
    MouseDownHandler mouse3 = new MouseDownHandler() {
        @Override
        public void onMouseDown(MouseDownEvent event) {
            add(mouse3);
        }

        @Override
        public String toString() {
            return "mouse 3";
        }
    };
    ClickHandler click1 = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            add(click1);
        }

        @Override
        public String toString() {
            return "click 1";
        }
    };
    ClickHandler click2 = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            add(click2);
        }

        @Override
        public String toString() {
            return "click 2";
        }
    };
    ClickHandler click3 = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            add(click3);
        }

        @Override
        public String toString() {
            return "click 3";
        }
    };

    @Override
    public String getModuleName() {
        return "org.gwtproject.event.dom.EventDOM";
    }

    void add(EventHandler handler) {
        active.add(handler);
    }

    void assertFired(EventHandler... handler) {
        for (int i = 0; i < handler.length; i++) {
            assertTrue(handler[i] + " should have fired", active.contains(handler[i]));
        }
    }

    void assertNotFired(EventHandler... handler) {
        for (int i = 0; i < handler.length; i++) {
            assertFalse(handler[i] + " should not have fired",
                    active.contains(handler[i]));
        }
    }

    void reset() {
        active.clear();
    }
}
