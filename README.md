# GWT Event DOM

![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)  [![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![Chat on Gitter](https://badges.gitter.im/hal/elemento.svg)](https://gitter.im/gwtproject/gwt-modules) ![CI](https://github.com/gwtproject/gwt-event-dom/workflows/CI/badge.svg)

A future-proof port of the `com.google.gwt.event.EventDOM` GWT module, with no dependency on `gwt-user` (besides the Java Runtime Emulation), to prepare for GWT 3 / J2Cl.

##  Migrating from `com.google.gwt.event.EventDOM`

```xml
<dependency>
    <groupId>org.gwtproject.event</groupId>
    <artifactId>gwt-event-dom</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.event:gwt-event-dom:HEAD-SNAPSHOT")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.event.EventDOM" />
   ```

3. Change the `import`s in your Java source files:

   ```java
   import org.gwtproject.event.dom.client.BlurEvent;
   ```

## Instructions

To build gwt-event-dom:

* run `mvn clean verify`

on the parent directory. This will build the artifact and run tests against the JVM, J2CL, and GWT2.

## System Requirements

**GWT Event DOM requires GWT 2.9.0 or newer!**

## Dependencies

GWT Event DOM depends on the following modules:
* gwt-dom
* gwt-event
* gwt-event-legacy

