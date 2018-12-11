package com.walmartlabs.ollie.guice;

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

import org.sonatype.siesta.server.SiestaServlet;

import com.google.inject.servlet.ServletModule;
import com.walmartlabs.ollie.OllieServerBuilder;
import com.walmartlabs.ollie.config.OllieConfigurationModule;

public class OllieServletModule extends ServletModule {

  private final OllieServerBuilder serverConfiguration;

  public OllieServletModule(OllieServerBuilder config) {
    this.serverConfiguration = config;
  }
      
  @Override
  protected void configureServlets() {
    bind(OllieServerBuilder.class).toInstance(serverConfiguration);
    OllieJaxRsModule jaxRsModule = new OllieJaxRsModule(serverConfiguration);
    install(jaxRsModule);
    //TODO: clean up this mess
    String apiPattern = jaxRsModule.apiPattern();
    if (!apiPattern.endsWith("/*")) {
      apiPattern += "/*";
    }
    serve(apiPattern, jaxRsModule.morePatterns()).with(SiestaServlet.class, jaxRsModule.parameters());
    install(new OllieConfigurationModule(serverConfiguration));
    install(new OllieSecurityModule(serverConfiguration, getServletContext()));
  }
}
