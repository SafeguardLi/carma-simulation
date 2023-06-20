/*
 * Copyright (c) 2021 Old Dominion University. All rights reserved.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.mosaic.fed.infrastructure.configuration;

import org.eclipse.mosaic.lib.util.gson.TimeFieldAdapter;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * The Infrastructure Message Ambassador configuration class.
 */
public class InfrastructureConfiguration implements Serializable {

    private static final long serialVersionUID = 1479294781446446539L;

    /**
     * The Interval after which RSU send one message is updated. The default value
     * is 1000 (1s). Unit: [ms].
     */
    @JsonAdapter(TimeFieldAdapter.LegacyMilliSeconds.class)
    public Long updateInterval = 1000L;

    /**
     * The ID of the RSU that sends messages.
     */
    public String senderRSUId;
}
