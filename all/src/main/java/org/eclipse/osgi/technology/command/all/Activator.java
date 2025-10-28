/**
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Stefan Bischof - initial
 */
package org.eclipse.osgi.technology.command.all;

import java.util.List;

import org.osgi.annotation.bundle.Header;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Header(name = Constants.BUNDLE_ACTIVATOR, value = "${@class}")
@org.osgi.annotation.bundle.Capability(namespace = "org.apache.felix.gogo", name = "command.implementation", version = "1.0.0")
@org.osgi.annotation.bundle.Requirement(effective = "active", namespace = "org.apache.felix.gogo", name = "runtime.implementation", version = "1.0.0")
public class Activator implements BundleActivator {

	private static final Logger logger = LoggerFactory.getLogger(Activator.class);

	private List<BundleActivator> activators = List.of(
			new org.eclipse.osgi.technology.command.converter.bundle.Activator(),
			new org.eclipse.osgi.technology.command.converter.file.Activator(),
			new org.eclipse.osgi.technology.command.diagnostics.Activator(),
			new org.eclipse.osgi.technology.command.fs.navigate.Activator(),
			new org.eclipse.osgi.technology.command.help.Activator(),
			new org.eclipse.osgi.technology.command.mxbean.Activator(),
			new org.eclipse.osgi.technology.command.osgi.framework.Activator(),
			new org.eclipse.osgi.technology.command.osgi.framework.modify.Activator(),
			new org.eclipse.osgi.technology.command.osgi.service.jakartars.Activator(),
			new org.eclipse.osgi.technology.command.osgi.service.scr.Activator(),
			new org.eclipse.osgi.technology.command.osgi.service.servlet.Activator(),
			new org.eclipse.osgi.technology.command.system.runtime.Activator());

	@Override
	public void start(BundleContext context) throws Exception {

		activators.forEach(a -> {

			try {
				a.start(context);
			} catch (Exception e) {
				logger.error("Failed to start activator: {}", a.getClass().getName(), e);
			}

		});
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		activators.forEach(a -> {

			try {
				a.stop(context);
			} catch (Exception e) {
				logger.error("Failed to stop activator: {}", a.getClass().getName(), e);
			}

		});
	}
}
