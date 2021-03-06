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

public class NestedPojo {
  private int nestInt;
  private double nestDouble;
  private String nestString;

  public NestedPojo() {}

  public NestedPojo(
    int testInt,
    double testDouble,
    String testString) {
    this.setNestInt(testInt);
    this.setNestDouble(testDouble);
    this.setNestString(testString);
  }

  public int getNestInt() {
    return nestInt;
  }

  public void setNestInt(int nestInt) {
    this.nestInt = nestInt;
  }

  public double getNestDouble() {
    return nestDouble;
  }

  public void setNestDouble(double nestDouble) {
    this.nestDouble = nestDouble;
  }

  public String getNestString() {
    return nestString;
  }

  public void setNestString(String nestString) {
    this.nestString = nestString;
  }
}
