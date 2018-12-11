package com.walmartlabs.ollie.config.test;

/*-
 * *****
 * Ollie
 * -----
 * Copyright (C) 2018 Takari
 * -----
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =====
 */

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;
import com.typesafe.config.ConfigMemorySize;

@Singleton
public class ProvidedPojo implements TestPojo {

  private final boolean testBoolean;
  private final boolean testYesBoolean;
  private final long testLong;
  private final byte testByte;
  private final int testInt;
  private final double testDouble;
  private final float testFloat;
  private final String testString;
  private final List<Boolean> testListOfBoolean;
  private final List<Integer> testListOfInteger;
  private final List<Double> testListOfDouble;
  private final List<Long> testListOfLong;
  private final List<String> testListOfString;
  private final List<Duration> testListOfDuration;
  private final List<ConfigMemorySize> testListOfSize;
  private final List<NestedPojo> testListOfNested;
  private final Duration testDuration;
  private final ConfigMemorySize testSize;
  private final Map<String, Integer> testMap;
  private final Map<Integer, String> testMapIntkey;
  private final NestedPojo testNestedPojo;
  private final String nullValue;
  private final String missingValue;

  public ProvidedPojo(
    boolean testBoolean,
    boolean testYesBoolean,
    long testLong,
    byte testByte,
    int testInt,
    double testDouble,
    float testFloat,
    String testString,
    List<Boolean> testListOfBoolean,
    List<Integer> testListOfInteger,
    List<Double> testListOfDouble,
    List<Long> testListOfLong,
    List<String> testListOfString,
    List<Duration> testListOfDuration,
    List<ConfigMemorySize> testListOfSize,
    List<NestedPojo> testListOfNested,
    Duration testDuration,
    ConfigMemorySize testSize,
    Map<String, Integer> testMap,
    Map<Integer, String> testMapIntkey,
    NestedPojo testNestedPojo,
    String nullValue,
    String missingValue) {

    this.testBoolean = testBoolean;
    this.testYesBoolean = testYesBoolean;
    this.testLong = testLong;
    this.testByte = testByte;
    this.testInt = testInt;
    this.testDouble = testDouble;
    this.testFloat = testFloat;
    this.testString = testString;
    this.testListOfBoolean = testListOfBoolean;
    this.testListOfInteger = testListOfInteger;
    this.testListOfDouble = testListOfDouble;
    this.testListOfLong = testListOfLong;
    this.testListOfString = testListOfString;
    this.testListOfDuration = testListOfDuration;
    this.testListOfSize = testListOfSize;
    this.testListOfNested = testListOfNested;
    this.testDuration = testDuration;
    this.testSize = testSize;
    this.testMap = testMap;
    this.testMapIntkey = testMapIntkey;
    this.testNestedPojo = testNestedPojo;
    this.nullValue = nullValue;
    this.missingValue = missingValue;
  }

  public boolean isTestBoolean() {
    return testBoolean;
  }

  public boolean isTestYesBoolean() {
    return testYesBoolean;
  }

  public long getTestLong() {
    return testLong;
  }

  public byte getTestByte() {
    return testByte;
  }

  public int getTestInt() {
    return testInt;
  }

  public double getTestDouble() {
    return testDouble;
  }

  public float getTestFloat() {
    return testFloat;
  }

  public String getTestString() {
    return testString;
  }

  public List<Boolean> getTestListOfBoolean() {
    return testListOfBoolean;
  }

  public List<Integer> getTestListOfInteger() {
    return testListOfInteger;
  }

  public List<Double> getTestListOfDouble() {
    return testListOfDouble;
  }

  public List<Long> getTestListOfLong() {
    return testListOfLong;
  }

  public List<String> getTestListOfString() {
    return testListOfString;
  }

  public List<Duration> getTestListOfDuration() {
    return testListOfDuration;
  }

  public List<ConfigMemorySize> getTestListOfSize() {
    return testListOfSize;
  }

  public List<NestedPojo> getTestListOfNested() {
    return testListOfNested;
  }

  public Duration getTestDuration() {
    return testDuration;
  }

  public ConfigMemorySize getTestSize() {
    return testSize;
  }

  public Map<String, Integer> getTestMap() {
    return testMap;
  }

  public Map<Integer, String> getTestMapIntkey() {
    return testMapIntkey;
  }

  public NestedPojo getTestNestedPojo() {
    return testNestedPojo;
  }

  public String getNullValue() {
    return nullValue;
  }

  public String getMissingValue() {
    return missingValue;
  }
}
