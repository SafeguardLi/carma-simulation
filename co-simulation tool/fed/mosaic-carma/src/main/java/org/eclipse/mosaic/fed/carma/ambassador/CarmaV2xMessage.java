/*
 * Copyright (C) 2019-2022 LEIDOS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.eclipse.mosaic.fed.carma.ambassador;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Message to be sent or received by the CARMA Platform NS-3 Adapater interface
 */
public class CarmaV2xMessage {

    private InetAddress originAddress;
    private String version;
    private String type;
    private int psid;
    private String vehicleId;
    private int priority;
    private String txMode;
    private int txChannel; 
    private int txInterval;
    private String deliveryStart;
    private String deliveryStop;
    private boolean signature;
    private boolean encryption;
    private double vehiclePosX;
    private double vehiclePosY;

    public CarmaV2xMessage(InetAddress originAddress, String version, String type, int psid, String vehicleId, int priority, String txMode,
            int txChannel, int txInterval, String deliveryStart, String deliveryStop, boolean signature,
            boolean encryption, double vehiclePosX, double vehiclePosY) {
        this.originAddress = originAddress;
        this.version = version;
        this.type = type;
        this.psid = psid;
        this.vehicleId = vehicleId;
        this.priority = priority; this.txMode = txMode;
        this.txChannel = txChannel;
        this.txInterval = txInterval;
        this.deliveryStart = deliveryStart;
        this.deliveryStop = deliveryStop;
        this.signature = signature;
        this.encryption = encryption;
        this.vehiclePosX = vehiclePosX;
        this.vehiclePosY = vehiclePosY;
    }

    public CarmaV2xMessage(String version, String type, int psid, String vehicleId, int priority, String txMode,
                           int txChannel, int txInterval, String deliveryStart, String deliveryStop, boolean signature,
                           boolean encryption, double vehiclePosX, double vehiclePosY) {
        this.version = version;
        this.type = type;
        this.psid = psid;
        this.vehicleId = vehicleId;
        this.priority = priority; this.txMode = txMode;
        this.txChannel = txChannel;
        this.txInterval = txInterval;
        this.deliveryStart = deliveryStart;
        this.deliveryStop = deliveryStop;
        this.signature = signature;
        this.encryption = encryption;
        this.vehiclePosX = vehiclePosX;
        this.vehiclePosY = vehiclePosY;
    }

    public CarmaV2xMessage(byte[] bytes) {
        parseV2xMessage(bytes);
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    public int getPsid() {
        return psid;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getPriority() {
        return priority;
    }

    public String getTxMode() {
        return txMode;
    }

    public int getTxChannel() {
        return txChannel;
    }

    public int getTxInterval() {
        return txInterval;
    }

    public String getDeliveryStart() {
        return deliveryStart;
    }

    public String getDeliveryStop() {
        return deliveryStop;
    }

    public boolean isSignature() {
        return signature;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public double getVehiclePosX() {
        return vehiclePosX;
    }

    public double getVehiclePosY() {
        return vehiclePosY;
    }

    /**
     * Constructor helper, decodes the format sent by the CARMA Platform driver
     * @param buf A binary array containing the data sent from the CARMA Platform's ns3 adapter
     */
    private void parseV2xMessage(byte[] buf)  {
        String msg = new String(buf, StandardCharsets.UTF_8);
        String[] msgParts = msg.split("=");
        
        for (int i = 0; i < msgParts.length; i++) {
            if (msgParts[i].equals("Version")) {
                version = msgParts[++i];
            } else if (msgParts[i].equals("Type")) {
                type = msgParts[++i];
            } else if (msgParts[i].equals("PSID")) {
                psid = Integer.parseInt(msgParts[++i]);
            } else if (msgParts[i].equals("VehicleID")) {
                vehicleId = msgParts[++i];
            } else if (msgParts[i].equals("Priority")) {
                priority = Integer.parseInt(msgParts[++i]);
            } else if (msgParts[i].equals("TxMode")) {
                txMode = msgParts[++i];
            } else if (msgParts[i].equals("TxChannel")) {
                txChannel = Integer.parseInt(msgParts[++i]);
            } else if (msgParts[i].equals("TxInterval")) {
                txInterval = Integer.parseInt(msgParts[++i]);
            } else if (msgParts[i].equals("DeliveryStart")) {
               // NOP 
            } else if (msgParts[i].equals("DeliveryStop")) {
               // NOP 
            } else if (msgParts[i].equals("Signature")) {
                signature = Boolean.parseBoolean(msgParts[++i]);
            } else if (msgParts[i].equals("Encryption")) {
                encryption = Boolean.parseBoolean(msgParts[++i]);
            } else if (msgParts[i].equals("VehiclePosX")) {
                vehiclePosX = Double.parseDouble(msgParts[++i]);
            } else if (msgParts[i].equals("VehiclePosY")) {
                vehiclePosY = Double.parseDouble(msgParts[++i]);
            } else {
                throw new IllegalArgumentException("No such field in CarmaV2xMessage.");
            }
        }
    }

    /**
     * Encode this CARMA V2X Message into a binary format suitable for receipt at the CARMA Platform driver
     * @return The encoded binary data
     */
    public byte[] encodeV2xMessage() {
        // TODO: Implement encoding
        return new byte[10];
    }

    public InetAddress getOriginAddress() {
        return originAddress;
    }
}
