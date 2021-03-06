package com.walmartlabs.ollie.lifecycle;

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

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class LifecycleRepository {

  private static final Logger logger = LoggerFactory.getLogger(LifecycleRepository.class);

  private final ExecutorService executor;
  private final Set<Lifecycle> tasks = Sets.newConcurrentHashSet();

  public LifecycleRepository(ExecutorService executor) {
    this.executor = executor;
  }

  public Set<Lifecycle> tasks() {
    return tasks;
  }

  public void register(Lifecycle task) {
    if (tasks.add(task)) {
      logger.info("Starting {} and registering for lifecycle management.", task.getClass().getName());
      executor.submit(() -> task.start());
    }
  }

  public synchronized void stop() {
    tasks.forEach(
        task -> {
          logger.info("Stopping {}", task.getClass().getName());
          task.stop();
        });
    tasks.clear();
    executor.shutdown();
    try {
      if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
    }
  }
}
