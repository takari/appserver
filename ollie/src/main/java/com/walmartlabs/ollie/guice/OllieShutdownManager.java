package com.walmartlabs.ollie.guice;

/*-
 * *****
 * Ollie
 * -----
 * Copyright (C) 2018 - 2019 Takari
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

import com.walmartlabs.ollie.lifecycle.Lifecycle;
import com.walmartlabs.ollie.lifecycle.LifecycleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class OllieShutdownManager {

  private static final Logger logger = LoggerFactory.getLogger(LifecycleRepository.class);
  private final LifecycleRepository taskRepository;
  private final Set<OllieShutdownListener> listeners;

  public OllieShutdownManager(LifecycleRepository taskRepository, Set<OllieShutdownListener> listeners) {
    this.taskRepository = taskRepository;
    this.listeners = listeners;
  }

  public synchronized void shutdown() {

    for (Lifecycle task : taskRepository.tasks()) {
      logger.info("Stopping {}", task.getClass().getName());
      task.stop();
    }

    for (OllieShutdownListener listener : listeners) {
      listener.listen();
    }
  }
}
