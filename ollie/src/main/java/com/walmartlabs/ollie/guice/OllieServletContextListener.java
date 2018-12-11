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

import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.SessionCookieConfig;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.walmartlabs.ollie.OllieServerBuilder;

import com.walmartlabs.ollie.SessionCookieOptions;
import com.walmartlabs.ollie.database.DatabaseModule;
import org.eclipse.sisu.space.BeanScanning;
import org.eclipse.sisu.space.SpaceModule;
import org.eclipse.sisu.space.URLClassSpace;
import org.eclipse.sisu.wire.WireModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sisu {@link GuiceServletContextListener}.
 *
 * @since 2.0
 */
public class OllieServletContextListener
  extends GuiceServletContextListener {
  
  public static final String INJECTOR_KEY = "@INJECTOR"; // NOTE: GuiceServletContextListener binds this into Injector.class.getName()
  protected final Logger log = LoggerFactory.getLogger(getClass());
  private ServletContext servletContext;
  private Injector injector;
  private final OllieServerBuilder config;

  public OllieServletContextListener(OllieServerBuilder config) {
    this.config = config;
  }
  
  @Override
  public void contextInitialized(final ServletContextEvent event) {
    checkNotNull(event);
    // capture the servlet context, some modules may need this and otherwise have no access to it (like shiro modules)
    servletContext = event.getServletContext();
    // We need to set the injector here first because super.contextInitialized() will call getInjector() so if we have not retrieved
    // our injector created elsewhere, say from a testing environment, a new one will be created and cause inconsistencies.
    injector = (Injector) event.getServletContext().getAttribute(INJECTOR_KEY);

    Set<SessionCookieOptions> opts = config.sessionCookieOptions();
    SessionCookieConfig cfg = servletContext.getSessionCookieConfig();
    if (opts.contains(SessionCookieOptions.SECURE)) {
      cfg.setSecure(true);
    }
    if (opts.contains(SessionCookieOptions.HTTP_ONLY)) {
      cfg.setHttpOnly(true);
    }

    super.contextInitialized(event);
  }

  protected ServletContext getServletContext() {
    return servletContext;
  }

  @Override
  protected Injector getInjector() {
    if (injector == null) {
      injector = createInjector();
    }
    return injector;
  }

  protected Injector createInjector() {
    List<Module> modules = Lists.newArrayList();
    configureModules(modules);
    if (log.isDebugEnabled() && !modules.isEmpty()) {
      log.debug("Modules:");
      for (Module module : modules) {
        log.debug("  {}", module);
      }
    }
    return Guice.createInjector(new WireModule(modules));
  }

  // TODO, these all needs to go within the space module
  protected void configureModules(final List<Module> modules) {
    modules.add(new SpaceModule(new URLClassSpace(getClass().getClassLoader()), BeanScanning.CACHE));
    modules.add(new OllieServletModule(config));
    if (config.hasDBSupport()) {
      modules.add(new DatabaseModule(config));
    }
    modules.addAll(config.modules());
  }
}
