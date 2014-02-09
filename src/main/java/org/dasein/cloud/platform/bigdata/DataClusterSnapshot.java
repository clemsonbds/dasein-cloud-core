/*
 * Copyright (C) 2014 Dell, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dasein.cloud.platform.bigdata;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Snapshots of data clusters that may be used to create/restore data clusters.
 * <p>Created by George Reese: 2/8/14 7:33 PM</p>
 * @author George Reese
 * @since 2014.03
 * @version 2014.03 initial version (issue #100)
 */
public class DataClusterSnapshot {
    /**
     * Constructs the most basic data cluster snapshot possible from the specified data.
     * @param providerOwnerId the owner of the snapshot
     * @param providerRegionId the region in which the snapshot is available
     * @param providerSnapshotId the unique ID of the snapshot
     * @param providerClusterId the cluster, if known, from which the snapshot was made
     * @param name a user-friendly name for the snapshot
     * @param description a long description of the snapshot
     * @param providerProductId the product associated with the cluster from which the snapshot was made
     * @param creationTimestamp the unix timestamp when the snapshot was created
     * @param currentState the current state of the snapshot
     * @param dbName the database name of the database that the snapshot contains
     * @return a cluster snapshot object matching the specified state
     */
    static public @Nonnull DataClusterSnapshot getInstance(@Nonnull String providerOwnerId, @Nonnull String providerRegionId, @Nonnull String providerSnapshotId, @Nullable String providerClusterId, @Nonnull String name, @Nonnull String description, @Nonnull String providerProductId, @Nonnegative long creationTimestamp, @Nonnull DataClusterSnapshotState currentState, @Nonnull String dbName) {
        return getInstance(providerOwnerId, providerRegionId, providerSnapshotId, null, null, name, description, providerProductId, creationTimestamp, true, currentState, "0", 1, dbName, 0);
    }

    /**
     * Constructs a data cluster snapshot from an extended set of data.
     * @param providerOwnerId the owner of the snapshot
     * @param providerRegionId the region in which the snapshot is available
     * @param providerSnapshotId the unique ID of the snapshot
     * @param providerClusterId the cluster, if known, from which the snapshot was made
     * @param providerDataCenterId the data center in which the cluster was operating when the snapshot was made
     * @param name a user-friendly name for the snapshot
     * @param description a long description of the snapshot
     * @param providerProductId the product associated with the cluster from which the snapshot was made
     * @param creationTimestamp the unix timestamp when the snapshot was created
     * @param automated true if this snapshot was the result of an automated process, false if manual
     * @param currentState the current state of the snapshot
     * @param clusterVersion the clustering version in force when the snapshot was made
     * @param nodeCount the number of nodes in the cluster when the snapshot was made
     * @param dbName the database name of the database that the snapshot contains
     * @param dbPort the query port for the database
     * @return a cluster snapshot object matching the specified state
     */
    static public @Nonnull DataClusterSnapshot getInstance(@Nonnull String providerOwnerId, @Nonnull String providerRegionId, @Nonnull String providerSnapshotId, @Nullable String providerClusterId, @Nullable String providerDataCenterId, @Nonnull String name, @Nonnull String description, @Nonnull String providerProductId, @Nonnegative long creationTimestamp, boolean automated, @Nonnull DataClusterSnapshotState currentState, @Nonnull String clusterVersion, @Nonnegative int nodeCount, @Nonnull String dbName, @Nonnegative int dbPort) {
        DataClusterSnapshot snapshot = new DataClusterSnapshot();

        snapshot.adminUserName = null;
        snapshot.automated = automated;
        snapshot.clusterVersion = clusterVersion;
        snapshot.creationTimestamp = creationTimestamp;
        snapshot.currentState = currentState;
        snapshot.databaseName = dbName;
        snapshot.databasePort = dbPort;
        snapshot.description = description;
        snapshot.name = name;
        snapshot.nodeCount = nodeCount;
        snapshot.providerClusterId = providerClusterId;
        snapshot.providerDataCenterId = providerDataCenterId;
        snapshot.providerOwnerId = providerOwnerId;
        snapshot.providerProductId = providerProductId;
        snapshot.providerRegionId = providerRegionId;
        snapshot.providerSnapshotId = providerSnapshotId;
        return snapshot;
    }

    private String                   adminUserName;
    private boolean                  automated;
    private String                   clusterVersion;
    private long                     creationTimestamp;
    private DataClusterSnapshotState currentState;
    private String                   databaseName;
    private int                      databasePort;
    private String                   description;
    private String                   name;
    private int                      nodeCount;
    private String                   providerClusterId;
    private String                   providerDataCenterId;
    private String                   providerOwnerId;
    private String                   providerProductId;
    private String                   providerRegionId;
    private String                   providerSnapshotId;

    private DataClusterSnapshot() { }

    @Override
    public boolean equals(@Nullable Object other) {
        if( other == null ) {
            return false;
        }
        if( other == this ) {
            return true;
        }
        if( !getClass().getName().equals(other.getClass().getName()) ) {
            return false;
        }
        DataClusterSnapshot s = (DataClusterSnapshot)other;

        return (providerOwnerId.equals(s.providerOwnerId) && providerRegionId.equals(s.providerRegionId) && providerSnapshotId.equals(s.providerSnapshotId));
    }

    /**
     * @return the admin user associated with the cluster behind this snapshot
     */
    public @Nonnull String getAdminUserName() {
        return adminUserName;
    }

    /**
     * @return the cluster version under which this snapshot was made
     */
    public @Nonnull String getClusterVersion() {
        return clusterVersion;
    }

    /**
     * @return the unix timestamp reflecting the time this snapshot was created
     */
    public @Nonnegative long getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * @return the current state of the snapshot
     */
    public @Nonnull DataClusterSnapshotState getCurrentState() {
        return currentState;
    }

    /**
     * @return the database name of the database contained in the snapshot
     */
    public @Nonnull String getDatabaseName() {
        return databaseName;
    }

    /**
     * @return the port on which this cluster listened to query requests
     */
    public @Nonnegative int getDatabasePort() {
        return databasePort;
    }

    /**
     * @return a user-friendly description of the snapshot
     */
    public @Nonnull String getDescription() {
        return description;
    }

    /**
     * @return a user-friendly name for the snapshot
     */
    public @Nonnull String getName() {
        return name;
    }

    /**
     * @return the number of nodes in operation when the snapshot occurred
     */
    public @Nonnegative int getNodeCount() {
        return nodeCount;
    }

    /**
     * @return the unique ID of the cluster, if known, from this this snapshot was taken
     */
    public @Nullable String getProviderClusterId() {
        return providerClusterId;
    }

    /**
     * @return the data center in which the snapshot was taken
     */
    public @Nullable String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    /**
     * @return the cloud account number of the cloud account that owns this snapshot
     */
    public @Nonnull String getProviderOwnerId() {
        return providerOwnerId;
    }

    /**
     * @return the product in place when the snapshot was created
     */
    public @Nonnull String getProviderProductId() {
        return providerProductId;
    }

    /**
     * @return the cloud region in which the snapshot is active
     */
    public @Nonnull String getProviderRegionId() {
        return providerRegionId;
    }

    /**
     * @return the unique ID of the snapshot
     */
    public @Nonnull String getProviderSnapshotId() {
        return providerSnapshotId;
    }

    @Override
    public int hashCode() {
        return (providerOwnerId + "/" + providerRegionId + "/" + providerSnapshotId).hashCode();
    }

    /**
     * Alters this data cluster snapshot to reflect the administrative credentials associated with this data cluster from
     * which the snapshot was made. This method does not affect an actual change in the cloud data cluster snapshot.
     * @param adminUser the administrative user name to use in SQL or other data access APIs
     * @return this
     */
    public @Nonnull DataClusterSnapshot havingAdminCredentials(@Nonnull String adminUser) {
        this.adminUserName = adminUser;
        return this;
    }

    /**
     * @return true if the snapshot was an automated snapshot, false if it was manually taken
     */
    public boolean isAutomated() {
        return automated;
    }

    @Override
    public @Nonnull String toString() {
        return providerSnapshotId;
    }
}
