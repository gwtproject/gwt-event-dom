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
package org.gwtproject.event.dom.client;

import static junit.framework.TestCase.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.event.legacy.shared.EventHandler;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.event.shared.SimpleEventBus;
import org.junit.Test;

/** Events test. */
@J2clTestInput(DomEventJ2clTest.class)
public class DomEventJ2clTest extends AbstractHandlerBaseJ2cl {

  @Test
  public void testKeyEvents() {
    final Flag flag = new Flag();
    eventbus = new SimpleEventBus();
    HandlerRegistration downRegistration =
        eventbus.addHandler(KeyDownEvent.getType(), event -> flag.flag = true);
    HandlerRegistration upRegistration =
        eventbus.addHandler(KeyUpEvent.getType(), event -> flag.flag = true);

    HandlerRegistration pressRegistration =
        eventbus.addHandler(KeyPressEvent.getType(), event -> flag.flag = true);

    checkFire(new KeyDownEvent(), downRegistration, flag, "onKeyDown");
    checkFire(new KeyUpEvent(), upRegistration, flag, "onKeyUp");
    checkFire(new KeyPressEvent(), pressRegistration, flag, "onKeyPressed");
  }

  @Test
  public void testMouseEvents() {
    final Flag flag = new Flag();
    eventbus = new SimpleEventBus();

    HandlerRegistration downRegistration =
        eventbus.addHandler(
            MouseDownEvent.getType(),
            new MouseDownHandler() {
              @Override
              public void onMouseDown(MouseDownEvent event) {
                flag.flag = true;
              }
            });
    HandlerRegistration upRegistration =
        eventbus.addHandler(
            MouseUpEvent.getType(),
            new MouseUpHandler() {
              @Override
              public void onMouseUp(MouseUpEvent event) {
                flag.flag = true;
              }
            });

    HandlerRegistration clickRegistration =
        eventbus.addHandler(ClickEvent.getType(), (ClickHandler) event -> flag.flag = true);

    HandlerRegistration dblclickRegistration =
        eventbus.addHandler(
            DoubleClickEvent.getType(), (DoubleClickHandler) event -> flag.flag = true);

    HandlerRegistration outRegistration =
        eventbus.addHandler(
            MouseOutEvent.getType(),
            new MouseOutHandler() {
              @Override
              public void onMouseOut(MouseOutEvent event) {
                flag.flag = true;
              }
            });
    HandlerRegistration overRegistration =
        eventbus.addHandler(
            MouseOverEvent.getType(),
            new MouseOverHandler() {
              @Override
              public void onMouseOver(MouseOverEvent event) {
                flag.flag = true;
              }
            });

    HandlerRegistration moveRegistration =
        eventbus.addHandler(
            MouseMoveEvent.getType(),
            new MouseMoveHandler() {
              @Override
              public void onMouseMove(MouseMoveEvent event) {
                flag.flag = true;
              }
            });

    HandlerRegistration wheelRegistration =
        eventbus.addHandler(
            MouseWheelEvent.getType(),
            new MouseWheelHandler() {
              @Override
              public void onMouseWheel(MouseWheelEvent event) {
                flag.flag = true;
              }
            });

    checkFire(new MouseDownEvent(), downRegistration, flag, "onMouseDown");
    checkFire(new MouseUpEvent(), upRegistration, flag, "onMouseUp");
    checkFire(new MouseOutEvent(), outRegistration, flag, "onMouseOut");
    checkFire(new MouseOverEvent(), overRegistration, flag, "onMouseOver");
    checkFire(new MouseMoveEvent(), moveRegistration, flag, "onMouseMove");
    checkFire(new MouseWheelEvent(), wheelRegistration, flag, "onMouseWheel");
    checkFire(new ClickEvent(), clickRegistration, flag, "onClick");
    checkFire(new DoubleClickEvent(), dblclickRegistration, flag, "onDoubleClick");
  }

  // TODO: Once we have the button widget migrated, we can add these tests!
  //  @Test
  //  public void testMouseEventCoordinates() {
  //    Button b = new Button();
  //    RootPanel.get().add(b);
  //
  //    final Flag flag = new Flag();
  //    b.addMouseDownHandler(new MouseDownHandler() {
  //      @Override
  //      public void onMouseDown(MouseDownEvent event) {
  //        assertEquals("", 16, event.getX());
  //        assertEquals("", 8, event.getY());
  //        flag.flag = true;
  //      }
  //    });
  //
  //    int x = b.getAbsoluteLeft() + 16;
  //    int y = b.getAbsoluteTop() + 8;
  //    NativeEvent event = Document.get().createMouseDownEvent(0, x, y, x, y,
  //        false, false, false, false, 1);
  //    b.getElement().dispatchEvent(event);
  //
  //    assertTrue("Never received expected mouse-down event", flag.flag);
  //  }
  //
  // TODO: Once we have the button widget migrated, we can add these tests!
  //  public void testMultipleDomEventTypesPerEventName() {
  //    Button b = new Button();
  //    RootPanel.get().add(b);
  //
  //    final Flag first = new Flag();
  //    b.addClickHandler(new ClickHandler() {
  //      @Override
  //      public void onClick(ClickEvent event) {
  //        first.flag = true;
  //      }
  //    });
  //
  //    final Flag second = new Flag();
  //    b.addDomHandler(new CustomClickHandler() {
  //      @Override
  //      public void onClick(CustomClickEvent event) {
  //        second.flag = true;
  //      }
  //    }, CustomClickEvent.getType());
  //
  //    NativeEvent event = Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false,
  // false);
  //    b.getElement().dispatchEvent(event);
  //
  //    assertTrue("Never received expected click event", first.flag);
  //    assertTrue("Never received expected click event", second.flag);
  //  }

  private void checkFire(
      DomEvent<?> event, HandlerRegistration registration, Flag flag, String eventName) {

    flag.flag = false;
    eventbus.fireEvent(event);
    assertTrue(eventName + " didn't fire.", flag.flag);

    flag.flag = false;
    registration.removeHandler();
    // event.reset(null);
    eventbus.fireEvent(event);
    assertTrue(eventName + " fired when it shouldn't have.", !flag.flag);
  }

  private SimpleEventBus eventbus;

  interface CustomClickHandler extends EventHandler {

    void onClick(CustomClickEvent evt);
  }

  static class Flag {

    public boolean flag = false;
  }

  static class CustomClickEvent extends MouseEvent<CustomClickHandler> {

    public static final Type<CustomClickHandler> TYPE = new Type<>("click", new CustomClickEvent());

    public static Type<CustomClickHandler> getType() {
      return TYPE;
    }

    @Override
    public Type<CustomClickHandler> getAssociatedType() {
      return TYPE;
    }

    @Override
    protected void dispatch(CustomClickHandler handler) {
      handler.onClick(this);
    }
  }
}
